
public interface FiltroCompuesto {
	public static class And implements Filtro{
		Filtro f1;
		Filtro f2;
		public And(Filtro f1, Filtro f2) {
			this.f1 = f1;
			this.f2 = f2;
		}
		public String getStatement() {
			String query = " (" + f1.getStatement() + " AND " + f2.getStatement() +") ";
			return query;
		}
	}
	public static class Or implements Filtro{
		Filtro f1;
		Filtro f2;
		public Or(Filtro f1, Filtro f2) {
			this.f1 = f1;
			this.f2 = f2;
		}
		public String getStatement() {
			String query = " (" + f1.getStatement() + " OR " + f2.getStatement() + ") ";
			return query;
		}
	}
}
