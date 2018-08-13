package Ventas;
import java.sql.Date;

import Cliente.Cliente;

public class VentaConCliente extends Venta{	
	Cliente cliente;
	
	public VentaConCliente(int idVenta, int tipoVenta, Date fechaventa, int idClienteVenta, Cliente cliente) {
		super(idVenta, tipoVenta, fechaventa, idClienteVenta);
		this.cliente= cliente;
	}
	
	public VentaConCliente(int tipoVenta, Date fechaventa, int idClienteVenta, Cliente cliente) {
		super(IDVENTANUEVA, tipoVenta, fechaventa, idClienteVenta);
		this.cliente= cliente;
	}
	
	@Override
	public float getPrecioAPagar() {
		return 0;
	}
}