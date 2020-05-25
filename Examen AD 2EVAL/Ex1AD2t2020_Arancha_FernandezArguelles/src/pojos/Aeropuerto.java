/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author aranx
 */
@Entity
public class Aeropuerto implements Serializable {

    @Id
    private Long codigo;
    /*
    CASCADE: Como en todas las relaciones bidireccionales 1-1, hay que eliminar en cascada por culpa de los errores
    con las PK
     */
    @OneToOne(cascade = {CascadeType.ALL})
    private Vuelo vueloLlegada;
    /*
    CASCADE: Como en todas las relaciones bidireccionales 1-1, hay que eliminar en cascada por culpa de los errores
    con las PK
     */
    @OneToOne(cascade = {CascadeType.ALL})
    private Vuelo vueloSalida;
    private String ciudad;

    public Aeropuerto() {
    }

    public Aeropuerto(Vuelo vueloLlegada, Vuelo vueloSalida, String ciudad) {
        this.vueloLlegada = vueloLlegada;
        this.vueloSalida = vueloSalida;
        this.ciudad = ciudad;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Vuelo getVueloLlegada() {
        return vueloLlegada;
    }

    public void setVueloLlegada(Vuelo vueloLlegada) {
        this.vueloLlegada = vueloLlegada;
    }

    public Vuelo getVueloSalida() {
        return vueloSalida;
    }

    public void setVueloSalida(Vuelo vueloSalida) {
        this.vueloSalida = vueloSalida;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
