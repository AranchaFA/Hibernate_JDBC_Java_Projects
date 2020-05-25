/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author aranx
 */
@Entity
public class Arrendatario implements Serializable {

    @Id // El ID lo estableceremos nosotros manualmente (persist(), no save()!)
    private String dni;
    private String nombre;
    private String apellidos;
    /*
    * PERSIST: Al grabar un nuevo arrendatario persistimos tb sus alquileres si tuviera (tb serían nuevos en la BD, luego tb necesitarán ser persistidos)
    * DETACH: Al trabajar con un ORM Arrendatario fuera del contexto de persistencia, sacamos de dicho contexto tb sus alquileres para evitar que posibles errores sean persistidos
    * REMOVE: No borramos los alquileres al borrar el arrendatario, pues nos interesa mantener los datos de nuestra actividad económica
    * UPDATE/MERGE: No actualizamos en cascada porque si queremos actualizar datos de un alquiler lo haremos directamente sobre su ORM. Si queremos insertar una nuevo alquiler debemos hacerlo con save(ORMNuevoAlquiler)
    * (si hiciéramos un update en cascada, si hubiera un nuevo alquiler en la lista intentaría ejecutar update(nuevoAlquiler) y daría Exception: viviendaNueva será TRANSIENT y no es posible actualizarla.
    * De modo que sólo usaremos update para modificar datos básicos del arrendatario, para lo cual no necesitamos actualizar nada en cascada a los alquileres
    * FETCH: Descargamos los alquileres del arrendatario en el acto, pues no será una lista muy pesada y generalmente será la información que nos interese consultar de un arrendatario
    */
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "arrendatario")
    private List<Alquiler> alquileres;

    public Arrendatario() {
    }

    public Arrendatario(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        alquileres=new ArrayList<>();
    }

    public Arrendatario(String dni, String nombre, String apellidos, List<Alquiler> alquileres) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.alquileres = alquileres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }
    
    
    
}
