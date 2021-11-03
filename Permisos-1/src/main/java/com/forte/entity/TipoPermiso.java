package com.forte.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="TipoPermiso2")
public class TipoPermiso {
	
	@Column (name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TP")
	private Long id;
	
	@Column (name="Descripcion", nullable = false)
	private String descripcion;

	public TipoPermiso(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TipoPermiso() {
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "TipoPermiso [descripcion=" + descripcion + "]";
	}
}