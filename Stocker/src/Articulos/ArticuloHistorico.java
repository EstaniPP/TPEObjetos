package Articulos;

public abstract class ArticuloHistorico {
	// descripcion del articulo
	String descripcion;
	// precio por unidad
	double precioUnitario;
	// cantidad en stock
	int stock;
	
	public ArticuloHistorico(String descripcion, double precioUnitario, int stock) {
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
