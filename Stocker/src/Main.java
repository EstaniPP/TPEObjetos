import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		DBManager db = new DBManager();	
		//ResultSet rs = db.query("select * from articulos");
		//while(rs.next()) {
			
		//}
		Articulo a = new Articulo();
		a.setCodigoBarras("asdadas");
		a.setDescripcion("Nuevo producto aniadido x interfaz");
		a.setFamilia(2);
		a.setPrecioUnitario(2.53);
		a.setStock(34);
		
		//db.addArticulo(a);
		
		FiltroArticulo.idInterno f1 = new FiltroArticulo.idInterno(2);
		System.out.println(f1.getStatement());
		System.out.println("EXITO");
	}
}
