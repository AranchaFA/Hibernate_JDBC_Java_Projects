
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aranx
 */
class Comentario {
    
    private int id_comentario;
    private int id_autor;
    private int id_cuadro;
    private Date fecha;

    public Comentario(int id_comentario, int id_autor, int id_cuadro, Date fecha) {
        this.id_comentario = id_comentario;
        this.id_autor = id_autor;
        this.id_cuadro = id_cuadro;
        this.fecha = fecha;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_cuadro() {
        return id_cuadro;
    }

    public void setId_cuadro(int id_cuadro) {
        this.id_cuadro = id_cuadro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id_comentario=" + id_comentario + ", id_autor=" + id_autor + ", id_cuadro=" + id_cuadro + ", fecha=" + fecha + '}';
    }
    
    
    
}
