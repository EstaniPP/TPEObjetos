package Ventas;

import java.util.Vector;

import Cliente.Cliente;

public class VentaHistorica{
	int idVenta;
	double total;
	double pagado;
	
	public VentaHistorica() {
		super();
	}
	public VentaHistorica(String fechaVenta, Cliente cliente) {
		super(fechaVenta, cliente);
	}
	@Override
	public Double getPrecioTotal() {
		return total;
	}
	@Override
	public double getPrecioAPagar(Vector<Promocion> promocion) {
		return pagado;
	}
	
}
 