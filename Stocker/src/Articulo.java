
public class Articulo {
	// id del auto increment de la base de datos
	int idInterno;
	// codigo de barras del articulo
	String codigoBarras;
	// descripcion del articulo
	String descripcion;
	// familia del articulo. Ejemplo: golosina
	int familia;
	// precio por unidad
	double precioUnitario;
	// cantidad en stock
	int stock;
	
	public Articulo(int idInterno, String codigoBarras,	String descripcion, int familia, double precioUnitario, int stock) {
		this.idInterno =idInterno;
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.familia = familia;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
	}
	
	public Articulo(String codigoBarras,	String descripcion, int familia, double precioUnitario, int stock) {
		this.codigoBarras = codigoBarras;
		this.descripcion = descripcion;
		this.familia = familia;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getFamilia() {
		return familia;
	}
	public void setFamilia(int familia) {
		this.familia = familia;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double d) {
		this.precioUnitario = d;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
