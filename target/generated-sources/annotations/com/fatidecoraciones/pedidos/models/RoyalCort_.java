package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Sistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoyalCort.class)
public abstract class RoyalCort_ {

	public static volatile SingularAttribute<RoyalCort, String> tela;
	public static volatile SingularAttribute<RoyalCort, Boolean> esTela;
	public static volatile SingularAttribute<RoyalCort, Double> precio;
	public static volatile SingularAttribute<RoyalCort, Sistema> sistema;
	public static volatile SingularAttribute<RoyalCort, Long> id;

	public static final String TELA = "tela";
	public static final String ES_TELA = "esTela";
	public static final String PRECIO = "precio";
	public static final String SISTEMA = "sistema";
	public static final String ID = "id";

}

