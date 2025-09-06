package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.dB.enums.Apertura;
import com.fatidecoraciones.FatiDeco.dB.enums.Estado;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Taller.class)
public abstract class Taller_ {

	public static volatile SingularAttribute<Taller, Estado> estado;
	public static volatile SingularAttribute<Taller, LocalDate> fecha_entrega;
	public static volatile SingularAttribute<Taller, String> ambiente;
	public static volatile SingularAttribute<Taller, Boolean> llego;
	public static volatile SingularAttribute<Taller, Integer> alto;
	public static volatile SingularAttribute<Taller, Apertura> apertura;
	public static volatile SingularAttribute<Taller, String> clienteNombre;
	public static volatile SingularAttribute<Taller, Cliente> cliente;
	public static volatile SingularAttribute<Taller, LocalDate> fecha_pedido;
	public static volatile SingularAttribute<Taller, Integer> ancho;
	public static volatile SingularAttribute<Taller, String> accesorios;
	public static volatile SingularAttribute<Taller, String> observaciones;
	public static volatile SingularAttribute<Taller, Long> id;

	public static final String ESTADO = "estado";
	public static final String FECHA_ENTREGA = "fecha_entrega";
	public static final String AMBIENTE = "ambiente";
	public static final String LLEGO = "llego";
	public static final String ALTO = "alto";
	public static final String APERTURA = "apertura";
	public static final String CLIENTE_NOMBRE = "clienteNombre";
	public static final String CLIENTE = "cliente";
	public static final String FECHA_PEDIDO = "fecha_pedido";
	public static final String ANCHO = "ancho";
	public static final String ACCESORIOS = "accesorios";
	public static final String OBSERVACIONES = "observaciones";
	public static final String ID = "id";

}

