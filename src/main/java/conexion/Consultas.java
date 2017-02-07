package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {
	
	public static final String CONSULTA_LISTAR_SINTOMAS = "SELECT * FROM Sintomas";
	public static final String CONSULTA_LISTAR_PATOLOGIAS = "SELECT * FROM Patologias";
	public static final String CONSULTA_PATOLOGIAS_POR_NOMBRE = "Select * From Patologias Where id_patol IN (Select id_patol from Patologias Where nom_patol = '";
	public static final String CONSULTA_SINTOMAS_POR_NOMBRE = "SELECT des_sint From Sintomas Where id_sint IN (Select id_sint From Sintomas Where des_sint = '";
	public static final String CONSULTA_PATOLOGIAS_POR_SINTOMA = "SELECT * FROM Patologias WHERE id_patol IN (SELECT id_patol FROM Causas WHERE id_sint IN (SELECT id_sint FROM Sintomas WHERE des_sint = '";
	public static final String CONSULTA_SINTOMAS_POR_PATOLOGIA = "SELECT des_sint FROM Sintomas WHERE id_sint IN (SELECT id_sint FROM Causas WHERE id_patol IN (SELECT id_patol FROM Patologias WHERE nom_patol = '";

	
	public static void listarPatologias (Statement stmt, Connection conn) throws SQLException
	{
		String nombre = null;
		ResultSet rset = null;
		rset = stmt.executeQuery(Consultas.CONSULTA_LISTAR_PATOLOGIAS);
		while (rset.next())
	    {
			nombre = rset.getString(2);
			System.out.println(nombre);
		}
		System.out.println();
	}
	
	public static void listarSintomas (Statement stmt, Connection conn) throws SQLException
	{
		String nombre = null;
		ResultSet rset = null;
		rset = stmt.executeQuery(Consultas.CONSULTA_LISTAR_SINTOMAS);
		while (rset.next())
	    {
			nombre = rset.getString(2);
			System.out.println(nombre);
		}
		System.out.println();
	}
	
	public static void buscarPatologiasPorNombre (Statement stmt, Connection conn) throws SQLException
	{
		String nombre = null;
		ResultSet rset = null;
		nombre = EntradaSalida.pedirNombre();
		rset = stmt.executeQuery(Consultas.CONSULTA_PATOLOGIAS_POR_NOMBRE+nombre+"')");
		while (rset.next())
	    {
			System.out.println(rset.getInt(1)+". "+rset.getString(2) + "\n");
			System.out.println("Descripción: " + rset.getString(3) + "\n");
			System.out.println("Tratamiento: " + rset.getString(4) + "\n");
			System.out.println("Causa: " + rset.getString(5) + "\n");
		}
	}
	
	public static void buscarSintomasPorNombre (Statement stmt, Connection conn) throws SQLException
	{
		String nombre = null;
		ResultSet rset = null;
		nombre = EntradaSalida.pedirNombre();
		rset = stmt.executeQuery(Consultas.CONSULTA_SINTOMAS_POR_NOMBRE+nombre+"')");
		while (rset.next())
	    {
			System.out.println("Descripción: " + rset.getString(1) + "\n");
		 }
	}
	
	public static void buscarPatologiasPorSintoma (Statement stmt, Connection conn) throws SQLException
	{
		String nombre = null;
		ResultSet rset = null;
		nombre = EntradaSalida.pedirNombre();
		rset = stmt.executeQuery(Consultas.CONSULTA_PATOLOGIAS_POR_SINTOMA+nombre+"'))");
		while (rset.next())
	    {
			System.out.println(rset.getInt(1)+". "+rset.getString(2) + "\n");
			System.out.println("Descripción: " + rset.getString(3) + "\n");
			System.out.println("Tratamiento: " + rset.getString(4) + "\n");
			System.out.println("Causa: " + rset.getString(5) + "\n");
		}
	}
	
	public static void buscarSintomasPorPatologia (Statement stmt, Connection conn) throws SQLException
	{
		String nombre = null;
		ResultSet rset = null;
		nombre = EntradaSalida.pedirNombre();
		rset = stmt.executeQuery(Consultas.CONSULTA_SINTOMAS_POR_PATOLOGIA+nombre+"'))");
		while (rset.next())
	    {
			nombre = rset.getString(1);
			System.out.println(nombre);
		}
		System.out.println();
	}
}