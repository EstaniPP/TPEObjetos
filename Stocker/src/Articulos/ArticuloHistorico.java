package Articulos;

public class ArticuloHistorico {
	// descripcion del articulo
	String descripcion;
	// precio por unidad
	double precioUnitario;
	// cantidad en stock
	int cantidad;
	
	public ArticuloHistorico(String descripcion, double precioUnitario, int cantidad) {
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
	public int getCantidad() {
		return cantidad;
	}
}
