/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
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
public class Propietario implements Serializable {

    @Id // El ID lo estableceremos nosotros manualmente (persist(), no save()!)
    private String cif;
    private String nombre;
    private String apellidos;
    /*
    * PERSIST: Al grabar un nuevo propietario persistimos tb sus viviendas si las tuviera (tb serían nuevas en la BD, luego tb necesitarán ser persistidas)
    * DETACH: Al trabajar con un ORM Propietario fuera del contexto de persistencia, sacamos de dicho contexto tb sus viviendas para evitar que posibles errores sean persistidos
    * REMOVE: No borramos las viviendas al borrar el propietario, pues nos interesa mantener su información para tener los datos de nuestra actividad económica
    * UPDATE/MERGE: No actualizamos en cascada porque si queremos actualizar datos de una vivienda lo haremos directamente sobre el ORM vivienda. Si queremos insertar una nueva vivienda debemos hacerlo con save(ORMViviendaNueva)
    * (si hiciéramos un update en cascada, si hubiera una nueva vivienda en la lista intentaría ejecutar update(viviendaNueva) y darçia Exception: viviendaNueva será TRANSIENT y no es posible actualizarla.
    * De modo que sólo usaremos update para modificar datos básicos del propietario, para lo cual no necesitamos actualizar nada en cascada a las viviendas
    * FETCH: Descargamos las viviendas del propietario en el acto, pues no será una lista muy pesada y generalmente será la información que nos interese consultar de un propietario
    */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "propietario")
    private List<Vivienda> viviendas;

    public Propietario() {
    }

    public Propietario(String cif, String nombre, String apellidos) {
        this.cif = cif;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Propietario(String cif, String nombre, String apellidos, List<Vivienda> viviendas) {
        this.cif = cif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.viviendas = viviendas;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
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

    public List<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }

    
}
