import java.sql.Connection;

public abstract class DAO<T> {
	public class impression {

		public impression() {
			// TODO Auto-generated constructor stub
		}

	}
	protected Connection connect;
	
	public DAO(Connection conn) {
		this.connect=conn;
	}
	
	public abstract boolean create(T obj);
	public abstract T read (int id);
	public abstract T[] readAll ();
	public abstract boolean update (T obj);
	public abstract boolean delete(T obj);
}
