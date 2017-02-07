package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class BuscarPatologiaPorID {

	static Connection conn;
	static Statement stmt;
	static ResultSet rset;
	
	static int id_patologia;
	static String nombre_patologia;
	static String descripcion_patologia;
	static String tratamiento_patologia;
	
	static String des_sint;
	static int id_sint;
	static ArrayList<SintomasDTO>lista_sintomas;
	
	public static ArrayList<PatologiaDTO> buscarPatologiaPorID(int id) {
		
	ArrayList<PatologiaDTO> lista_patologias = new ArrayList<PatologiaDTO>();

	final String S_PATH_FILE_PRIVATE_KEY = "id_rsa.ppk"; 
	final String S_PATH_FILE_KNOWN_HOSTS = "known_hosts";
	final String S_PASS_PHRASE = "mypassphrase";
	final int LOCAl_PORT = 3309;
	final int REMOTE_PORT = 3306;
	final int SSH_REMOTE_PORT = 22;
	final String SSH_USER = "587fca0889f5cf057100004b";
	final String SSH_REMOTE_SERVER = "femxa-ebtm.rhcloud.com";
	final String MYSQL_REMOTE_SERVER = "127.11.220.2";
	Session sesion = null; // represents each ssh session
	final String cadena_conexion = "jdbc:mysql://localhost:3309/femxa";
	final String user = "adminGXjlxzG";
	final String password = "QBShkFDW_69e";

		try {
			JSch jsch = new JSch();
			jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
			jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());

			sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
			sesion.connect(); // ssh connection established!

			// by security policy, you must connect through a fowarded port
			sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(cadena_conexion, user, password);
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery("SELECT des_sint FROM Sintomas WHERE id_sint IN (SELECT id_sint FROM Causas WHERE id_patol ="+id+")");
			
			lista_sintomas= new ArrayList<SintomasDTO>();
			id_sint=1;
			
			while (rset.next()) {
				des_sint = rset.getString("des_sint");
			
				SintomasDTO sintoma = new SintomasDTO(id_sint, des_sint);
				lista_sintomas.add(sintoma);
				id_sint++;
			}
			
			rset = stmt.executeQuery("SELECT * FROM Patologias WHERE id_patol = "+id);
			
			while (rset.next()) {
				nombre_patologia = rset.getString("nom_patol");
				id_patologia = rset.getInt("id_patol");
				descripcion_patologia = rset.getString("des_patol");
				tratamiento_patologia = rset.getString("trat_patol");

				PatologiaDTO patologia = new PatologiaDTO(id_patologia, nombre_patologia, descripcion_patologia,
						tratamiento_patologia, lista_sintomas);
				lista_patologias.add(patologia);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally // libero recursos, de "adentro a fuera" , ResultSet,
					// Statment, Conexion
		{
			if (rset != null) {
				try {
					rset.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
			sesion.disconnect();
		}
		return lista_patologias;
	}
	
}
