import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		DBManager db = new DBManager();	
		ResultSet rs = db.query("select * from articulos");
		while(rs.next()) {
			System.out.println(rs.getString("codigoBarras"));
		}
	}
}
