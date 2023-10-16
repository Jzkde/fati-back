package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.enums.Sistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Presupuesto.class)
public abstract class Presupuesto_ {

	public static volatile SingularAttribute<Presupuesto, Cliente> cliente;
	public static volatile SingularAttribute<Presupuesto, String> clienteNombre;
	public static volatile SingularAttribute<Presupuesto, Sistema> sistema;
	public static volatile SingularAttribute<Presupuesto, String> ambiente;
	public static volatile SingularAttribute<Presupuesto, Integer> ancho;
	public static volatile SingularAttribute<Presupuesto, String> accesorios;
	public static volatile SingularAttribute<Presupuesto, String> observaciones;
	public static volatile SingularAttribute<Presupuesto, Comando> comando;
	public static volatile SingularAttribute<Presupuesto, Long> id;
	public static volatile SingularAttribute<Presupuesto, Integer> alto;
	public static volatile SingularAttribute<Presupuesto, Apertura> apertura;

	public static final String CLIENTE = "cliente";
	public static final String CLIENTE_NOMBRE = "clienteNombre";
	public static final String SISTEMA = "sistema";
	public static final String AMBIENTE = "ambiente";
	public static final String ANCHO = "ancho";
	public static final String ACCESORIOS = "accesorios";
	public static final String OBSERVACIONES = "observaciones";
	public static final String COMANDO = "comando";
	public static final String ID = "id";
	public static final String ALTO = "alto";
	public static final String APERTURA = "apertura";

}

