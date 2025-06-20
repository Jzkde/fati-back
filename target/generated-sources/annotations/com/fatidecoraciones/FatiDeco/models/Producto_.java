package com.fatidecoraciones.FatiDeco.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Producto.class)
public abstract class Producto_ {

	public static volatile SingularAttribute<Producto, Boolean> esTela;
	public static volatile SingularAttribute<Producto, Marca> marca;
	public static volatile SingularAttribute<Producto, String> art;
	public static volatile SingularAttribute<Producto, Double> precio;
	public static volatile SingularAttribute<Producto, Long> id;
	public static volatile SingularAttribute<Producto, String> nombre;

	public static final String ES_TELA = "esTela";
	public static final String MARCA = "marca";
	public static final String ART = "art";
	public static final String PRECIO = "precio";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

