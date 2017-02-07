package conexion;

import java.util.Scanner;

public class EntradaSalida {

	
	 
	public static void mostrarMenu ()		//Muestra el menu con las opciones del programa
	{
		System.out.println("Elija una opción: ");
		System.out.println("1. Listar patologías");
		System.out.println("2. Listar síntomas");
		System.out.println("3. Buscar patologías por nombre");
		System.out.println("4. Buscar síntomas por nombre");
		System.out.println("5. Buscar lista patologías por síntomas");
		System.out.println("6. Listar síntomas de una patología");
		System.out.println("7. Salir");
	}
	
	public static int pedirOpcion ()
	{
		int opcion = 0;
		Scanner sc = null;
		
			sc = new Scanner(System.in);
			opcion = sc.nextInt();
			
		return opcion;
	}
	
	
	public static String pedirNombre ()
	{
		String nombre = null;
		Scanner sc = null;
		
			sc = new Scanner(System.in);
			System.out.println("Introduzca el nombre: ");
			nombre = sc.nextLine();
			
		return nombre;
	}
}