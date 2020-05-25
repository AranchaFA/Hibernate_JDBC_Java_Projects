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
import javax.persistence.OneToOne;

/**
 *
 * @author aranx
 */
@Entity
//@Table no hará falta: todas las tablas se llamarán por defecto como los objetos que representan a las entidades
public class Amarre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Todos los IDs se generarán en secuencia numérica: 1,2,3,...
    private int id;
    private int numero;
    private int categoria; // Categoría 1,2,3,... es más sencillo que un String
    private boolean ocupado;
    @OneToOne
    private Barco barco;

    public Amarre() {
    }

    public Amarre(int numero, int categoria, Barco barco) {
        this.numero = numero;
        this.categoria = categoria;
        this.barco = barco;
        if (this.barco != null) {
            this.barco.setAmarre(this);
        }
        this.ocupado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    

}
