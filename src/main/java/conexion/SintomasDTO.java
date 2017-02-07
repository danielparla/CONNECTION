package conexion;

import java.util.List;

public class SintomasDTO {
	
	private int id_sintoma;
	private String des_sint;
	private List<PatologiaDTO> lista_patologias;
	
	public SintomasDTO(int id_sintoma, String nombre_sintoma)
	{
		super();
		this.id_sintoma = id_sintoma;
		this.des_sint = nombre_sintoma;
	}

	public SintomasDTO(){
		
	}
	
	public SintomasDTO(String nombre_sintoma) {
		this.des_sint = nombre_sintoma;
	}

	public int getId_sintoma() {
		return id_sintoma;
	}
	public void setId_sintoma(int id_sintoma) {
		this.id_sintoma = id_sintoma;
	}
	public String getNombre_sintoma() {
		return des_sint;
	}
	public void setNombre_sintoma(String nombre_sintoma) {
		this.des_sint = nombre_sintoma;
	}
	
}