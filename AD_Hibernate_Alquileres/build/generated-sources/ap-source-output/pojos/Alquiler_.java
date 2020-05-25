package pojos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Alquiler.class)
public abstract class Alquiler_ {

	public static volatile SingularAttribute<Alquiler, Integer> id;
	public static volatile SingularAttribute<Alquiler, Float> precio;
	public static volatile SingularAttribute<Alquiler, Arrendatario> arrendatario;
	public static volatile SingularAttribute<Alquiler, Vivienda> vivienda;
	public static volatile SingularAttribute<Alquiler, Date> fechaFin;
	public static volatile SingularAttribute<Alquiler, Date> fechaInicio;

}

