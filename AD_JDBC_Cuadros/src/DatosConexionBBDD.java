

/**
 * clase que almacena los datos de una conexion equipo, bbdd, usr y pwd
 * @author rpe
 */
public class DatosConexionBBDD {
    private String equipo;
    private String bd;
    private String usr;
    private String pwd;

    // equipo = localhost
    // bd = XE
    // usr = nombre_usuario
    // pwd = contraseña
    public DatosConexionBBDD(String equipo, String bd,String usr, String pwd) {
        this.equipo = equipo;
        this.bd = bd;
        this.usr = usr;
        this.pwd = pwd;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "DatosConexionBBDD{" + "equipo=" + equipo + "BBDD=" + bd +", usr=" + usr + ", pwd=" + pwd + '}';
    }
    
}
