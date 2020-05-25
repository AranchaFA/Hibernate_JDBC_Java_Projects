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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author aranx
 */
@Entity
public class Vuelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado en orden numérico (1,2,3,...)
    private Long codigo;
    /*
    CASCADE: Si elimino un vuelo tengo que eliminar obligatoriamente sus billetes porque, al ser una relación bidireccional 1-N,
    en las tablas de la BD el registro del billete incluirá un campo con la PK del vuelo como FK, si elimino la referencia a esa FK
    daría un error. Si se deseara guardar la info de los billetes habría que volcarla a otro sitio antes de eliminar.
    Persisto y actualizo en cascada los posibles cambios en el vuelo (por ejemplo, un cambio de PK).
    FETCH: Eager->La lista tendrá el tamaño del número de plazas como máximo, luego será poco pesada,
    y cuando trabajemos con la info de un vuelo nos interesará conocer también la de sus billetes
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "vuelo")
    private List<Billete> billetes;
    /*
    CASCADE: Si elimino un vuelo tengo que eliminar obligatoriamente sus asientos porque, al ser una relación bidireccional 1-N,
    en las tablas de la BD el registro del billete incluirá un campo con la PK del vuelo como FK, si elimino la referencia a esa FK
    daría un error. Si se deseara guardar la info de los billetes habría que volcarla a otro sitio antes de eliminar. Además, los asientos
    (entendidos como la posibilidad de volar en un avión) no existirían si no hay vuelo.
    Persisto y actualizo en cascada los posibles cambios en el vuelo (por ejemplo, un cambio de PK).
    FETCH: Eager->La lista tendrá el tamaño del número de plazas, luego será poco pesada,
    y cuando trabajemos con la info de un vuelo nos interesará conocer también la de sus asientos
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "vuelo")
    private List<Asiento> asientos; // Podría ser TreeSet paa que vayan en orden y sin duplicados
    /*
    CASCADE: Como en todas las relaciones bidireccionales 1-1, hay que eliminar en cascada por culpa de los errores
    con las PK
     */
    @OneToOne(cascade = {CascadeType.ALL})
    private Aeropuerto origen;
    /*
    CASCADE: Como en todas las relaciones bidireccionales 1-1, hay que eliminar en cascada por culpa de los errores
    con las PK
     */
    @OneToOne(cascade = {CascadeType.ALL})
    private Aeropuerto destino;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVuelo;

    /*
    Ningún constructor recibirá el id por parámetro pues se AUTOGENERA
     */
    public Vuelo() {
    }

    public Vuelo(Aeropuerto origen, Aeropuerto destino, Date fechaVuelo) {
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

    public List<Billete> getBilletes() {
        return billetes;
    }

    public void setBilletes(List<Billete> billetes) {
        this.billetes = billetes;
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
