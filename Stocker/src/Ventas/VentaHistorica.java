package Ventas;

import java.util.Vector;

import Articulos.ArticuloHistorico;
import Cliente.Cliente;

public class VentaHistorica{
	
	int idVenta;
	
	double total;
	double pagado;
	String fechaVenta;
	Vector<ArticuloHistorico> articulos;
	
	public VentaHistorica(int idVenta, double total, double pagado, String fechaVenta, Vector<ArticuloHistorico> articulos) {
		this.idVenta = idVenta;
		this.total = total;
		this.pagado = pagado;
		this.articulos = articulos;
		this.fechaVenta = fechaVenta;
	}
	
	
	public double getPrecioTotal() {
		return total;
	}
	public double getPrecioAPagar(Vector<Promocion> promocion) {
		return pagado;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public Vector<ArticuloHistorico> getArticulos() {
		return articulos;
	}

	
}
 