
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Aeropuerto;
import pojos.Asiento;
import pojos.Billete;
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
public class LogicaVueloDAOImplementation implements ILogicaVueloDAO {

    private Session session;
    private Transaction transaction;

    // MÉTODOS AUXILIARES
    public void openSessionAndTransaction() {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }

    /**
     * Codigo: Se autogenera AL PERSISTIR (luego hay qeu asignárselo al ORM a devolver)
     * Asientos: Los genero
     * Billetes: Aún no existirá ninguno (no están vendidos), lista vacía
     * @param origen
     * @param destino
     * @param fechaVuelo
     * @return vuelo generado
     */
    @Override
    public Vuelo creaVuelo737(Aeropuerto origen, Aeropuerto destino, Date fechaVuelo) {
        // Podríamos dar exception si alguno de los aeropuertos está ocupado en la fecha (si se pasara con hora, es muy lioso pero la idea está ahí)
        Vuelo vueloGenerado = null;

        try {
            // Cargamos datos básicos y campos que sólo se inicializan pero originalmente están vacíos
            vueloGenerado = new Vuelo(origen, destino, fechaVuelo);
            vueloGenerado.setBilletes(new ArrayList<Billete>());

            // Generar los asientos
            vueloGenerado.setAsientos(new ArrayList<Asiento>());
            for (int i = 1; i <= 31; i++) {
                for (int j = 1; j <= 6; j++) {
                    Asiento asientoGenerado = new Asiento();
                    // Asignamos fila i
                    asientoGenerado.setFila(i);
                    // Generamos letra
                    switch (j) {
                        case 1:
                            asientoGenerado.setLetra("A");
                            break;
                        case 2:
                            asientoGenerado.setLetra("B");
                            break;
                        case 3:
                            asientoGenerado.setLetra("C");
                            break;
                        case 4:
                            asientoGenerado.setLetra("D");
                            break;
                        case 5:
                            asientoGenerado.setLetra("E");
                            break;
                        case 6:
                            asientoGenerado.setLetra("F");
                            break;
                        default:
                            throw new AssertionError();
                    }
                    // Asignamos bussines
                    if (i <= 5) { // Bussines
                        asientoGenerado.setBussines(true);
                    } else {
                        asientoGenerado.setBussines(false);
                    }
                    // Asignamos el vuelo al asiento! Pues se inicializaron con vuelo=null
                    // Esto se podría poner en el seter del vuelo ¿? para que cargue el atrib vuelo a una lista de asientos que le sea pasada
                    // (en el constructor daría exception, pq no podemos hacer asiento.set(this) y pasar el propio vuelo que aún NO ha terminado de ser creado
                    asientoGenerado.setVuelo(vueloGenerado);
                    // No hace falta persistir el asiento pq lo hace en cascada, necesitaríamos sacar el ID nosotros para devolver el vuelo
                    // con los asientos con ID? O Hibernate "guardaría" en los ORM generados su ID? (a mí no me funcionaba bien)
                    vueloGenerado.getAsientos().add(asientoGenerado);
                }
            }
            
            // Persistimos el vuelo (con save() para generar ID)
            Long idGenerado = (Long) session.save(vueloGenerado);
            transaction.commit();
            vueloGenerado.setCodigo(idGenerado);
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage() + "\nInit cause: " + he.initCause(he) + "\n");
        } finally {
            session.close();
        }

        return vueloGenerado;
    }

}
