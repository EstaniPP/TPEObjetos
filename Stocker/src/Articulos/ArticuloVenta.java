package Articulos;

import Ventas.Venta;

public class ArticuloVenta extends Articulo {
	// id del auto increment de la base de datos
	int idInterno;
	// codigo de barras del articulo
	String codigoBarras;
	// familia del articulo. Ejemplo: golosina
	int familia;
	
	public boolean equals(Object o) {
		return idInterno == ((ArticuloVenta) o).getIdInterno();
	}
	
	public ArticuloVenta(int idInterno, String codigoBarras,	String descripcion, int familia, double precioUnitario, int stock) {
		super(descripcion,precioUnitario,stock);
		this.idInterno =idInterno;
		this.codigoBarras = codigoBarras;
		this.familia = familia;
	}
	
	public ArticuloVenta(String codigoBarras, String descripcion, int familia, double precioUnitario, int stock) {
		super(descripcion,precioUnitario,stock);
		this.codigoBarras = codigoBarras;
		this.familia = familia;
	}
	
	public static ArticuloVenta getArticuloError() {
		return new ArticuloVenta(-1, "Error", "Error", -1, 0.0, -1);
	}
	public int getIdInterno() {
		return idInterno;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public void setDescripcion(String descripcion) {
		descripcion = descripcion;
	}
	public int getFamilia() {
		return familia;
	} 
	public void setFamilia(int familia) {
		this.familia = familia;
	}
	public void setPrecioUnitario(double d) {
		precioUnitario = d;
	}
	public void setStock(int stock) {
		stock = stock;
	}
	
	public void update(ArticuloVenta art) {
		this.codigoBarras = art.getCodigoBarras();
		this.descripcion = art.getDescripcion();
		this.familia = art.getFamilia();
		this.precioUnitario = art.getPrecioUnitario();
		this.stock = art.getStock();
	}
	
}
