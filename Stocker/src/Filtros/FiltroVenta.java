import java.sql.Date;

public interface FiltroVenta {
	
	public static class idVenta implements Filtro{
		private int value;
		public idVenta(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " VENTAS.idVenta = "+ value + " ";
		}
	}
	public static class idClienteVenta implements Filtro{
		private int value;
		public idClienteVenta(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " VENTAS.idClienteVenta = "+ value + " ";
		}
	}
	public static class tipoVenta implements Filtro{
		private int value;
		public tipoVenta(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " VENTAS.tipoVenta = "+ value + " ";
		}
	}
	public static class totalIgual implements Filtro{
		private int value;
		public totalIgual(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " VENTAS.total = "+ value + " ";
		}
	}
	public static class totalRango implements Filtro{
		private int value1;
		private int value2;
		
		public totalRango(int value1, int value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
		public String getStatement() {
			return " VENTAS.total BETWEEN "+ value1 + " AND "+ value2+" ";
		}
	}
	public static class pagadoIgual implements Filtro{
		private int value;
		public pagadoIgual(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " VENTAS.pagado = "+ value + " ";
		}
	}
	public static class pagadoRango implements Filtro{
		private int value1;
		private int value2;
		
		public pagadoRango(int value1, int value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
		public String getStatement() {
			return " VENTAS.pagado BETWEEN "+ value1 + " AND "+ value2+" ";
		}
	}
	public static class fechaVentaIgual implements Filtro{
		private Date value;
		public fechaVentaIgual(Date value) {
			this.value = value;
		}
		public String getStatement() {
			return " VENTAS.fechaVenta = "+ value + " ";
		}
	}
	public static class fechaVentaRango implements Filtro{
		private Date value1;
		private Date value2;
		
		public fechaVentaRango(Date value1, Date value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
		public String getStatement() {
			return " VENTAS.fechaVenta BETWEEN "+ value1 + " AND "+ value2+" ";
		}
	}
	
}