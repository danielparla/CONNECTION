package conexion;

import java.util.ArrayList;
import java.util.Scanner;

public class MainBuscarPatol {

	public static void main(String[] args) {
	
		System.out.println("INTRODUZCA UN ID");
		
		Scanner sc= new Scanner(System.in);
		int eleccion= sc.nextInt();
		
		long t1=System.currentTimeMillis();
		ArrayList<PatologiaDTO> lista= BuscarPatologiaPorID.buscarPatologiaPorID(eleccion);
		
		
		
		for(PatologiaDTO patol:lista){
			System.out.println(patol.getNombre_patologia()+"\n");
			System.out.println("Descripcion: \n"+patol.getDescripcion_patologia());
			System.out.println("Tratamiento: \n"+patol.getTratamiento_patologia());
			System.out.println("Sintomas: \n");
			for(SintomasDTO sintom : patol.getLista_sintomas()){
				System.out.println(sintom.getId_sintoma()+" "+sintom.getNombre_sintoma());
			}
		}
		long t2=
		System.currentTimeMillis();
		
		System.out.println("Tiempo transcurrido: "+(t2-t1));

	}

}
//Tiempo 4224 aprox...