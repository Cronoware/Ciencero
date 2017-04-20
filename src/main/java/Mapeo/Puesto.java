package Mapeo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;

/**
 *
 * @author Emilio
 */
@Entity
@Table(name="puesto")

public class Puesto{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    	@Column(name = "puesto_id")
    	private int puesto_id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@ElementCollection
	@CollectionTable(name = "categorias", joinColumns = @JoinColumn(name = "puesto_id"))
	private List<String> tags;
	
	@Column(name = "posX")
	private int posX;
	
	@Column(name = "posY")
	private int posY;
	
	@OneToMany(mappedBy = "calificacion")
	private List<Calificacion> calificaciones_ususario;
	
	@OneToMany(mappedBy = "comentario")
	private List<Comentario> comentarios_usuario;
	
}
