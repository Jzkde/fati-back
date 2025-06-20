package com.fatidecoraciones.FatiDeco.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, String> direccion;
	public static volatile SetAttribute<Cliente, Taller> talleres;
	public static volatile SetAttribute<Cliente, Medida> medidas;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> telefono;
	public static volatile SingularAttribute<Cliente, String> nombre;

	public static final String DIRECCION = "direccion";
	public static final String TALLERES = "talleres";
	public static final String MEDIDAS = "medidas";
	public static final String ID = "id";
	public static final String TELEFONO = "telefono";
	public static final String NOMBRE = "nombre";

}

