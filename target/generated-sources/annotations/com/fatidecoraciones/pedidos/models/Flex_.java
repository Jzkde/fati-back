package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Sistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Flex.class)
public abstract class Flex_ {

	public static volatile SingularAttribute<Flex, String> tela;
	public static volatile SingularAttribute<Flex, Boolean> esTela;
	public static volatile SingularAttribute<Flex, Double> precio;
	public static volatile SingularAttribute<Flex, Sistema> sistema;
	public static volatile SingularAttribute<Flex, Long> id;

	public static final String TELA = "tela";
	public static final String ES_TELA = "esTela";
	public static final String PRECIO = "precio";
	public static final String SISTEMA = "sistema";
	public static final String ID = "id";

}

