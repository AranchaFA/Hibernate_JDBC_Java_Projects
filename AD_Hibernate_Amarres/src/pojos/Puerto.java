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
//@Table no hará falta: todas las tablas se llamarán por defecto como los objetos que representan a las entidades
public class Puerto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Todos los IDs se generarán en secuencia numérica: 1,2,3,...
    private int id;
    private String nombre;
    private float longitud;
    private float latitud;

    public Puerto() {
    }

    public Puerto(String nombre, float longitud, float latitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
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

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    @Override
    public String toString() {
        return "Puerto{" + "id=" + id + ", nombre=" + nombre + ", longitud=" + longitud + ", latitud=" + latitud + '}';
    }

}
