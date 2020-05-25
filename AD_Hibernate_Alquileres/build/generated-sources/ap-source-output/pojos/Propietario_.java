package pojos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Propietario.class)
public abstract class Propietario_ {

	public static volatile SingularAttribute<Propietario, String> nombre;
	public static volatile SingularAttribute<Propietario, String> cif;
	public static volatile SingularAttribute<Propietario, String> apellidos;
	public static volatile ListAttribute<Propietario, Vivienda> viviendas;

}

