package com.fatidecoraciones.FatiDeco.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CortEspeciales.class)
public abstract class CortEspeciales_ {

	public static volatile SingularAttribute<CortEspeciales, String> tela;
	public static volatile SingularAttribute<CortEspeciales, Boolean> esTela;
	public static volatile SingularAttribute<CortEspeciales, Marca> marca;
	public static volatile SingularAttribute<CortEspeciales, Double> precio;
	public static volatile SingularAttribute<CortEspeciales, Sistema> sistema;
	public static volatile SingularAttribute<CortEspeciales, Long> id;

	public static final String TELA = "tela";
	public static final String ES_TELA = "esTela";
	public static final String MARCA = "marca";
	public static final String PRECIO = "precio";
	public static final String SISTEMA = "sistema";
	public static final String ID = "id";

}

