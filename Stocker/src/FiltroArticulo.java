
public interface FiltroArticulo {
	
	public static class descripcion implements Filtro{
		private String value;
		public descripcion(String value) {
			this.value = value;
		}
		public String getStatement() {
			return " ARTICULOS.descripcion LIKE '%" + value + "%' ";
		}
	}
	public static class idInterno implements Filtro{
		private int value;
		public idInterno(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " ARTICULOS.idInterno = "+ value +" ";
		}
	}
	public static class codigoBarras implements Filtro{
		private String value;
		public codigoBarras(String value) {
			this.value = value;
		}
		public String getStatement() {
			return " ARTICULOS.codigoBarras = "+ value +" ";
		}
	}
	public static class familia implements Filtro{
		private String value;
		public familia(String value) {
			this.value = value;
		}
		public String getStatement() {
			return " ARTICULOS.familia = "+ value +" ";
		}
	}
}
