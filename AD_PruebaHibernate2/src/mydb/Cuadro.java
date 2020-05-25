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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cuadro generated by hbm2java
 */
@Entity
@Table(name="Cuadro"
    ,catalog="mydb"
)
public class Cuadro  implements java.io.Serializable {


     private Integer idCuadro;
     private Pintor pintor;
     private String titulo;
     private Integer anio;
     private Set comentarios = new HashSet(0);

    public Cuadro() {
    }

	
    public Cuadro(Pintor pintor, String titulo) {
        this.pintor = pintor;
        this.titulo = titulo;
    }
    public Cuadro(Pintor pintor, String titulo, Integer anio, Set comentarios) {
       this.pintor = pintor;
       this.titulo = titulo;
       this.anio = anio;
       this.comentarios = comentarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_cuadro", unique=true, nullable=false)
    public Integer getIdCuadro() {
        return this.idCuadro;
    }
    
    public void setIdCuadro(Integer idCuadro) {
        this.idCuadro = idCuadro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_autor", nullable=false)
    public Pintor getPintor() {
        return this.pintor;
    }
    
    public void setPintor(Pintor pintor) {
        this.pintor = pintor;
    }

    
    @Column(name="titulo", nullable=false, length=200)
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    @Column(name="anio")
    public Integer getAnio() {
        return this.anio;
    }
    
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cuadro")
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }




}


