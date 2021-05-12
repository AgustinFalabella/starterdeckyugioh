package starterdeck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String url= "jdbc:mysql://localhost:3306/deckyugi?autoReconnect=true&useSSL=false";
	 private static String usuario= "root";
	 private static String password="36344817";
	 
	 
	 public static Connection conectar() {
		 Connection conexion=null;
		 try {
			 conexion= DriverManager.getConnection(url, usuario, password);
			
		} catch (SQLException e) {
		System.out.println("Ocurrio un error al conectar con la base de datos" + e.getMessage());
		}
		 return conexion;
	 }
	
	
	
}
