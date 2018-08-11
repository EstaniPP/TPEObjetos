import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class DBManager {
	private Connection connection;
	private final String dbHost = "localhost";
	private final String dbPort = "3306";
	private final String dbUser = "root";
	private final String dbPassword = "";
	private final String dbName = "stocker";
	
	
	public static void main(String[] args) {
		DBManager db = new DBManager();	
		
		//System.out.print("hola");
	}
	
	public DBManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection ("jdbc:mysql://"+ dbHost +":"+ dbPort +"/"+ dbName +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",""+ dbUser +"", dbPassword);
			//Statement s = connection.createStatement(); 
			//ResultSet rs = s.executeQuery ("select * from CLIENTES");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet dataQuery(String query) {
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void execQuery(String query) {
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(query);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// funcionalidad articulos
	
	public void addArticulo(Articulo a) {
		String query = "INSERT INTO `ARTICULOS` "
				+ "(`idInterno`, `codigoBarras`, `descripcion`, `familia`, `precioUnitario`, `stock`) "
				+ "VALUES (NULL, '" + a.getCodigoBarras() + "', '" + a.getDescripcion() + "', '" + a.getFamilia()+ "', '" + a.getPrecioUnitario() + "', '" + a.getStock() + "');";
		// ejecuto la consulta
		this.execQuery(query);
	}
	
	public Vector<Articulo> getArticulos(Filtro f) throws SQLException{
		String query = "SELECT * FROM ARTICULOS ";
		if(f != null) {
			query += "WHERE " + f.getStatement();
		}
		Vector<Articulo> vTemp = new Vector<Articulo>();
		ResultSet rs = dataQuery(query);
		while(rs.next()) {
			Articulo a = new Articulo();
			a.setCodigoBarras(rs.getString("codigoBarras"));
			a.setDescripcion(rs.getString("descripcion"));
			a.setFamilia(rs.getInt("familia"));
			a.setPrecioUnitario(rs.getDouble("precioUnitario"));
			a.setStock(rs.getInt("stock"));
			vTemp.add(a);
		}
		return vTemp;
	}
	
	
}
