package com.mc.corp.spring.boot.backent.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cliente_id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Size(min=4, max=12, message = "El tamaño tiene que ser entre 4 y 12 caracteres")
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name="apellido")
	private String apellido;
	
	@NotEmpty(message = "No puede estar vacio")
	@Email(message = "No es una direccíon de correo correcta (ejemplo: correo@email.com)")
	@Column(name="email", nullable=false, unique=false)
	private String email;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@PrePersist
	public void prePersist() {
		fecha = new Date(); 
	}
	
	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 371835905440722343L;

}
