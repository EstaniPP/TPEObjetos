import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Articulos.Articulo;
import DataBase.DBManager;
import Filtros.FiltroArticulo;
import Filtros.FiltroCompuesto;

public class Main {
	public static void main(String[] args) throws SQLException {
		DBManager db = new DBManager();	

		FiltroArticulo.descripcion f2 = new FiltroArticulo.descripcion("FSDFDF");
		FiltroArticulo.descripcion f3 = new FiltroArticulo.descripcion("khkjkgj");
		FiltroCompuesto.Or f4 = new FiltroCompuesto.Or(f2, f3);
		Vector<Articulo> va = db.getArticulos(f4);
		for(Articulo a : va) {
			System.out.println(a.getDescripcion());
		}
		System.out.println(va.size());
	}
}
