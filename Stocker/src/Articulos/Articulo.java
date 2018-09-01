package Articulos;

import Ventas.Venta;

public class Articulo{
	// id del auto increment de la base de datos
	int idInterno;
	// codigo de barras del articulo
	String codigoBarras;
	// familia del articulo. Ejemplo: golosina
	int familia;
	// descripcion del articulo
	String descripcion;
	// precio por unidad
	double precioUnitario;
	// cantidad en stock
	int stock;
	
	public boolean equals(Object o) {
		return idInterno == ((Articulo) o).getIdInterno();
	}
	
	public Articulo(int idInterno, String codigoBarras,	String descripcion, int familia, double precioUnitario, int stock) {
		this.descripcion=descripcion;
		this.precioUnitario=precioUnitario;
		this.stock=stock;
		this.idInterno =idInterno;
		this.codigoBarras = codigoBarras;
		this.familia = familia;
	}
	
	public Articulo(String codigoBarras, String descripcion, int familia, double precioUnitario, int stock) {
		this.descripcion=descripcion;
		this.precioUnitario=precioUnitario;
		this.stock=stock;
		this.codigoBarras = codigoBarras;
		this.familia = familia;
	}
	
	public static Articulo getArticuloError() {
		return new Articulo(-1, "Error", "Error", -1, 0.0, -1);
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
	public String getDescripcion() {
		return descripcion;
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public int getStock() {
		return stock;
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
	
	public void update(Articulo art) {
		this.codigoBarras = art.getCodigoBarras();
		this.descripcion = art.getDescripcion();
		this.familia = art.getFamilia();
		this.precioUnitario = art.getPrecioUnitario();
		this.stock = art.getStock();
	}
	
	//terminar para valen public ArticuloHistorico 
	
}
