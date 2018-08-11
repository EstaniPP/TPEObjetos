import java.sql.Date;

public class VentaSinCliente extends Venta{
	static final int IDNOCLIENTE = -1;
	Cliente cliente;
	
	public VentaSinCliente(int idVenta, int tipoVenta, Date fechaventa, Cliente cliente) {
		super(idVenta, tipoVenta, fechaventa, IDNOCLIENTE);
		this.cliente= cliente;
	}
	
	public VentaSinCliente(int tipoVenta, Date fechaventa, Cliente cliente) {
		super(IDVENTANUEVA, tipoVenta, fechaventa, IDNOCLIENTE);
		this.cliente= cliente;
	}
	
	@Override
	public float getPrecioAPagar() {
		return getPrecioTotal();
	}
}
