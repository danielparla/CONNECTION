package conexion;

import java.util.ArrayList;
import java.util.List;


public class PatologiaDTO{

	private int id_patologia;
	private String nombre_patologia;
	private String descripcion_patologia;
	private String tratamiento_patologia;
	private List<SintomasDTO> lista_sintomas;
	
	public PatologiaDTO(){		
	}
	
	public PatologiaDTO(int id_patologia, String nombre_patologia,	String descripcion_patologia, 
			String tratamiento_patologia, ArrayList<SintomasDTO> lista_sintomas) 
	{
		this.id_patologia = id_patologia;
		this.nombre_patologia = nombre_patologia;
		this.descripcion_patologia = descripcion_patologia;
		this.tratamiento_patologia = tratamiento_patologia;
		this.lista_sintomas = lista_sintomas;
	}

	public int getId_patologia() {
		return id_patologia;
	}
	public void setId_patologia(int id_patologia) {
		this.id_patologia = id_patologia;
	}
	public String getNombre_patologia() {
		return nombre_patologia;
	}
	public void setNombre_patologia(String nombre_patologia) {
		this.nombre_patologia = nombre_patologia;
	}
	public String getDescripcion_patologia() {
		return descripcion_patologia;
	}
	public void setDescripcion_patologia(String descripcion_patologia) {
		this.descripcion_patologia = descripcion_patologia;
	}

	public String getTratamiento_patologia() {
		return tratamiento_patologia;
	}

	public void setTratamiento_patologia(String tratamiento_patologia) {
		this.tratamiento_patologia = tratamiento_patologia;
	}

	public List<SintomasDTO> getLista_sintomas() {
		return lista_sintomas;
	}

	public void setLista_sintomas(List<SintomasDTO> lista_sintomas) {
		this.lista_sintomas = lista_sintomas;
	}	
	
}