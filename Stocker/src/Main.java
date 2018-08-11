import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws SQLException {
		DBManager db = new DBManager();	
		//ResultSet rs = db.query("select * from articulos");
		//while(rs.next()) {
			
		//}
		Vector<Articulo> va = db.getArticulos(null);
		for(Articulo a : va) {
			System.out.println(a.getCodigoBarras());
		}
		
		
		//db.addArticulo(a);
		
		//FiltroArticulo.idInterno f1 = new FiltroArticulo.idInterno(2);
		//System.out.println(f1.getStatement());
		System.out.println("EXITO");
	}
}
