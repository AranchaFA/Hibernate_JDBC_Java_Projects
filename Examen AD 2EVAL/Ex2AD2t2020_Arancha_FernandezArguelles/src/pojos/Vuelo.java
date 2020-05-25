/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aranx
 */
public class Vuelo implements Serializable {

    private Long codigo;
    private List<Asiento> asientos; // Podría ser TreeSet paa que vayan en orden y sin duplicados
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Date fechaVuelo;

    public Vuelo() {
    }

    public Vuelo(Long codigo, List<Asiento> asientos, Aeropuerto origen, Aeropuerto destino, Date fechaVuelo) {
        this.codigo = codigo;
        this.asientos = asientos;
        this.origen = origen;
        this.destino = destino;
        this.fechaVuelo = fechaVuelo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

}
