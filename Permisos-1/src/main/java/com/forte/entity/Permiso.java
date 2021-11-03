package com.forte.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Permiso2")
public class Permiso {
	
	@Column (name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Pms")
	private Long idP;
	
	@Column (name="Nombre_Empleado", nullable = false)
	private String nombre;
	
	@Column (name="Apellidos_Empleado", nullable = false)
	private String apellidos;
	
	@Column (name="Fecha_Permiso", nullable = false)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="TipoPermiso")
	private TipoPermiso id;

	public Permiso(String nombre, String apellidos, Date fecha, TipoPermiso id) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.id = id;
	}
	
	public Permiso() {
		
	}

	public Long getId() {
		return idP;
	}

	public void setId(Long id) {
		this.idP = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoPermiso getTp() {
		return id;
	}

	public void setTp(TipoPermiso tp) {
		this.id = tp;
	}

	@Override
	public String toString() {
		return "Permiso [nombre=" + nombre + ", apellidos=" + apellidos + ", fecha=" + fecha + ", tp=" + id + "]";
	}
}
