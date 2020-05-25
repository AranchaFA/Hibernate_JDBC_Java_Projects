/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import src.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import src.Usuarios;

/**
 *
 * @author isaac
 */
public class UsuariosDAO {

    private Session sesion;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public int guardaUsuario(Usuarios usuario) {
        int id = 0;

        try {
            iniciaOperacion();
            id = (int) sesion.save(usuario);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
        return id;
    }

    public void actualizaUsuario(Usuarios usuarios) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(usuarios);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void eliminaUsuario(Usuarios usuarios) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(usuarios);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public Usuarios obtenUsuarios(int idUsuario) throws HibernateException {
        Usuarios usuarios = null;

        try {
            iniciaOperacion();
            usuarios = (Usuarios) sesion.get(Usuarios.class, idUsuario);
        } finally {
            sesion.close();
        }
        return usuarios;
    }

    public List<Usuarios> obtenListaUsuarios() throws HibernateException {
        List<Usuarios> listaUsuarios = null;

        try {
            iniciaOperacion();
            listaUsuarios = sesion.createQuery("FROM Usuarios").list();
        } finally {
            sesion.close();
        }

        return listaUsuarios;
    }
}
