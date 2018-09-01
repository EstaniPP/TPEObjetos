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
	Cliente cliente;
	public VentaHistorica(int idVenta, double total, double pagado, String fechaVenta, Vector<ArticuloHistorico> articulos, Cliente cliente) {
		this.idVenta = idVenta;
		this.total = total;
		this.pagado = pagado;
		this.articulos = articulos;
		this.fechaVenta = fechaVenta;
		this.cliente = cliente;
	}
	
	
	public double getPrecioTotal() {
		return total;
	}
	public double getPrecioAPagar() {
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
	
	public Cliente getCliente() {
		return cliente;
	}

	
}
 