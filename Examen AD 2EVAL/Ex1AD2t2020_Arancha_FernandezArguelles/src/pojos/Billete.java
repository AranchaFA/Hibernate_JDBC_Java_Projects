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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author aranx
 */
@Entity
public class Billete implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado en orden numérico (1,2,3,...)
    private Long codigo;
    @ManyToOne
    private Vuelo vuelo;
    /*
    CASCADE: Borramos en cascada, pues si anulamos el billete de unos pasajeros dejarán de ser pasajeros de ese vuelo
    También persistimos/actualizamos pues los pasajeros se persistirán cuando se cree el billete (antes no existirían como tal)
    FETCH: Eager->De un billete casi siempre nos va a interesar acceder a sus pasajeros, y será una lista muy pequeña
    */
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER,mappedBy = "billete")
    private List<Pasajero> pasajeros;
    @ManyToOne
    private Cliente cliente;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEmite;
    private float precioPasajero;
    private float descuentoNiños;
    private float precioFinal;

    /*
    Ningún constructor recibirá el id por parámetro pues se AUTOGENERA
    */
    public Billete() {
    }

    // Constructor sin los atributos ORM
    public Billete(Date fechaEmite, float precioPasajero, float descuentoNiños, float precioFinal) {
        this.fechaEmite = fechaEmite;
        this.precioPasajero = precioPasajero;
        this.descuentoNiños = descuentoNiños;
        this.precioFinal = precioFinal;
    }

    public Billete(Vuelo vuelo, List<Pasajero> pasajeros, Cliente cliente, Date fechaEmite, float precioPasajero, float descuentoNiños, float precioFinal) {
        this.vuelo = vuelo;
        this.pasajeros = pasajeros;
        this.cliente = cliente;
        this.fechaEmite = fechaEmite;
        this.precioPasajero = precioPasajero;
        this.descuentoNiños = descuentoNiños;
        this.precioFinal = precioFinal;
    }
    
    

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaEmite() {
        return fechaEmite;
    }

    public void setFechaEmite(Date fechaEmite) {
        this.fechaEmite = fechaEmite;
    }

    public float getPrecioPasajero() {
        return precioPasajero;
    }

    public void setPrecioPasajero(float precioPasajero) {
        this.precioPasajero = precioPasajero;
    }

    public float getDescuentoNiños() {
        return descuentoNiños;
    }

    public void setDescuentoNiños(float descuentoNiños) {
        this.descuentoNiños = descuentoNiños;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }
    
    
    
    
   
    
}
