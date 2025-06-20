package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.enums.Estado;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, LocalDate> fecha_llegada;
	public static volatile SingularAttribute<Pedido, Estado> estado;
	public static volatile SingularAttribute<Pedido, String> responsable;
	public static volatile SingularAttribute<Pedido, String> n_factura;
	public static volatile SingularAttribute<Pedido, String> n_remito;
	public static volatile SingularAttribute<Pedido, String> provedor;
	public static volatile SingularAttribute<Pedido, Boolean> llego;
	public static volatile SingularAttribute<Pedido, String> via;
	public static volatile SingularAttribute<Pedido, String> clienteNombre;
	public static volatile SingularAttribute<Pedido, LocalDate> fecha_pedido;
	public static volatile SingularAttribute<Pedido, Double> monto;
	public static volatile SingularAttribute<Pedido, String> n_pedido;
	public static volatile SingularAttribute<Pedido, String> observaciones;
	public static volatile SingularAttribute<Pedido, Long> id;

	public static final String FECHA_LLEGADA = "fecha_llegada";
	public static final String ESTADO = "estado";
	public static final String RESPONSABLE = "responsable";
	public static final String N_FACTURA = "n_factura";
	public static final String N_REMITO = "n_remito";
	public static final String PROVEDOR = "provedor";
	public static final String LLEGO = "llego";
	public static final String VIA = "via";
	public static final String CLIENTE_NOMBRE = "clienteNombre";
	public static final String FECHA_PEDIDO = "fecha_pedido";
	public static final String MONTO = "monto";
	public static final String N_PEDIDO = "n_pedido";
	public static final String OBSERVACIONES = "observaciones";
	public static final String ID = "id";

}

