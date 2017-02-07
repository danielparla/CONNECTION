package conexion;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Daos {
	
	static Connection conn = null;
	static ResultSet rset = null;
	static Statement stmt = null;
	
	public static Statement conexion() throws JSchException{
		
		final String S_PATH_FILE_PRIVATE_KEY = "id_rsa.ppk"; //\\windows absolut path of our ssh private key locally saved
		final String S_PATH_FILE_KNOWN_HOSTS = "known_hosts";
		final String S_PASS_PHRASE = "mypassphrase";
		final int LOCAl_PORT = 3309; 
		final int REMOTE_PORT = 3306; 
		final int SSH_REMOTE_PORT = 22; 
		final String SSH_USER = "587fca0889f5cf057100004b";
		final String SSH_REMOTE_SERVER = "femxa-ebtm.rhcloud.com";
		final String MYSQL_REMOTE_SERVER = "127.11.220.2";
		Session sesion = null; //represents each ssh session
		final String cadena_conexion = "jdbc:mysql://localhost:3309/femxa";
		final String user = "adminGXjlxzG";
		final String password = "QBShkFDW_69e";
		
		try{
		JSch jsch = new JSch();
        jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
        sesion.connect(); //ssh connection established!

        //by security policy, you must connect through a fowarded port          
        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT); 
        
        DriverManager.registerDriver (new com.mysql.jdbc.Driver());// método equivalente al anterior
		//Sea como sea, es, <<oye, si te piden una conexión, se la pides a esa clase!>>
		Connection conn = DriverManager.getConnection (cadena_conexion, user, password);
	        Statement stmt = conn.createStatement();
        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally //libero recursos, de "adentro a fuera" , ResultSet, Statment, Conexion
		{
			if (rset != null) 	{ try { rset.close(); } catch (Exception e2) { e2.printStackTrace(); }}
			if (stmt != null)	{ try {	stmt.close(); } catch (Exception e2) { e2.printStackTrace(); }}
			if (conn != null) 	{ try { conn.close(); } catch (Exception e3) { e3.printStackTrace(); }}
		  	sesion.disconnect();
		}   
		return stmt;
	}
	
	
	
	
	public static List<PatologiaDTO> lista_patologias() throws SQLException, JSchException{
		
		int id_patologia= 0;
		String nombre_patologia=null;
		String descripcion_patologia=null;
		String tratamiento_patologia=null;
		List<PatologiaDTO> lista_patologias=new ArrayList<PatologiaDTO>();
		Statement stmt= conexion();
		rset = stmt.executeQuery(Consultas.CONSULTA_LISTAR_PATOLOGIAS);
		while (rset.next())
	    {
			nombre_patologia = rset.getString("nom_patol");
			id_patologia= rset.getInt("id_patol");
			descripcion_patologia= rset.getString("des_patol");
			tratamiento_patologia= rset.getString("trat_patol");
			
			PatologiaDTO patologia=new PatologiaDTO(id_patologia, nombre_patologia, descripcion_patologia, tratamiento_patologia);
			lista_patologias.add(patologia);
		}
		
	return null;
		
	}
	
	public static void listarSintomas (Statement stmt, Connection conn) throws SQLException
	{//TODO
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
	
	
}
