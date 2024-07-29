package com.fatidecoraciones.pedidos.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Colocacion.class)
public abstract class Colocacion_ {

	public static volatile SingularAttribute<Colocacion, String> tipo;
	public static volatile SingularAttribute<Colocacion, Double> precio;
	public static volatile SingularAttribute<Colocacion, Long> id;

	public static final String TIPO = "tipo";
	public static final String PRECIO = "precio";
	public static final String ID = "id";

}

