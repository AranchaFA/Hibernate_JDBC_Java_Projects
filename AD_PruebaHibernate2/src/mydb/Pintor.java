package mydb;
// Generated 12-feb-2020 18:14:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pintor generated by hbm2java
 */
@Entity
@Table(name="Pintor"
    ,catalog="mydb"
)
public class Pintor  implements java.io.Serializable {


     private Integer idPintor;
     private String nombre;
     private Integer anioNacimiento;
     private String estilo;
     private Set cuadros = new HashSet(0);

    public Pintor() {
    }

	
    public Pintor(String nombre) {
        this.nombre = nombre;
    }
    public Pintor(String nombre, Integer anioNacimiento, String estilo, Set cuadros) {
       this.nombre = nombre;
       this.anioNacimiento = anioNacimiento;
       this.estilo = estilo;
       this.cuadros = cuadros;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_pintor", unique=true, nullable=false)
    public Integer getIdPintor() {
        return this.idPintor;
    }
    
    public void setIdPintor(Integer idPintor) {
        this.idPintor = idPintor;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="anio_nacimiento")
    public Integer getAnioNacimiento() {
        return this.anioNacimiento;
    }
    
    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    
    @Column(name="estilo", length=50)
    public String getEstilo() {
        return this.estilo;
    }
    
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pintor")
    public Set getCuadros() {
        return this.cuadros;
    }
    
    public void setCuadros(Set cuadros) {
        this.cuadros = cuadros;
    }

    @Override
    public String toString() {
        return "Pintor{" + "idPintor=" + idPintor + ", nombre=" + nombre + ", estilo=" + estilo +'}';
    }

    




}

