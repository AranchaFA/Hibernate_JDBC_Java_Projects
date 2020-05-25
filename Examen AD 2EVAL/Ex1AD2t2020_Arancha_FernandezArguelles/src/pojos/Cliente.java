/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author aranx
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado en orden numérico (1,2,3,...)
    private Long id;
    /*
    CASCADE: Si elimino un cliente tengo que eliminar obligatoriamente sus billetes porque, al ser una relación bidireccional 1-N,
    en las tablas de la BD el registro del billete incluirá un campo con la PK del cliente como FK, si elimino la referencia a esa FK
    daría un error. Si se deseara guardar la info de los billetes habría que volcarla a otro sitio antes de eliminar.
    Persisto y actualizo en cascada los posibles cambios en el usuario (por ejemplo, un cambio de PK aunque en este caso es el DNI y no debería darse).
    FETCH: Lazy->Un cliente puede tener una lista de billetes amplia (por ejemplo, una empresa) y no querer acceder a ella, sino a sus datos personales
    */
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "cliente")
    private List<Billete> billetes;
    private String nombre;
    private String correoE;

    /*
    Ningún constructor recibirá el id por parámetro pues se AUTOGENERA
    */
    public Cliente() {
    }

    // Constructor sin los atributos ORM
    public Cliente(String nombre, String correoE) {
        this.nombre = nombre;
        this.correoE = correoE;
    }

    public Cliente(List<Billete> billetes, String nombre, String correoE) {
        this.billetes = billetes;
        this.nombre = nombre;
        this.correoE = correoE;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Billete> getBilletes() {
        return billetes;
    }

    public void setBilletes(List<Billete> billetes) {
        this.billetes = billetes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoE() {
        return correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }
    
    

}
