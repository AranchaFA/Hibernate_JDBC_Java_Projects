
import java.util.ArrayList;
import java.util.List;
import mydb.Pintor;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public class DAO_cuadros implements IDAO_cuadros {

    /**
     * SELECT -> query.uniqueResult();
     *
     *
     * Transaction transaction = session.beginTransaction();
     */
    /*
    private void MANEJA_EXCEPTION(HibernateException he) throws HibernateException {
    transaccion.rollback();
    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
     */
    // Los campos en las consultas se deben nombrar como los ATRIBUTOS de las clases java anotadas, no como los campos de la BD
    private static final String QUERY_BUSCAR_PINTOR = "from Pintor where idPintor=idPintorBuscado"; // replace() idPintorBuscado
    private static final String QUERY_PINTOR_DE_UN_CUADRO = "from Pintor p where p.idPintor=(select c.pintor from Cuadro c where c.idCuadro=idCuadroBuscado)"; // replace() idCuadroBuscado
    private static final String QUERY_TODOS_LOS_PINTORES = "from Pintor";

    /*  // No está bien la sintaxis de inserción
    private static final String QUERY_INSERTAR_PINTOR = "insert into Pintor (nombre,anioNacimiento,estilo) 'nuevoNombre',nuevoAnioNacimiento,'nuevoEstilo'"; // replace() valores a insertar
    private static final String QUERY_ACTUALIZAR_PINTOR = "update Pintor set nombre='nuevoNombre' estilo='nuevoEstilo' anioNacimiento='nuevoAnioNacimiento' where idPintor = "; // replace() valores a insertar
    private static final String QUERY_ELIMINAR_PINTOR = "delete Pintor where idPintor =idPintorBuscado"; // replace() idPintorBuscado*/
    @Override
    public Pintor buscarPintorHQL(int id_pintor) {
        Pintor pintorEncontrado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Transaction transaction = session.beginTransaction(); // No necesitamos que sea una transacción
        try {
            String hqlSelect = QUERY_BUSCAR_PINTOR.replace("idPintorBuscado", String.valueOf(id_pintor));
            pintorEncontrado = (Pintor) session.createQuery(hqlSelect).uniqueResult();
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return pintorEncontrado;
    }

    @Override
    public Pintor buscarPintor(int id_pintor) {
        Pintor pintorEncontrado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Transaction transaction = session.beginTransaction(); // No necesitamos que sea una transacción
        try {
            pintorEncontrado = (Pintor) session.get(Pintor.class, id_pintor);
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return pintorEncontrado;
    }

    @Override // Este da org.hibernate.LazyInitializationException: could not initialize proxy - no Session
    public Pintor buscarPintor2(int id_pintor) {
        Pintor pintorEncontrado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Transaction transaction = session.beginTransaction(); // No necesitamos que sea una transacción
        try {
            pintorEncontrado = (Pintor) session.load(Pintor.class, id_pintor);
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally { 
            session.close();            
        }
        return pintorEncontrado;
    }

    @Override
    public Pintor pintorDeUnCuadroHQL(int id_cuadro) {
        Pintor pintorEncontrado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hqlSelect = QUERY_PINTOR_DE_UN_CUADRO.replace("idCuadroBuscado", String.valueOf(id_cuadro));
            pintorEncontrado = (Pintor) session.createQuery(hqlSelect).uniqueResult();
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return pintorEncontrado;
    }

    @Override
    public List<Pintor> todosLosPintoresHQL() {
        List<Pintor> pintores = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            pintores = (List<Pintor>) session.createQuery(QUERY_TODOS_LOS_PINTORES).list();
            // Estamos leyendo, no hay necesidad de hacer commit()
        } catch (HibernateException he) {
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return pintores;
    }

    @Override
    public int insertarPintor(Pintor pintor) {
        int idPintorInsertado = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            idPintorInsertado = (int) session.save(pintor);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return idPintorInsertado;
    }

    @Override
    public Pintor eliminarPintor(int id_pintor) {
        Pintor pintorEliminado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            pintorEliminado = buscarPintorHQL(id_pintor);
            session.delete(pintorEliminado);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return pintorEliminado;
    }

    @Override
    public Pintor modificarPintor(Pintor pintor) {
        Pintor pintorModificado = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            pintorModificado = buscarPintorHQL(pintor.getIdPintor());
            session.update(pintor);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            System.out.println("Ocurrió un error en la capa de acceso a datos\n" + he.getMessage());
        } finally {
            session.close();
        }
        return pintorModificado;
    }

}
