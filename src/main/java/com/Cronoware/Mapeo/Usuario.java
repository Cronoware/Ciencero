package com.Cronoware.Mapeo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Emilio
 */
@Entity
@Table(name="usuario")
public class Usuario{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    	@Column(name = "usuario_id")
    	private int usuario_id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "foto")
	private String foto;//dirección de la foto en el servidor
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "psswd")
	private String psswd;
	
	@OneToMany(mappedBy = "calificacion")
	private List<Calificacion> calificaciones_usuario;
	
	@OneToMany(mappedBy = "comentario")
	private List<Comentario> comentarios_usuario;
	
	public int getUsuario_id(){
		return usuario_id;
	}
	
	public void setUsuario_id(int usuario_id){
		this.usuario_id = usuario_id;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNobre(String nombre){
		this.nombre = nombre;
	}
	
	public String getFoto(){
		return foto;
	}
	
	public void setFoto(String foto){
		this.foto = foto;
	}
	
	public String getCorreo(){
		return correo;
	}
	
	public void setCorreo(String correo){
		this.correo = correo;
	}
	
	public String getPsswd(){
		return psswd;
	}
	
	public void setPsswd(){
		this.psswd = psswd;
	}
	
	public List<Calificacion> getCalificaciones_usuario(){
		return calificaciones_usuario;
	}
	
	public void setCalificaciones_usuario(List<Calificacion> calificaciones_usuario){
		this.calificaciones_usuario = calificaciones_usuario;
	}
	
	public List<Comentario> getComentarios_usuario(){
		return comentarios_usuario;
	}
	
	public void setComentarios_usuario(List<Comentario> comentarios_usuario){
		this.comentarios_usuario = comentarios_usuario;
	}
	
}
