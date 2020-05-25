
import exceptions.ViviendaOcupadaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Alquiler;
import pojos.Arrendatario;
import pojos.Vivienda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public class AlquilerDAOImplementation {

    private Session session;
    private Transaction transaction;

    // MÉTODOS AUXILIARES
    public void openSessionAndTransaction() {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }

    public List<Alquiler> listaAlquileres() {
        openSessionAndTransaction();
        List<Alquiler> alquileres = new ArrayList<>();
        try {
            alquileres = (List<Alquiler>) session.createQuery("from Alquiler").list();
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage() + "\nInit cause: " + he.initCause(he) + "\n");
        } finally {
            session.close();
        }
        return alquileres;
    }

    public Alquiler grabarAlquiler(Alquiler alquiler) {
        Alquiler alquilerGrabado = null;
        openSessionAndTransaction();
        try {
            int idGenerado = (int) session.save(alquiler);
            alquiler.setId(idGenerado);
            alquilerGrabado = alquiler;
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage());
        } finally {
            session.close();
        }
        return alquilerGrabado;
    }


    /**
     * Vivienda deberá ser un ORM detached que hayamos obtenido de nuestra BD,
     * Arrendatario podrá ser un nuevo cliente porque se persiste en cascada con
     * el alquiler
     *
     * @param arrendatario
     * @param vivienda
     * @param fechaInicio
     * @param fechaFin
     * @param precio
     * @return KEY: Resultado exitoso o no de la operación (TRUE si estaba libre
     * y se generó la reserva, FALSE si estaba reservado en esas fechas) VALUE:
     * Lista con el alquiler generado si es true, y con los alquileres que
     * impiden la reserva si es false (puede haber más de uno).
     */
    public Map<Boolean, List<Alquiler>> nuevoAlquiler(Arrendatario arrendatario, Vivienda vivienda, Date fechaInicio, Date fechaFin, float precio) {
        openSessionAndTransaction();
        Map<Boolean, List<Alquiler>> mapAlquileres = new HashMap<>();
        List<Alquiler> listaAlquileres = new ArrayList<>();
        try {
            // COMPROBAR QUE NO ESTÁ OCUPADA ESAS FECHAS
            // Listamos los alquileres de esa vivienda que se solapen con esa fecha
            String stringQuery = "from alquiler a, alquiler_vivienda av "
                    + "where a.id=av.idAlquiler"
                    + "and av.idVivienda='" + vivienda.getId() + "'"
                    + "and a.fechaFin>=" + fechaInicio + " and fechaInicio<=" + fechaFin;
            listaAlquileres = (List<Alquiler>) session.createQuery(stringQuery).list();

            // Si se encontraron alquileres para ese período lanzamos exception
            if (listaAlquileres.size() > 0) {
                // Añadimos la lista al map (FALSE)
                mapAlquileres.put(Boolean.FALSE, listaAlquileres);
                throw new exceptions.ViviendaOcupadaException();
            } else { // SI NO ESTÁ OCUPADA: REGISTRAMOS EL ALQUILER (TRUE)
                Alquiler alquilerGenerado = new Alquiler(fechaInicio, fechaFin, precio, vivienda, arrendatario);
                int idGenerado = (int) session.save(alquilerGenerado); // Se autogenera el ID
                alquilerGenerado.setId(idGenerado); // Debemos devolver el ORM con el ID generado
                listaAlquileres.add(alquilerGenerado);
                // Añadimos la lista con el nuevo alquiler al map (TRUE)
                mapAlquileres.put(Boolean.TRUE, listaAlquileres);
            }
        } catch (ViviendaOcupadaException ex) {
            Logger.getLogger(AlquilerDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Error en la capa de acceso a datos\nException message: " + he.getMessage() + "\nInit cause: " + he.initCause(he) + "\n");
        } finally {
            session.close();
        }
        return mapAlquileres;
    }
}
