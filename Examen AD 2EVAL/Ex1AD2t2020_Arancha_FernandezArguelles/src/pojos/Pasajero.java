/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author aranx
 */
@Entity
public class Pasajero implements Serializable {

    @Id // No se autogenera, se identifica por el DNI (deberemos persistir con persist(), no con save()!)
    // To hubiera puesto en pasajero un ID aleatorio y en cliente el DNI¿?
    private String dni;
    /*
    CASCADE: Los procesos que intervengan en una actualización del pasajero (incluido detach, para trabajar con
    el ORM fuera del contexto de persistencia y evitar errores) se ejecutan en cascada. Remove no, pues en un billete
    podemos suprimir a uno de sus pasajeros pero seguirá vigente.
    */
    @ManyToOne (cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Billete billete;
    /*
    CASCADE: Los procesos que intervengan en una actualización del pasajero (incluido detach, para trabajar con
    el ORM fuera del contexto de persistencia y evitar errores) se ejecutan en cascada. Remove no, pues una plaza de
    un vuelo no deja de existir porque deje de ser ocupada por un determinado pasajero.
    No deberíamos eliminar en cascada, pero daría error en la BD pq en las tablas ambas PK serán FK de la otra entidad!
    No persistimos en cascada, pq tanto el asiento debería haber sido persistido antes (para poder ser ofertado, tiene que estar ya en la BD),
    luego ambos ORM se persisten en momentos diferentes y podría dar error (si se usa save() o persist(), en lugar de saveOrUpdate())
    */
    @OneToOne (cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Asiento asiento;
    private String nombre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    public Pasajero() {
    }

    /*
    Los constructores SÍ deben recibir en DNI por parámetro, pues será el ID del ORM
    */
    public Pasajero(String dni, String nombre, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pasajero(String dni, Billete billete, Asiento asiento, String nombre, Date fechaNacimiento) {
        this.dni = dni;
        this.billete = billete;
        this.asiento = asiento;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Billete getBillete() {
        return billete;
    }

    public void setBillete(Billete billete) {
        this.billete = billete;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
    
    

}
