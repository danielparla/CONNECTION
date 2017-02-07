package conexion;

import java.util.Scanner;

public class EntradaSalida {

	
	 
	public static void mostrarMenu ()		//Muestra el menu con las opciones del programa
	{
		System.out.println("Elija una opci�n: ");
		System.out.println("1. Listar patolog�as");
		System.out.println("2. Listar s�ntomas");
		System.out.println("3. Buscar patolog�as por nombre");
		System.out.println("4. Buscar s�ntomas por nombre");
		System.out.println("5. Buscar lista patolog�as por s�ntomas");
		System.out.println("6. Listar s�ntomas de una patolog�a");
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