package Paquete;

import Prueba.UsuariosDAO;
import java.util.List;
import src.Usuarios;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isaac
 */
public class PruebaMainUsuarios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        Usuarios usuarioRecuperado = null;
        int idAEliminar = 1;
        
        Usuarios usuario1 = new Usuarios(1, "Isaac");
        Usuarios usuario2 = new Usuarios(2, "Cayetano");
        Usuarios usuario3 = new Usuarios(3, "Alfredito");
        
        //idAEliminar = (int) usuariosDAO.guardaUsuario(usuario1);
        //usuariosDAO.guardaUsuario(usuario2);
        //usuariosDAO.guardaUsuario(usuario3);
        
        usuario2.setNombre("Cayetana");
        usuariosDAO.actualizaUsuario(usuario2);
        
        usuarioRecuperado = usuariosDAO.obtenUsuarios(idAEliminar);
        System.out.println("Recuperamos a " + usuarioRecuperado.getNombre());
        
        usuariosDAO.eliminaUsuario(usuarioRecuperado);
        
        List<Usuarios> listaUsuarios = usuariosDAO.obtenListaUsuarios();
        System.out.println("Hay " + listaUsuarios.size() + " en la base de datos");
        
        for (Usuarios usuario : listaUsuarios) {
            System.out.println("-->" + usuario.getNombre());
        }
    }
    
}
