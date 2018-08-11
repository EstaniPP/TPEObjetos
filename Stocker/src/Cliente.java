
public class Cliente {
	// id del auto increment de la base de datos
	int idCliente;
	//nombre del cliente
	String nombre;
	//telefono del cliente
	String telefono;
	//email del cliente
	String email;
	//tipo de cliente que es. Ejemplo 1(minorista)
	int tipoCliente;
	
	public Cliente(int idCliente, String nombre, String telefono, String email, int tipoCliente) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.telefono = nombre;
		this.email = email;
		this.tipoCliente = tipoCliente;
	}
	@Override
	public boolean equals(Object obj) {
		Cliente aux = (Cliente)obj;
		return (this.idCliente== aux.getIdCliente());
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public int getTipoCliente() {
		return tipoCliente;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	
}
