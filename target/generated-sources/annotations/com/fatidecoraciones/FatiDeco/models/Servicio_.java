package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.dB.enums.Serv;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servicio.class)
public abstract class Servicio_ {

	public static volatile SingularAttribute<Servicio, Marca> marca;
	public static volatile SingularAttribute<Servicio, Serv> tipo;
	public static volatile SingularAttribute<Servicio, Double> precio;
	public static volatile SingularAttribute<Servicio, Long> id;
	public static volatile SingularAttribute<Servicio, String> nombre;

	public static final String MARCA = "marca";
	public static final String TIPO = "tipo";
	public static final String PRECIO = "precio";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

