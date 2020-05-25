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
public class Aeropuerto implements Serializable {

    private Long codigo;
    private Vuelo vueloLlegada;
    private Vuelo vueloSalida;
    private String ciudad;

    public Aeropuerto() {
    }

    public Aeropuerto(Long codigo, Vuelo vueloLlegada, Vuelo vueloSalida, String ciudad) {
        this.codigo = codigo;
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
