package com.fatidecoraciones.pedidos.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, String> apellido;
	public static volatile SetAttribute<Cliente, Presupuesto> presupuestos;
	public static volatile SingularAttribute<Cliente, String> direccion;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SetAttribute<Cliente, Pedido> pedidos;
	public static volatile SingularAttribute<Cliente, String> telefono;
	public static volatile SingularAttribute<Cliente, String> nombre;

	public static final String APELLIDO = "apellido";
	public static final String PRESUPUESTOS = "presupuestos";
	public static final String DIRECCION = "direccion";
	public static final String ID = "id";
	public static final String PEDIDOS = "pedidos";
	public static final String TELEFONO = "telefono";
	public static final String NOMBRE = "nombre";

}

