import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws SQLException {
		DBManager db = new DBManager();	
		
		FiltroArticulo.idInterno f1 = new FiltroArticulo.idInterno(2);
		FiltroArticulo.descripcion f2 = new FiltroArticulo.descripcion("des");
		Vector<Articulo> va = db.getArticulos(f2);
		for(Articulo a : va) {
			System.out.println(a.getCodigoBarras());
		}
		
		System.out.println("EXITO");
	}
}
