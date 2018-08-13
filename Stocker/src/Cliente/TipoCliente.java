package Cliente;
import java.util.HashMap;

public class TipoCliente {
	int idTipoCliente;
	//descuento asociado a ese tipo de cliente
	double descuento;
	//nombre asociado a ese tipo de cliente. Ej Minorista, mayorista.
	String nombreTipoCliente;
	
	public TipoCliente(int idTipoCliente, double descuento, String nombreTipoCliente) {
		 this.idTipoCliente = idTipoCliente;
		 this.descuento = descuento;
		 this.nombreTipoCliente = nombreTipoCliente;
	}
	public TipoCliente(int descuento, String nombreTipoCliente) {
		 this.descuento = descuento;
		 this.nombreTipoCliente = nombreTipoCliente;
	}
	public int getIdTipoCliente() {
		return idTipoCliente;//
	}
	
	public String getNombreTipoCliente() {
		return nombreTipoCliente;
	}
	
	public double getDescuento() {
		return descuento;
	}
}
