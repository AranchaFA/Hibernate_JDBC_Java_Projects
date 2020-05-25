package pojos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vivienda.class)
public abstract class Vivienda_ {

	public static volatile SingularAttribute<Vivienda, Urbanizacion> urbanizacion;
	public static volatile SingularAttribute<Vivienda, Integer> id;
	public static volatile SingularAttribute<Vivienda, String> letra;
	public static volatile SingularAttribute<Vivienda, Propietario> propietario;
	public static volatile SingularAttribute<Vivienda, Integer> piso;

}

