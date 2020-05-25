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

/**
 *
 * @author aranx
 */
@Entity
public class Vivienda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Valor número autogenerado en orden 1,2,3,4,...
    private int id;
    private int piso;
    private String letra;
    @ManyToOne (cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private Propietario propietario;
    @ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Urbanizacion urbanizacion;

    public Vivienda() {
    }

    public Vivienda(int piso, String letra, Propietario propietario, Urbanizacion urbanizacion) {
        this.piso = piso;
        this.letra = letra;
        this.propietario = propietario;
        this.urbanizacion = urbanizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Urbanizacion getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(Urbanizacion urbanizacion) {
        this.urbanizacion = urbanizacion;
    }
    
    
}
