/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOs;

/**
 *
 * @author aranx
 */
public class Cuadro {

    private int id_cuadro;
    private int id_autor;
    private String titulo;
    private int anio;

    public Cuadro(int id_cuadro, int id_autor, String titulo, int anio) {
        this.id_cuadro = id_cuadro;
        this.id_autor = id_autor;
        this.titulo = titulo;
        this.anio = anio;
    }

    public int getId_cuadro() {
        return id_cuadro;
    }

    public void setId_cuadro(int id_cuadro) {
        this.id_cuadro = id_cuadro;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Cuadro{" + "id_cuadro=" + id_cuadro + ", id_autor=" + id_autor + ", titulo=" + titulo + ", anio=" + anio + '}';
    }

}
