package com.fatidecoraciones.FatiDeco.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sistema.class)
public abstract class Sistema_ {

	public static volatile SingularAttribute<Sistema, String> sistema;
	public static volatile SetAttribute<Sistema, CortEspeciales> cortEspeciales;
	public static volatile SetAttribute<Sistema, Medida> medidas;
	public static volatile SingularAttribute<Sistema, Long> id;

	public static final String SISTEMA = "sistema";
	public static final String CORT_ESPECIALES = "cortEspeciales";
	public static final String MEDIDAS = "medidas";
	public static final String ID = "id";

}

