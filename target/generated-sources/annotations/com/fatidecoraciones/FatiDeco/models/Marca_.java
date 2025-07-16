package com.fatidecoraciones.FatiDeco.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Marca.class)
public abstract class Marca_ {

	public static volatile SetAttribute<Marca, Servicio> servicios;
	public static volatile SingularAttribute<Marca, String> marca;
	public static volatile SetAttribute<Marca, Sistema> sistemas;
	public static volatile SingularAttribute<Marca, Boolean> esSistema;
	public static volatile SetAttribute<Marca, CortEspeciales> cortEspeciales;
	public static volatile SingularAttribute<Marca, Long> id;
	public static volatile SingularAttribute<Marca, String> nombre;
	public static volatile SetAttribute<Marca, Producto> productos;

	public static final String SERVICIOS = "servicios";
	public static final String MARCA = "marca";
	public static final String SISTEMAS = "sistemas";
	public static final String ES_SISTEMA = "esSistema";
	public static final String CORT_ESPECIALES = "cortEspeciales";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String PRODUCTOS = "productos";

}

