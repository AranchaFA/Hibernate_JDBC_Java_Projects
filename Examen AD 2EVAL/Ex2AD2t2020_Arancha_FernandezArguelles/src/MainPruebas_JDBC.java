

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public class MainPruebas_JDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Creamos una conexión a la BBDD con los datos encapsulados en el objeto datosConexionBBDD
        DatosConexionBBDD datosConexionBBDD = new DatosConexionBBDD("localhost:3306", "examenAD", "root", "@sturiaS2020");
        QuerySQL querySQL = new QuerySQL(datosConexionBBDD);
        
        // Probaríamos la ejecutión del método y visualizamos el resultado:
        System.out.println("Número de asientos libres: "+querySQL.asientosLibres(Long.valueOf(1234)));
        
        // Cerramos la conexión a la BBDD (en los métodos JDBC se cierran las Statement, pero no la conexión
        // pues se recibe por parámetro y es la misma durante toda la ejecución del Main)
        querySQL.cerrarConexion();
        
        
        
    }

}
