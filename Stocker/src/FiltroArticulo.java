
public interface FiltroArticulo {
	public static class idInterno implements Filtro{
		private int value;
		public idInterno(int value) {
			this.value = value;
		}
		public String getStatement() {
			return " idInterno = "+ value +" ";
		}
	}
}
