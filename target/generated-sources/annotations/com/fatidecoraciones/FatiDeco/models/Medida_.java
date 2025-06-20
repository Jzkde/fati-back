package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.enums.Apertura;
import com.fatidecoraciones.FatiDeco.enums.Comando;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medida.class)
public abstract class Medida_ {

	public static volatile SingularAttribute<Medida, String> ambiente;
	public static volatile SingularAttribute<Medida, Sistema> sistema;
	public static volatile SingularAttribute<Medida, Boolean> viejo;
	public static volatile SingularAttribute<Medida, Boolean> caida;
	public static volatile SingularAttribute<Medida, Integer> alto;
	public static volatile SingularAttribute<Medida, Apertura> apertura;
	public static volatile SingularAttribute<Medida, Boolean> comprado;
	public static volatile SingularAttribute<Medida, String> clienteNombre;
	public static volatile SingularAttribute<Medida, LocalDate> fecha;
	public static volatile SingularAttribute<Medida, Cliente> cliente;
	public static volatile SingularAttribute<Medida, Integer> ancho;
	public static volatile SingularAttribute<Medida, String> accesorios;
	public static volatile SingularAttribute<Medida, String> observaciones;
	public static volatile SingularAttribute<Medida, Comando> comando;
	public static volatile SingularAttribute<Medida, Long> id;

	public static final String AMBIENTE = "ambiente";
	public static final String SISTEMA = "sistema";
	public static final String VIEJO = "viejo";
	public static final String CAIDA = "caida";
	public static final String ALTO = "alto";
	public static final String APERTURA = "apertura";
	public static final String COMPRADO = "comprado";
	public static final String CLIENTE_NOMBRE = "clienteNombre";
	public static final String FECHA = "fecha";
	public static final String CLIENTE = "cliente";
	public static final String ANCHO = "ancho";
	public static final String ACCESORIOS = "accesorios";
	public static final String OBSERVACIONES = "observaciones";
	public static final String COMANDO = "comando";
	public static final String ID = "id";

}

