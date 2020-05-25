/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aranx
 */
@Entity
//@Table no hará falta: todas las tablas se llamarán por defecto como los objetos que representan a las entidades
public class Regata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Todos los IDs se generarán en secuencia numérica: 1,2,3,...
    private int id;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Temporal(TemporalType.DATE)
    private Date fechaLlegada;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "regatas") // Inversa -> mappedBy
    private List<Barco> barcos; // Barco será dueña de la relación, implementará cascade
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Puerto> puertos;

    public Regata() {
    }

    public Regata(String nombre, Date fechaSalida, Date fechaLlegada) {
        this.nombre = nombre;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
    }

    public Regata(String nombre, Date fechaSalida, Date fechaLlegada, List<Barco> barcos, List<Puerto> puertos) {
        this.nombre = nombre;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.barcos = barcos;
        this.puertos = puertos;
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

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    public List<Puerto> getPuertos() {
        return puertos;
    }

    public void setPuertos(List<Puerto> puertos) {
        this.puertos = puertos;
    }


}
