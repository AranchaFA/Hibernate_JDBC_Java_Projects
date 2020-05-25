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
public class VOPintor {
    
    private int id_pintor;
    private String nombre;
    private int anio_nacimiento;
    private String estilo;

    public VOPintor(int id_pintor, String nombre, int anio_nacimiento, String estilo) {
        this.id_pintor = id_pintor;
        this.nombre = nombre;
        this.anio_nacimiento = anio_nacimiento;
        this.estilo = estilo;
    }

    public int getId_pintor() {
        return id_pintor;
    }

    public void setId_pintor(int id_pintor) {
        this.id_pintor = id_pintor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio_nacimiento() {
        return anio_nacimiento;
    }

    public void setAnio_nacimiento(int anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public String toString() {
        return "VOPintor{" + "id_pintor=" + id_pintor + ", nombre=" + nombre + ", anio_nacimiento=" + anio_nacimiento + ", estilo=" + estilo + '}';
    }
    
    
    
}
