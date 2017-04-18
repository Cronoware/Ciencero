package com.Cronoware.Mapeo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*@author Emilio
*/
@Entity
@Table(name="calificacion")
public class Calificacion{
	
	@ManyToOne	
	@JoinColumn(name = "usuario_id")
	private Ususario u;
	
	@ManyToOne	
	@JoinColumn(name = "puesto_id")
	private Puesto p;
	
	@Column(name = "calificacion")
	private int estrellas;
	
	public Ususario getUsuario(){
		return u;
	}
	
	public void setUsuario(Usuario u){
		this.u = u;
	}
	
	public Puesto getPuesto(){
		return p;
	}
	
	public void setPuesto(Puesto p){
		this.p = p;
	}
	
	public int getCalificacion(){
		return calificacion;
	}
	
	public void setCalificacion(int calificacion){
		this.calificacion = calificacion;
	}
	
}
