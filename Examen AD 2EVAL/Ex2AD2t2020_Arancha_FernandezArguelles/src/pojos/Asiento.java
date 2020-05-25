/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;

/**
 *
 * @author aranx
 */
public class Asiento implements Serializable {

    private Long id;
    private Vuelo vuelo;
    private int fila;
    private String letra;
    private boolean bussines;

    public Asiento() {
    }

    public Asiento(Long id, Vuelo vuelo, int fila, String letra, boolean bussines) {
        this.id = id;
        this.vuelo = vuelo;
        this.fila = fila;
        this.letra = letra;
        this.bussines = bussines;
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
