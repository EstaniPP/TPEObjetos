import java.util.HashMap;

public class tipoCliente {
	int idTipoCliente;
	//descuento asociado a ese tipo de cliente
	int descuento;
	//nombre asociado a ese tipo de cliente. Ej Minorista, mayorista.
	String nombreTipoCliente;
	
	public tipoCliente(int idTipoCliente, int descuento, String nombreTipoCliente) {
		 this.idTipoCliente = idTipoCliente;
		 this.descuento = descuento;
		 this.nombreTipoCliente = nombreTipoCliente;
	}
	 
	public int getIdTipoCliente() {
		return idTipoCliente;
	}
	
	public String getNombreTipoCliente() {
		return nombreTipoCliente;
	}
	
	public int getDescuento() {
		return descuento;
	}
}
