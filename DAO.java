import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connect;
	
	public DAO(Connection conn) {
		this.connect=conn;
	}
	
	public static abstract boolean create(T obj);
	public static abstract T read (Object obj);
	public static abstract T[] readAll ();
	public static abstract boolean update (T obj);
	public static abstract boolean delete(T obj);
}
