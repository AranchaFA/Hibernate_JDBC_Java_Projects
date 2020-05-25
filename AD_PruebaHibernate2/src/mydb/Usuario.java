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
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="Usuario"
    ,catalog="mydb"
)
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private String nombre;
     private Set comentarios = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    public Usuario(String nombre, Set comentarios) {
       this.nombre = nombre;
       this.comentarios = comentarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_usuario", unique=true, nullable=false)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    @Column(name="nombre", nullable=false, length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }




}


