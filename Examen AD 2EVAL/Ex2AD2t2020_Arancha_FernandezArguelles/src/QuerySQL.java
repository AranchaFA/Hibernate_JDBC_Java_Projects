
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aranx
 */
public class QuerySQL {
    
    // ATRIBUTOS       

    private Connection conexion;

    /**
     * Variable para establecer las características de los ResultSet: 
     * scrollable y sensible a los cambios en los campos cuyos datos se extraen en la query y 'cargan' el ResultSet
     */
    public static int tipoResultado = ResultSet.TYPE_SCROLL_SENSITIVE;

    /**
     * Variable para establecer las características de los ResultSet: 
     * actualizable (permite actualizar o insertar valores de columna y eliminar filas)
     */
    public static int tipoActualizacion = ResultSet.CONCUR_UPDATABLE;

    /**
     * Instanciar un objeto de la clase, se establece la conexión a la BBDD
     * @param datosConexionBBDD objeto que encapsula los datos de la conexión a la BD (host, nombre BD, user y password)
     */
    public QuerySQL(DatosConexionBBDD datosConexionBBDD) {
        abrirConexionBBDD(datosConexionBBDD);
    }
    

    // MÉTODOS AUXILIARES   

    /**
     * Abrir conexión con los datos encapsulados en datosConexionBBDD
     * @param datosConexionBBDD
     * @return
     */
    public boolean abrirConexionBBDD(DatosConexionBBDD datosConexionBBDD) {
        // Cargamos el driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuerySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error con el driver, comprueba que has importado la librería correcta en el proyecto.");
        }
        // Definimos la URL que se usará para la conexión: los datos se cargarán siempre desde un obj DatosConexionBBDD
        // getEquipo = nuestra IP de MySQL
        // getBD = nombre nuestra BD
        String url = "jdbc:mysql://" + datosConexionBBDD.getEquipo() + "/" + datosConexionBBDD.getBd() + "?user=" + datosConexionBBDD.getUsr() + "&password=" + datosConexionBBDD.getPwd();
        System.out.println(url);
        try {
            // Creamos la conexión
            conexion = DriverManager.getConnection(url, datosConexionBBDD.getUsr(), datosConexionBBDD.getPwd());
            if (conexion != null) {
                System.out.println("Conexión establecida con exito");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Fallo en la conexión; verificar BBDD, nombre equipo, usuario o contraseña. Compruebe si la BD está levantada");
            return false;
        }
        return false;
    }

    /**
     * Cerrar conexión
     */
    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cerrar la conexion a la BBDD");
        }
    }

    
    // MÉTODO EXAMEN

    /**
     *
     * @param codigoVuelo 
     * @return número de asientos libres para el vuelo cuyo código pasamos por parámetro
     */   
    public int asientosLibres(Long codigoVuelo){
        int asientosLibres=0;
        try {
            ResultSet rsExiste = conexion.prepareStatement("SELECT COUNT(*) FROM Vuelo WHERE codigo="+codigoVuelo, tipoResultado, tipoActualizacion).executeQuery();
            rsExiste.next();
            int numVuelos = rsExiste.getInt(1);
            if (numVuelos==0) {
                // LANZAR EXCEPTION PROPIA PQ NO EXISTE (sin tiempo :( )
            }
            // String con la query a la BD
            String sentenciaSQL = "SELECT COUNT(a.*) FROM Vuelo v, Asiento a WHERE v.codigo="+codigoVuelo+" AND a.dni_pasajero IS NULL";
            // Generamos la PreparedStatement y la ejecutamos: obtenemos un ResultSet
            PreparedStatement pstmt = conexion.prepareStatement(sentenciaSQL, tipoResultado, tipoActualizacion);
            ResultSet resultSet = pstmt.executeQuery();
            // Es un result set de una sóla "celda" (un valor numérico)
            resultSet.next();
            asientosLibres = resultSet.getInt(1);
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLException.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asientosLibres;
    }
    
}
