package pojos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Arrendatario.class)
public abstract class Arrendatario_ {

	public static volatile SingularAttribute<Arrendatario, String> nombre;
	public static volatile ListAttribute<Arrendatario, Alquiler> alquileres;
	public static volatile SingularAttribute<Arrendatario, String> apellidos;
	public static volatile SingularAttribute<Arrendatario, String> dni;

}

