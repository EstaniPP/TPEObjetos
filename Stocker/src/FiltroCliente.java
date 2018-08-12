
public interface FiltroCliente {

	public static class idCliente implements Filtro{
		private int value;
		public idCliente(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " CLIENTES.idCliente = "+ value + " ";
		}
	}
	public static class tipoCliente implements Filtro{
		private int value;
		public tipoCliente(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " CLIENTES.tipoCliente = "+ value + " ";
		}
	}
	public static class telefono implements Filtro{
		private String value;
		public telefono(String value) {
			this.value = value;
		}
		public String getStatement() {
			return " CLIENTES.telefono = '%" + value +"%' ";
		}
	}
	public static class nombre implements Filtro{
		private String value;
		public nombre(String value) {
			this.value = value;
		}
		public String getStatement() {
			return " CLIENTES.nombre LIKE '%" + value +"%' ";
		}
	}
	public static class email implements Filtro{
		private String value;
		public email(String value) {
			this.value = value;
		}
		public String getStatement() {
			return " CLIENTES.email = "+ value +" ";
		}
	}
	
}
