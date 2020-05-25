/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author aranx
 */
@Entity
public class Asiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado en orden numérico (1,2,3,...)
    private Long id;
    /*
    CASCADE: Los procesos que intervengan en una actualización del asiento (incluido detach, para trabajar con
    el ORM fuera del contexto de persistencia y evitar errores) se ejecutan en cascada. Remove no, pues un pasajero
    puede seguir tomando un aunque se cancele su asiento (puede ser reubicado).
    No deberíamos eliminar en cascada, pero daría error en la BD pq en las tablas ambas PK serán FK de la otra entidad!
    No persistimos en cascada, pq tanto el asiento debería haber sido persistido antes (para poder ser ofertado, tiene que estar ya en la BD),
    luego ambos ORM se persisten en momentos diferentes y podría dar error (si se usa save() o persist(), en lugar de saveOrUpdate())
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Vuelo vuelo;
    @OneToOne()
    private Pasajero pasajero;
    private int fila;
    private String letra;
    private boolean bussines;

    /*
    Ningún constructor recibirá el id por parámetro pues se AUTOGENERA
     */
    public Asiento() {
    }

    public Asiento(Vuelo vuelo, Pasajero pasajero) {
        this.vuelo = vuelo;
        this.pasajero = pasajero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean isBussines() {
        return bussines;
    }

    public void setBussines(boolean bussines) {
        this.bussines = bussines;
    }

    
}
