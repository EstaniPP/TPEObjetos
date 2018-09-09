package DataBase;
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

import Articulos.Articulo;
import Articulos.ArticuloHistorico;
import Articulos.FamiliaArticulo;
import Cliente.Cliente;
import Cliente.TipoCliente;
import Filtros.Filtro;
import Filtros.FiltroCliente;
import Ventas.Promocion;
import Ventas.Venta;
import Ventas.VentaHistorica;

public class DBManager {
	private Connection connection;
	private final String dbHost = "localhost";
	private final String dbPort = "3306";
	private final String dbUser = "root";
	private final String dbPassword = "";
	private final String dbName = "stocker";
	private boolean conectado;
	
	
	public DBManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection ("jdbc:mysql://"+ dbHost +":"+ dbPort +"/"+ dbName +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",""+ dbUser +"", dbPassword);
			conectado = true;
		}catch(Exception e) {
			e.printStackTrace();
			conectado = false;
		}
	}
	
	public boolean conexionExitosa() {
		return conectado;
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
		this.execQuery(query);
	}
	 
	public void updateArticulo(Articulo a) {
		String query = "UPDATE `ARTICULOS` SET "
				+ "`codigoBarras` = '" + a.getCodigoBarras() + "', "
				+ "`descripcion` = '"+ a.getDescripcion() +"', "
				+ "`familia` = '" + a.getFamilia() + "', "
				+ "`stock` = '" + a.getStock() + "', "
				+ "`precioUnitario` = '" + a.getPrecioUnitario() + "' "
				+ "WHERE `ARTICULOS`.`idInterno` = " + a.getIdInterno() + ";";
		this.execQuery(query);
	}
	
	public void deleteArticulo(Articulo a) throws SQLException {
		String query = "DELETE FROM `ARTICULOS` WHERE `idInterno` = " + a.getIdInterno() + ";";
		this.execQuery(query);
	}
	
	public Vector<Articulo> getArticulos(Filtro f) throws SQLException{
		String query = "SELECT * FROM ARTICULOS ";
		if(f != null) {
			query += "WHERE" + f.getStatement();
		}
		Vector<Articulo> vTemp = new Vector<Articulo>();
		ResultSet rs = dataQuery(query);
		while(rs.next()) {
			Articulo a = new Articulo(rs.getInt("idInterno"), rs.getString("codigoBarras"), rs.getString("descripcion"), rs.getInt("familia"), rs.getDouble("precioUnitario"), rs.getInt("stock"));
			vTemp.add(a);
		}
		return vTemp;
	}
	
	//fin funcionalidad articulo
	
	
	
	
	// funcionalidad clientes
	
	public Vector<Cliente> getClientes(Filtro f) throws SQLException{
		// Cliente(int idCliente, String nombre, String telefono, String email, int tipoCliente) 
		String query = "SELECT * FROM CLIENTES ";
		if(f != null) {
			query += "WHERE" + f.getStatement();
		}
		Vector<Cliente> vTemp = new Vector<Cliente>();
		//System.out.println(query);
		ResultSet rs = dataQuery(query);
		while(rs.next()) {
			Cliente a = new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"), rs.getInt("tipoCliente"));
			vTemp.add(a);
		}
		return vTemp;
	}
	
	public Cliente getClienteById(int idCliente) throws SQLException {
		FiltroCliente.idCliente f1 = new FiltroCliente.idCliente(idCliente);
		Vector<Cliente> vect = getClientes(f1);
		if(vect.size() != 0) {
			return vect.elementAt(0);
		}else {
			return Cliente.getClienteError();
		}
	}

	public void addCliente(Cliente c) {
		String query = "INSERT INTO `CLIENTES` (`idCliente`, `nombre`, `telefono`, `email`, `tipoCliente`) VALUES "
				+ "(NULL, "
				+ "'" + c.getNombre() + "', "
				+ "'" + c.getTelefono() + "', "
				+ "'" + c.getEmail() + "', "
				+ "'" + c.getTipoCliente() + "');";
		this.execQuery(query);
	}
	
	public void updateCliente(Cliente c) {
		String query = "UPDATE `CLIENTES` SET "
				+ "`nombre` = '" + c.getNombre() + "', "
				+ "`telefono` = '"+c.getTelefono()+"', "
				+ "`email` = '" + c.getEmail() + "', "
				+ "`tipoCliente` = '" + c.getTipoCliente() + "' "
				+ "WHERE `CLIENTES`.`idCliente` = " + c.getIdCliente() + ";";
		this.execQuery(query);
	}
	public void deleteCliente(Cliente c) throws SQLException {
		execQuery("DELETE FROM `CLIENTES` WHERE `CLIENTES`.`idCliente` = " + c.getIdCliente() + "");
		execQuery("UPDATE `VENTAS` SET "
				+ "`idClienteVenta` = '" + "NULL" + "' "
				+ "WHERE `VENTAS`.`idClienteVenta` = " + c.getIdCliente()+ ";");
	}
	
	//fin funcionalidad clientes
	
	
	
	
	// funcionalidad tipo de clientes
	
	public void updateTipoCliente(TipoCliente c) {
		String query = "UPDATE `TIPOCLIENTE` SET "
				+ "`nombreTipo` = '" + c.getNombreTipoCliente()+ "', "
				+ "`porcentajeDescuento` = '"+ c.getDescuento() +"' "
				+ "WHERE `TIPOCLIENTE`.`idInterno` = " + c.getIdTipoCliente()+ ";";
		this.execQuery(query);
	}
	
	public Vector<TipoCliente> getTiposCliente(){
		Vector<TipoCliente> vect = new Vector<TipoCliente>();
		
		try {
			ResultSet rs = dataQuery("SELECT * FROM TIPOCLIENTE ORDER BY idInterno ASC");
			while(rs.next()) {
				TipoCliente tTemp = new TipoCliente(rs.getInt("idInterno"), rs.getDouble("porcentajeDescuento"), rs.getString("nombreTipo"));
				vect.add(tTemp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(vect.size() == 0) {
			vect.add(new TipoCliente(-1, 0, "Sin resultados"));
		}
		return vect;
	}
	
	public void deleteTipoCliente(TipoCliente c) throws SQLException {
		execQuery("DELETE FROM `TIPOCLIENTE` WHERE `TIPOCLIENTE`.`idInterno` = " + c.getIdTipoCliente() + "");
	}
	
	public TipoCliente getTipoCliente(int tipo) {
		try {
			ResultSet rs = dataQuery("SELECT * FROM TIPOCLIENTE WHERE idInterno = '" + tipo + "'");
			if(rs.next()) {
				TipoCliente tTemp = new TipoCliente(rs.getInt("idInterno"), rs.getDouble("porcentajeDescuento"), rs.getString("nombreTipo"));
				return tTemp;
			}else {
				TipoCliente tTemp = new TipoCliente(-1, 0, "Sin resultados");
				return tTemp;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addTipoCliente(TipoCliente c) {
		String query = "INSERT INTO `TIPOCLIENTE` (`idInterno`, `nombreTipo`, porcentajeDescuento) VALUES "
				+ "(NULL, "
				+ "'" + c.getNombreTipoCliente() + "',"
				+ c.getDescuento() + ");";
		this.execQuery(query);
	}
	
	
	//fin funcionalidad tipos de cliente
	
	
	
	//funcionalidad promociones
	
	public Vector<Promocion> getPromociones(){
		Vector<Promocion> vect = new Vector<Promocion>();
		try {
			ResultSet rs = dataQuery("SELECT * FROM PROMOCIONES ORDER BY idPromocion ASC");
			while(rs.next()) {
				Promocion tTemp = new Promocion(rs.getInt("idPromocion"), this.getFamiliaArticulo(rs.getInt("idFamilia")), rs.getDouble("descuento"));
				vect.add(tTemp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return vect;
	}
	
	public void updatePromocion(Promocion c) {
		String query = "UPDATE `PROMOCIONES` SET "
				+ "`idPromocion` = '" + c.getIdPromocion() + "', "
				+ "`idFamilia` = '"+ c.getFamilia().getIdFamilia() +"', "
				+ "`descuento` = '" + c.getDescuento() + "' "
				+ "WHERE `PROMOCIONES`.`idPromocion` = " + c.getIdPromocion() + ";";
		this.execQuery(query);
	}
	
	
	public void addPromocion(Promocion promo) {
		String query = "INSERT INTO `PROMOCIONES` (`idPromocion`, `idFamilia`, `descuento`) VALUES "
				+ "(NULL, "
				+ "'" + promo.getFamilia().getIdFamilia() + "', "
				+ "'" + promo.getDescuento() + "');";
		// ejecuto la consulta
		this.execQuery(query);
	}
	
	public void deletePromocion(Promocion c) throws SQLException {
		execQuery("DELETE FROM `PROMOCIONES` WHERE `PROMOCIONES`.`idPromocion` = " + c.getIdPromocion() + "");
	}
	//fin funcionalidad promociones
	
	
	
	
	// Funcionalidad familias de articulos
	
	public Vector<FamiliaArticulo> getFamilias(){
		Vector<FamiliaArticulo> vect = new Vector<FamiliaArticulo>();
		try {
			ResultSet rs = dataQuery("SELECT * FROM FAMILIA");
			while(rs.next()) {
				FamiliaArticulo fTemp = new FamiliaArticulo(rs.getInt("idFamilia"), rs.getString("nombreFamilia"));
				vect.add(fTemp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(vect.size()==0) {
			FamiliaArticulo fTemp2 = new FamiliaArticulo(-1, "Ninguna");
			vect.add(fTemp2);
		}
		return vect;
	}

	public FamiliaArticulo getFamiliaArticulo(int tipo) {
		try {
			ResultSet rs = dataQuery("SELECT * FROM FAMILIA WHERE idFamilia = '" + tipo + "'");
			FamiliaArticulo fTemp = null;
			if(rs.next()) {
				fTemp = new FamiliaArticulo(rs.getInt("idFamilia"), rs.getString("nombreFamilia"));
			}else {
				fTemp = new FamiliaArticulo(tipo,"Sin familia");
			}
			return fTemp;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateFamilia(FamiliaArticulo a) {
		String query = "UPDATE `FAMILIA` SET "
				+ "`nombreFamilia` = '"+ a.getNombreFamilia() +"' "
				+ "WHERE `FAMILIA`.`idFamilia` = " + a.getIdFamilia() + ";";
		this.execQuery(query);
	}
	
	public void deleteFamilia(FamiliaArticulo a) throws SQLException {
		String query = "DELETE FROM `FAMILIA` WHERE `idFamilia` = " + a.getIdFamilia() + ";";
		this.execQuery(query);
	}
	
	public void addFamiliaArticulo(FamiliaArticulo c) {
		String query = "INSERT INTO `FAMILIA` (`idFamilia`, `nombreFamilia`) VALUES "
				+ "(NULL, "
				+ "'" + c.getNombreFamilia() + "');";
		// ejecuto la consulta
		this.execQuery(query);
	}
	
	//Fin funcionalidad familia de articulos
	 
	
	
	
	// inicio funcionalidad de ventas
	// retorna la venta insertada con el id actualizado
	public VentaHistorica addVenta(Venta v) throws SQLException {
		Vector<Promocion> vP = this.getPromociones();
		
		String query = "INSERT INTO `VENTAS` (`idVenta`, `total`, `pagado`, `idClienteVenta`, `fechaVenta`) VALUES "
				+ "(NULL, "
				+ "'" + v.getPrecioTotal() + "', "
				+ "'" + v.getPrecioAPagar(vP) + "', "
				+ "'" + v.getCliente().getIdCliente() + "', "
				+ "'" + v.getFechaVenta() + "');";
		this.execQuery(query);
		
		ResultSet q = this.dataQuery("SELECT LAST_INSERT_ID() AS lid FROM VENTAS GROUP BY lid");
		int lastID = -1;
		if(q.next()) {
			lastID = q.getInt("lid");
		}
		
		for(Articulo a : v.getArticulos()) {
			String qArts = "INSERT INTO `PRODUCTOSVENTA` (`idVentaProd`, `idVentaForeign`, `descripcionArticulo`, `precioArticulo`, `cantidad`) VALUES (NULL, "
					+ "'" + lastID + "', "
					+ "'" + a.getDescripcion() + "', "
					+ "'" + a.getPrecioUnitario() + "', "
					+ "'" + v.getCantidadArticulo(a) + "');";
			this.execQuery(qArts);
			// descontar stock
			//
			a.setStock(a.getStock() - v.getCantidadArticulo(a));
			this.updateArticulo(a);
			
		}
		v.setIdVenta(lastID);
		return v.getHistorica(vP);
		
	}
	

	public Vector<VentaHistorica> getVentas(Filtro f) throws SQLException{
		String query = "SELECT * FROM VENTAS ";
		if(f != null) {
			query += "WHERE " + f.getStatement();
		}
		ResultSet rs = this.dataQuery(query);
		Vector<VentaHistorica> vH = new Vector<VentaHistorica>();
		while(rs.next()) {
			// obtengo los productos de la venta
			String q2 = "SELECT * FROM PRODUCTOSVENTA WHERE idVentaForeign = '" + rs.getInt("idVenta") + "'";
			ResultSet rsProd = this.dataQuery(q2);
			// get cliente venta
			FiltroCliente.idCliente filC = new FiltroCliente.idCliente(rs.getInt("idClienteVenta"));
			
			Cliente cV = this.getClientes(filC).elementAt(0);
			Vector<ArticuloHistorico> arts = new Vector<ArticuloHistorico>();
			while(rsProd.next()) {
				ArticuloHistorico art = new ArticuloHistorico(rsProd.getString("descripcionArticulo"), rsProd.getDouble("precioArticulo"), rsProd.getInt("cantidad"));
				arts.add(art);
			}
			//System.out.println(arts.size());
			// creo la venta
			// VentaHistorica(int idVenta, double total, double pagado, String fechaVenta, Vector<ArticuloHistorico> articulos, Cliente cliente)
			vH.add(new VentaHistorica(rs.getInt("idVenta"), rs.getDouble("total"), rs.getDouble("pagado"), rs.getString("fechaVenta"), arts, cV));
		}
		return vH;
	}
	
	
}
