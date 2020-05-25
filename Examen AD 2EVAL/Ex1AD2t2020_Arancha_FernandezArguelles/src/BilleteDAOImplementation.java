
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Asiento;
import pojos.Billete;
import pojos.Cliente;
import pojos.Pasajero;
import pojos.Vuelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public class BilleteDAOImplementation implements IBilleteDAO {

    private Session session;
    private Transaction transaction;

    // MÉTODOS AUXILIARES
    public void openSessionAndTransaction() {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }

    /**
     * Recorrerá la lista de pasajeros buscando aquellos que tengan menos de 18 años, y les aplicará el descuento
     * @param billete del que se desea calcular el importe (será un ORM TRANSIENT, aún no habrá sido persistido)
     * @return billete con el importe calculado, si falla la operación el billete devuelvo no tendrá asignada ningún ID
     */
    @Override
    public Billete calcularPrecioFinal(Billete billete) {
        openSessionAndTransaction();
        float importeTotaL = 0;
        // Fecha límite para saber si es menor
        GregorianCalendar gcLimit = (GregorianCalendar) Calendar.getInstance();
        gcLimit.add(Calendar.YEAR, -18);
        Date fechaLimite = gcLimit.getTime();
        // Recorremos la lista calculando el importe para cada pasajero según su edad
        for (Pasajero pasajero : billete.getPasajeros()) {
            if (pasajero.getFechaNacimiento().after(fechaLimite)) {
                importeTotaL += billete.getPrecioPasajero() * (1 - billete.getDescuentoNiños()); // Entendiendo el descuento en tanto por 1
            } else {
                importeTotaL += billete.getPrecioPasajero();
            }
        }
        // Asignamos importe total al billete
        billete.setPrecioFinal(importeTotaL);
        // Persistimos el billete (generando el ID automático!)
        try {
            Long idGenerado = (Long) session.save(billete);
            transaction.commit();
            // Si no tuvo éxito la operación, el billete devuelto no tendrá ID (sabremos que ha fallado y no se ha grabado)
            billete.setCodigo(idGenerado);
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage() + "\nInit cause: " + he.initCause(he) + "\n");
        } finally {
            session.close();
        }

        return billete;
    }

    /**
     * Se mirará el nº de pasajeros del billete (size de su lista pasajeros) para comprobar que son 6 o menos,
     * luego se buscará en el vuelo una fila ENTERA libre (para no complicarnos)
     * El objeto Billete deberá estar en estado DETACHED (Transient daría exception, pues vamos a hacer update()).
     * @param billete del que se desea recolocar los asientos
     * @return map con la respuesta a la operación (TRUE exitosa, FALSE error) y el billete retornado (modificado o no, según el ressultado)
     */
    @Override
    public Map<Boolean, Billete> reAsignarAsientosFamilia(Billete billete) {

        Map<Boolean, Billete> mapReturn = new HashMap<>();
        mapReturn.put(Boolean.FALSE, billete); // devolveremos TRUE sólo una vez finalizada con éxito la operación
        // Si son más de 6 -> Exception, y retornamos el billete sin modificar y un FALSE
        if (billete.getPasajeros().size() > 6) {
            try {
                throw new noFamiliaException();
            } catch (noFamiliaException ex) {
                return mapReturn;
            }
        }

        openSessionAndTransaction(); // No la abro al principio, pues al hacer el return en la exception noFamilia, no llegaría a cerrarla

        // Comprobamos que el billete NO ES TRANSIENT, basta con ver si tiene o no ID (si nunca se persistió, no se generó su ID)
        if (billete.getCodigo() == null) { // Como es un Long (objeto, no tipo primitivo) null y no 0
            throw new HibernateException("Transient ORM can´t be updated!"); // Uso HibernateException pq una propia para tema de la BD me parece feo
        }

        try { // HAY QUE EMPEZAR EL TRY-CATCH AQUÍ, SI NO NOS DEVOLVERÁ UN FALSE PERO SI HEMOS MODIFICADO ATRIBUTOS EN EL ORM BILLETE LOS MANTIENE!
            // Buscamos en el vuelo tantos asientos vacíos en la misma fila como pasajeros (lo suyo sería que la lista de asientos fuera ordenada por fila,
            // con una TreeList, pero para implementarlo de verdad tendría que meter un comparator y se hace mu largo. Nos lo imaginamos :) )
            // Cargamos los asientos en una matriz de 31*6 para poder acceder por índice
            int contadorAsiento = 1;
            int contadorFila = 1;
            List<Asiento> asientosLibres = new ArrayList<>();
            for (Asiento asientoLeido : billete.getVuelo().getAsientos()) {
                // Ir contando los asientos libres de cada fila
                if (contadorAsiento >= 6) { // Cambio de fila, reseteo de contadores de numAsiento y asientos libres
                    contadorAsiento = 1;
                    contadorFila++;
                    asientosLibres.clear();
                }

                // Dependerá del tipo de billete (bussines o no)
                if (billete.getPasajeros().get(0).getAsiento().isBussines() && contadorFila < 6 && asientoLeido.getPasajero() == null) {
                    // Esta libre y es bussines
                    asientosLibres.add(asientoLeido);
                } else if (!billete.getPasajeros().get(0).getAsiento().isBussines() && contadorFila >= 6 && asientoLeido.getPasajero() == null) {
                    // Esta libre y no es bussines
                    asientosLibres.add(asientoLeido);
                }
                contadorAsiento++;
                // SI SE ENCONTRÓ UNA FILA CON SUFICIENTES ASIENTOS
                // Cargamos a cada uno de los pasajeros uno de los nuevos asientos encontrados
                int contador = 0;
                if (asientosLibres.size() >= billete.getPasajeros().size()) {
                    for (Pasajero pasajeroLeido : billete.getPasajeros()) {
                        pasajeroLeido.setAsiento(asientosLibres.get(contador));
                        contador++;
                    }
                }
            }
            // Actualizamos el cambio en la BD (debemos dejar vacíos los asientos que antes ocupaban! 
            // Lo debería hacer Hibernate automáticamente, porque especificamos que actualizara en cascada, si no habría que hacerlo
            // a manos almacenando en otra lista los asientos previos de la familia, buscándolos y eliminando su pasajero

            session.update(billete); // Update, porque trabajaríamos sobre un objeto detached, saveOrUpdate() sería mejor pero en la práctica daba muchos fallos :(
            transaction.commit();
            // Cargamos el map a retornar con el billete modificado y TRUE sólo después de haber actualizado con éxito
            mapReturn.put(Boolean.TRUE, billete);
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage() + "\nInit cause: " + he.initCause(he) + "\n");
        } finally {
            session.close();
        }

        return mapReturn;
    }

    /**
     * Calculará el precio final utilizando otro método de esta interface, los reasignará si son familia, la fecha
     * de emisión será la del momento de generar el billete
     * @param pasajeros
     * @param vuelo
     * @param cliente
     * @param precioPasajero
     * @param descuentoNiños
     * @return
     */
    @Override
    public Billete creaBillete(List<Pasajero> pasajeros, Vuelo vuelo, Cliente cliente, float precioPasajero, float descuentoNiños, boolean isFamilia) {
        openSessionAndTransaction();
        Billete billeteBasico = new Billete();
        Billete billeteFinal = new Billete();
        try {
            // Cargamos el ORM con los datos básicos antes de hacer comprobaciones o cálculos (estos van sobre la lista de pasajeros!)
            billeteBasico.setCliente(cliente);
            billeteBasico.setDescuentoNiños(descuentoNiños);
            billeteBasico.setFechaEmite(new Date()); // Fecha actual, la de creación
            billeteBasico.setPasajeros(pasajeros);
            billeteBasico.setPrecioPasajero(precioPasajero);
            billeteBasico.setVuelo(vuelo);
            // Cargamos los campos calculados
            Billete billetePrecio = calcularPrecioFinal(billeteBasico); // No sé si cascaría guardándolo en el mismo obj
            billeteFinal = reAsignarAsientosFamilia(billetePrecio).containsKey(Boolean.TRUE)
                    ? reAsignarAsientosFamilia(billetePrecio).get(Boolean.TRUE)
                    : reAsignarAsientosFamilia(billetePrecio).get(Boolean.FALSE);
            
            // Persistimos el billete (son save(), para generar ID)
            Long idGenerado = (Long) session.save(billeteFinal);
            billeteFinal.setCodigo(idGenerado);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage() + "\nInit cause: " + he.initCause(he) + "\n");
        } finally {
            session.close();
        }

        return billeteFinal;
    }

}
