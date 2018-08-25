package Ventas;

import java.util.Vector;

public class VentaHistorica extends ElementoVenta{
	int idVenta;
	double total;
	double pagado;
	@Override
	public Double getPrecioTotal() {
		return total;
	}
	@Override
	public double getPrecioAPagar(Vector<Promocion> promocion) {
		return pagado;
	}
}
 