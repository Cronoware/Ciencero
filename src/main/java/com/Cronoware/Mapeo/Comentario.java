package com.Cronoware.Mapeo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*@author Emilio
*/
@Entity
@Table(name="comentario")
public class Comentario{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    	@Column(name = "comentario_id")
    	private int comentario_id;
	
	@ManyToOne	
	@JoinColumn(name = "usuario_id")
	private Ususario u;
	
	@ManyToOne	
	@JoinColumn(name = "puesto_id")
	private Puesto p;
	
	@Column(name = "comentario")
	private String comentario;
	
	public int getComentario_id() {
	        return comentario_id;
    	}

    	public void setComentario_id(int comentario_id) {
        	this.comentario_id = comentario_id;
    	}
	
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
	
	public String getComentario(){
		return comentario;
	}
	
	public void setComentario(String comentario){
		this.comentario = comentario;
	}
	
}
