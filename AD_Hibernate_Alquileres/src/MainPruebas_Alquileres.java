
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public class MainPruebas_Alquileres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //AlquilerDAOImplementation dao = new AlquilerDAOImplementation();

        Date fechaInicio = new Date();
        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
        gc.add(Calendar.DATE, 7);
        Date fechaFin = gc.getTime();

        Arrendatario arrendatario = new Arrendatario("12345678A", "Aran", "Argu");
        session.persist(arrendatario);
        Propietario propietario = new Propietario("11112222Z", "Pepa", "Pepítez");
        session.persist(propietario);
        
        // La urbanización debe estar persistida ANTES de grabar la vivienda
        Urbanizacion urbanizacion = new Urbanizacion("CerroAlberche", 42554);
        int idUrbanizacion = (int) session.save(urbanizacion);
        urbanizacion.setId(idUrbanizacion);

        // La vivienda debe estar persistida ANTES de grabar el alquiler
        Vivienda vivienda = new Vivienda(1, "A", propietario, urbanizacion);
        int idVivienda = (int) session.save(vivienda);
        vivienda.setId(idVivienda);

        Alquiler alquiler = new Alquiler(fechaInicio, fechaFin, 300, vivienda, arrendatario);

        session.save(alquiler);
        
        transaction.commit();

        //dao.nuevoAlquiler(arrendatario, vivienda, fechaInicio, fechaFin, 300);
        //System.out.println(dao.listaAlquileres());
        
        session.close();
    }

}
