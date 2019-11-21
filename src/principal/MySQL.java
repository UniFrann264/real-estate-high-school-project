package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MySQL {
	
	public String driver = "com.mysql.jdbc.Driver";
    public static String database = "inmobiliaria";
    public static String hostname = "localhost";
    public static String port = "3306";
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    public static String username = "root";
    public static String password = "";
    public static Connection conexion = null;
    public static ResultSet resultado = null;
    public static Statement consulta = null;
    
	public static void open() {
		
		try {
			
		   Class.forName("com.mysql.jdbc.Driver");
		   
		   try {
			   
			   conexion = DriverManager.getConnection(url, username, password);
			   
		   } catch(SQLException e) {
			   
			   JOptionPane.showMessageDialog(null, "¡Error al intentar conectar con la base de datos!" + "\n" + e.getMessage());
			   System.exit(0);
			   
		   } 
		   
		} catch (Exception e) {
			
		   JOptionPane.showMessageDialog(null, "¡Error con el driver MySQL, cerrando programa");
		   System.exit(0);
		   
		}
		
	}
	
	public static Connection getConnection() {
		
		return conexion;
		
	}
	
}
