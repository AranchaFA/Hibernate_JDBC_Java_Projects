/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aranx
 */
@Entity
public class Urbanizacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Valor número autogenerado en orden 1,2,3,4,...
    private int id;
    private String nombre;
    private int cp;

    public Urbanizacion() {
    }

    public Urbanizacion(String nombre, int cp) {
        this.nombre = nombre;
        this.cp = cp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

}
