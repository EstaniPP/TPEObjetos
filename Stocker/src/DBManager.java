import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class DBManager {
	private Connection connection;
	private String dbHost = "localhost";
	private String dbPort = "3307";
	private String dbUser = "root";
	private String dbPassword = "";
	private String dbName = "stocker";
	public DBManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection ("jdbc:mysql://"+ dbHost +":"+ dbPort +"/"+ dbName +"",""+ dbUser +"", dbPassword);
			Statement s = connection.createStatement(); 
			ResultSet rs = s.executeQuery ("select * from CLIENTES");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet query(String query) {
		try {
			Statement s = connection.createStatement();
			return s.executeQuery(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
