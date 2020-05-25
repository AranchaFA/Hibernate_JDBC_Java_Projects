
import VOs.Cuadro;
import VOs.VOPintor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DBQueries {

    ////////////////////////////
    //        ATRIBUTOS       //
    ////////////////////////////
    // Datos de la conexión
    private Connection conexion;
    // Tipo de ResultSet devuelto
    public static int tipoResultado = ResultSet.TYPE_SCROLL_SENSITIVE;
    public static int tipoActualizacion = ResultSet.CONCUR_UPDATABLE;

    // Constructor
    public DBQueries(DatosConexionBBDD datosConexionBBDD) {
        abrirConexionBBDD(datosConexionBBDD);
    }

    //////////////////////////////
    //      MÉTODOS QUERIES     //
    //////////////////////////////
    public List<VOPintor> infoPintores(boolean ordenadosPorNombre) {
        try {
            String sentenciaSQL = ordenadosPorNombre ? "SELECT * FROM Pintores ORDER BY nombre" : "SELECT * FROM Pintores";
            PreparedStatement pstmt = conexion.prepareStatement(sentenciaSQL, tipoResultado, tipoActualizacion);
            ResultSet resultSet = pstmt.executeQuery();
            List<VOPintor> listaPintores = new ArrayList();
            while (resultSet.next()) {
                VOPintor voPintor = generarUnVOPintor(resultSet);
                listaPintores.add(voPintor);
            }
            pstmt.close();
            return listaPintores;
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insertarPintor(VOPintor pintor) {
        try {
            if (!conexion.prepareStatement("SELECT * FROM Pintores WHERE id_pintor=" + pintor.getId_pintor(), tipoResultado, tipoResultado).executeQuery().next()) {
                PreparedStatement pstmt = conexion.prepareStatement("INSERT INTO Pintores VALUES(?,?,?,?)", tipoResultado, tipoActualizacion);
                pstmt.setInt(1, pintor.getId_pintor());
                pstmt.setString(2, pintor.getNombre());
                pstmt.setInt(3, pintor.getAnio_nacimiento());
                pstmt.setString(4, pintor.getEstilo());
                int numElementosInsertados = pstmt.executeUpdate();
                pstmt.close();
                return numElementosInsertados;
            }
            System.out.println("Ya existe un pintor con ese ID!");
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int eliminarPintor(String id_o_nombre, boolean isID) {
        PreparedStatement pstmt = null;
        int numFilasModificadas = 0;
        try {
            if (isID) {
                pstmt = conexion.prepareStatement("DELETE FROM Pintores WHERE id_pintor=?", tipoResultado, tipoActualizacion);
                pstmt.setInt(1, Integer.valueOf(id_o_nombre));
                numFilasModificadas = pstmt.executeUpdate();
                pstmt.close();
            } else {
                pstmt = conexion.prepareStatement("DELETE FROM Pintores WHERE UPPER(nombre)=UPPER(?)", tipoResultado, tipoActualizacion);
                pstmt.setString(1, id_o_nombre);
                numFilasModificadas = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numFilasModificadas;
    }

    public int eliminarPintoresPorAnio(int anhoNacimiento) {
        try {
            PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM Pintores WHERE anio_nacimiento=" + anhoNacimiento, tipoResultado, tipoActualizacion);
            int numRegistrosEliminados = pstmt.executeUpdate();
            pstmt.close();
            return numRegistrosEliminados;
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public VOPintor generarUnVOPintor(ResultSet resultSet) {
        VOPintor voPintor = null;
        try {
            int id_pintor = resultSet.getInt("id_pintor");
            String nombre = resultSet.getString("nombre");
            int anio_nacimiento = resultSet.getInt("anio_nacimiento");
            String estilo = resultSet.getString("estilo");

            voPintor = new VOPintor(id_pintor, nombre, anio_nacimiento, estilo);
        } catch (SQLException ex) {
            System.out.println("Error al crear un socio. " + ex.getMessage());
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voPintor;
    }

    public Map<Cuadro, String> cuadrosPorAutor(boolean ordenadoPorTitulo) {
        Map<Cuadro, String> mapCuadros = new HashMap<>();
        try {
            PreparedStatement pstmt = ordenadoPorTitulo
                    ? conexion.prepareStatement("SELECT c.*,p.nombre FROM Cuadros c LEFT JOIN Pintores p ON c.id_autor=p.id_pintor ORDER BY c.titulo", tipoResultado, tipoResultado)
                    : conexion.prepareStatement("SELECT c.*,p.nombre FROM Cuadros c LEFT JOIN Pintores p ON c.id_autor=p.id_pintor", tipoResultado, tipoResultado);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Cuadro cuadro = new Cuadro(resultSet.getInt("id_cuadro"), resultSet.getInt("id_autor"), resultSet.getString("titulo"), resultSet.getInt("anio"));
                mapCuadros.put(cuadro, resultSet.getString("nombre"));
            }
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapCuadros;
    }

    public List<Cuadro> cuadrosDeUnAutor(String nombrePintor) {
        List<Cuadro> listaCuadros = new ArrayList<>();
        try {
            PreparedStatement pstmt = conexion.prepareStatement("SELECT c.* FROM Cuadros c LEFT JOIN Pintores p ON c.id_autor=p.id_pintor WHERE UPPER(p.nombre)=UPPER(?)", tipoResultado, tipoResultado);
            pstmt.setString(1, nombrePintor);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Cuadro cuadro = new Cuadro(resultSet.getInt("id_cuadro"), resultSet.getInt("id_autor"), resultSet.getString("titulo"), resultSet.getInt("anio"));
                listaCuadros.add(cuadro);
            }
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCuadros;
    }

    public Map<Integer, Map<String,String>> infoComentariosRecientes(int numDiasAMostrar) {
        Map<Integer, Map<String,String>> mapInfoComentarios = new HashMap<>();
        try {
            // FALTA FILTRAR POR FECHA
            PreparedStatement pstmt = conexion.prepareStatement("SELECT co.id_comentario,co.fecha,p.nombre AS pintor,u.nombre AS usuario,cu.titulo FROM Comentarios co"
                    + " INNER JOIN Cuadros cu ON co.id_cuadro=cu.id_cuadro"
                    + " INNER JOIN Pintores p ON cu.id_autor=p.id_pintor"
                    + " INNER JOIN Usuarios u ON u.id_usuario=co.id_usuario", tipoResultado, tipoActualizacion);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Map<String,String> infoComentario=new HashMap<>();
                //infoComentario.put("fecha", resultSet.getDate("fecha").toString()); // Da problemas al seleccionar el tipo de dato en workbench para las fechas :(
                infoComentario.put("usuario", resultSet.getString("usuario"));
                infoComentario.put("pintor", resultSet.getString("pintor"));
                infoComentario.put("titulo", resultSet.getString("titulo"));
                mapInfoComentarios.put(resultSet.getInt("id_comentario"), infoComentario);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapInfoComentarios;
    }

    /////////////////////////////
    //    MÉTODOS AUXILIARES   //
    /////////////////////////////
    public boolean abrirConexionBBDD(DatosConexionBBDD datosConexionBBDD) {
        boolean exito = false;
        // Cargamos el driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error con el driver,comprueba que importado la librería en el proyecto y que es la correcta.");
        }
        // Definimos la URL que se usará para la conexión (1521 para oracle)
        // Equipo = nuestra IP de MySQL
        // getBD = nombre nuestra BD
        String url = "jdbc:mysql://" + datosConexionBBDD.getEquipo() + "/" + datosConexionBBDD.getBd() + "?user=" + datosConexionBBDD.getUsr() + "&password=" + datosConexionBBDD.getPwd();
        System.out.println(url);
        try {
            // Creamos la conexión
            conexion = DriverManager.getConnection(url, datosConexionBBDD.getUsr(), datosConexionBBDD.getPwd());
            if (conexion != null) {
                System.out.println("Conexión establecida con exito");
                exito = true;
            }
        } catch (SQLException ex) {
            System.out.println("Fallo en la conexion; verificar BBDD, nombre equipo, usuario o contraseña");
            System.out.println("Error al conectar con la BBDD, compruebe si está levantada");
            exito = false;
        }
        return exito;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cerrar la conexion a la BBDD");
        }
    }

}
