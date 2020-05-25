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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author aranx
 */
@Entity
//@Table no hará falta: todas las tablas se llamarán por defecto como los objetos que representan a las entidades
public class Barco implements Serializable {

    @Id
    private String matricula;
    private String nombre;
    private float eslora;
    private float manga;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Regata> regatas;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Amarre amarre; // Tiene que persistirlo y actualizarlo en cascada, pq podemos haber cambiado el estado de amarre.isOcupado

    public Barco() {
    }

    public Barco(String matricula, String nombre, float eslora, float manga, Amarre amarre) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.eslora = eslora;
        this.manga = manga;
        this.amarre = amarre;
    }

    public Barco(String matricula, String nombre, float eslora, float manga, List<Regata> regatas, Amarre amarre) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.eslora = eslora;
        this.manga = manga;
        this.regatas = regatas;
        this.amarre = amarre;
        if (this.amarre != null) {
            this.amarre.setBarco(this);
        }

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getEslora() {
        return eslora;
    }

    public void setEslora(float eslora) {
        this.eslora = eslora;
    }

    public float getManga() {
        return manga;
    }

    public void setManga(float manga) {
        this.manga = manga;
    }

    public List<Regata> getRegatas() {
        return regatas;
    }

    public void setRegatas(List<Regata> regatas) {
        this.regatas = regatas;
    }

    public Amarre getAmarre() {
        return amarre;
    }

    public void setAmarre(Amarre amarre) {
        this.amarre = amarre;
    }

    public boolean amarrar() {
        amarre.setOcupado(!amarre.isOcupado());
        return amarre.isOcupado();
    }

}
