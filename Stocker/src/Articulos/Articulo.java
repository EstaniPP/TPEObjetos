package Articulos;

public abstract class Articulo {
	// descripcion del articulo
	String descripcion;
	// precio por unidad
	double precioUnitario;
	// cantidad en stock
	int stock;
	
	public Articulo(String descripcion, double precioUnitario, int stock) {
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
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
}
