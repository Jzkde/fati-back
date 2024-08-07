package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.enums.Sistema;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Presupuesto.class)
public abstract class Presupuesto_ {

	public static volatile SingularAttribute<Presupuesto, Sistema> sistema;
	public static volatile SingularAttribute<Presupuesto, String> ambiente;
	public static volatile SingularAttribute<Presupuesto, Boolean> viejo;
	public static volatile SingularAttribute<Presupuesto, Integer> alto;
	public static volatile SingularAttribute<Presupuesto, Apertura> apertura;
	public static volatile SingularAttribute<Presupuesto, Boolean> comprado;
	public static volatile SingularAttribute<Presupuesto, String> clienteNombre;
	public static volatile SingularAttribute<Presupuesto, LocalDate> fecha;
	public static volatile SingularAttribute<Presupuesto, Integer> ancho;
	public static volatile SingularAttribute<Presupuesto, String> accesorios;
	public static volatile SingularAttribute<Presupuesto, String> observaciones;
	public static volatile SingularAttribute<Presupuesto, Comando> comando;
	public static volatile SingularAttribute<Presupuesto, Long> id;

	public static final String SISTEMA = "sistema";
	public static final String AMBIENTE = "ambiente";
	public static final String VIEJO = "viejo";
	public static final String ALTO = "alto";
	public static final String APERTURA = "apertura";
	public static final String COMPRADO = "comprado";
	public static final String CLIENTE_NOMBRE = "clienteNombre";
	public static final String FECHA = "fecha";
	public static final String ANCHO = "ancho";
	public static final String ACCESORIOS = "accesorios";
	public static final String OBSERVACIONES = "observaciones";
	public static final String COMANDO = "comando";
	public static final String ID = "id";

}

