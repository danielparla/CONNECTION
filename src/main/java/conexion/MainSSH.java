package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class MainSSH {

	private final static String S_PATH_FILE_PRIVATE_KEY = "id_rsa.ppk"; //\\windows absolut path of our ssh private key locally saved
	private final static String S_PATH_FILE_KNOWN_HOSTS = "known_hosts";
	private final static String S_PASS_PHRASE = "mypassphrase";
	private final static int LOCAl_PORT = 3309; 
	private final static int REMOTE_PORT = 3306; 
	private final static int SSH_REMOTE_PORT = 22; 
	private final static String SSH_USER = "587fca0889f5cf057100004b";
	private final static String SSH_REMOTE_SERVER = "femxa-ebtm.rhcloud.com";
	private final static String MYSQL_REMOTE_SERVER = "127.11.220.2";
	private static Session sesion; //represents each ssh session
	private final static String cadena_conexion = "jdbc:mysql://localhost:3309/femxa";
	private final static String user = "adminGXjlxzG";
	private final static String password = "QBShkFDW_69e";
	
	private static void conectate_A_SSH () throws Throwable
	{
		JSch jsch = new JSch();
        jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
        sesion.connect(); //ssh connection established!

        //by security policy, you must connect through a fowarded port          
        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT); 

	}
	
	private static void desconectate_D_SSH ()
	{
		sesion.disconnect();
	}
	
	public static void main(String[] args) throws Throwable {
		
		
		Connection conn = null;
		ResultSet rset = null;
		Statement stmt = null;
		
		try
		{
	
			conectate_A_SSH();
			
			DriverManager.registerDriver (new com.mysql.jdbc.Driver());// método equivalente al anterior
			//Sea como sea, es, <<oye, si te piden una conexión, se la pides a esa clase!>>
			conn = DriverManager.getConnection (cadena_conexion, user, password);
  	        stmt = conn.createStatement();
  	        
		int opcion;
		do
  	      {
  			EntradaSalida.mostrarMenu();
  				opcion = EntradaSalida.pedirOpcion();
  				switch (opcion){
  					case 1:
  						Consultas.listarPatologias(stmt, conn);
  						break;
  					case 2:
  						Consultas.listarSintomas(stmt, conn);
  						break;
  					case 3:
  						Consultas.buscarPatologiasPorNombre(stmt, conn);
  						break;
  					case 4:
  						Consultas.buscarSintomasPorNombre(stmt, conn);
  						break;
  					case 5:
  						Consultas.buscarPatologiasPorSintoma(stmt, conn);
  						break;
  					case 6:
  						Consultas.buscarSintomasPorPatologia(stmt, conn);
  						break;
  					default:
  						break;
  				}
  		}
  		while(opcion!=7);
  	        
			
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
		  	desconectate_D_SSH(); 
		}   

		
	}



}
