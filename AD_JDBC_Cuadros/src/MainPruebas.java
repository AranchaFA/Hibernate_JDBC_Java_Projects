
import VOs.VOPintor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public class MainPruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DatosConexionBBDD datosConexionBBDD = new DatosConexionBBDD("104.198.164.218:3306", "mydb", "root", "root");
        DBQueries dBQueries = new DBQueries(datosConexionBBDD);
        VOPintor pintorDePrueba = new VOPintor(4, "Leela", 1970, "Pandereta");
        System.out.println("Pintores insertados: " + dBQueries.insertarPintor(pintorDePrueba));
        Vista.Vista.visualizarListaPintores(dBQueries.infoPintores(true));
        System.out.println("Pintores eliminados: " + dBQueries.eliminarPintor("Doraemon", false));
        Vista.Vista.visualizarListaPintores(dBQueries.infoPintores(true));
        System.out.println("Pintores eliminados por año: "+dBQueries.eliminarPintoresPorAnio(1970));
        Vista.Vista.visualizarListaPintores(dBQueries.infoPintores(true));
        
        Vista.Vista.visualizarMapCuadrosPorAutor(dBQueries.cuadrosPorAutor(true));
        Vista.Vista.visualizarListaCuadros(dBQueries.cuadrosDeUnAutor("Ataulfa")); // No es sensitive a las tildes
        Vista.Vista.visualizarInfoComentarios(dBQueries.infoComentariosRecientes(0));
        dBQueries.cerrarConexion();
        
        
        
    }

}
