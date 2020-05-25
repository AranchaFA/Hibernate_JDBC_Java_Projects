
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
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
public class PropietarioDAOImplementation implements IPropietarioDAO {

    private Session session;
    private Transaction transaction;

    @Override
    public List<Propietario> listaPropietarios() {
        openSessionAndTransaction();
        List<Propietario> propietarios = new ArrayList<>();
        try {
            propietarios = (List<Propietario>) session.createQuery("from Propietario").list();
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return propietarios;
    }

    @Override
    public Propietario getPropietario(String dni) {
        openSessionAndTransaction();
        Propietario propietario = null;
        try {
            propietario = (Propietario) session.get(Propietario.class, dni);
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return propietario;
    }

    @Override
    public Propietario deletePropietario(String dni) {
        Propietario propietarioEliminado = null;
        openSessionAndTransaction();
        try {
            propietarioEliminado = (Propietario) session.get(Propietario.class, dni);
            if (propietarioEliminado != null) {
                session.delete(propietarioEliminado);
            }
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return propietarioEliminado;
    }

    @Override
    public boolean persistPropietario(Propietario propietario) {
        boolean success = false;
        openSessionAndTransaction();
        try {
            // persist() porque el DNI (ID) lo habremos asignado nosotros al obj. Propietario!
            // save() generaría un ID (DNI), y el DNI es un dato determinado desde el exterior!
            session.persist(propietario);
            transaction.commit();
            success = true;
        } catch (ConstraintViolationException cve) {
            transaction.rollback();
            System.out.println("Ya existe un registro con esa PK DNI\n");
        } catch (PersistenceException pe) { // persist() lanza PersistenceException al aplicarlo sobre un obj. detached en lugar de transient
            transaction.rollback();
            System.out.println("No se puede persistir una instancia detached!\n" + pe.getMessage() + "\n" + pe.getCause());
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage() + "\n" + he.initCause(he));
        } finally {
            session.close();
        }
        return success;
    }

    // Tendremos que pasar la instancia de Propietario descargada de la BD previamente (get() o load()) si queremos
    // actualizar, 
    @Override
    public int saveOrUpdatePropietario(Propietario propietario) {
        Logger logger = Logger.getLogger("");
        ConsoleHandler consoleHandler=new ConsoleHandler();
        logger.addHandler(consoleHandler);
        
        openSessionAndTransaction();
        Propietario propietarioEncontrado = (Propietario) session.get(Propietario.class, propietario.getDni());
        int numNewPropietarios = propietarioEncontrado == null ? 1 : 0;
        // Si descargamos de la BD el propietario existente, tenemos que desvincularlo del contexto de persistencia
        // antes de guardar el pasado por parámetro, si no da EXCEPTION porque encuentra dos instancias con el mismo DNI
        if (propietarioEncontrado != null) {
            session.evict(propietarioEncontrado);
            propietarioEncontrado = null;
        }
        try {
            //session.merge(propietario);
            //session.update(propietario);
            session.saveOrUpdate(propietario);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            numNewPropietarios = -1;
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage()+"\n"+he.initCause(he));
            Logger.getLogger(pojos.Propietario.class.getName()).log(Level.SEVERE, "LOG MESSAGE");
        } finally {
            session.close();
        }
        return numNewPropietarios;
    }

    // MÉTODOS AUXILIARES
    public void openSessionAndTransaction() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }

}
