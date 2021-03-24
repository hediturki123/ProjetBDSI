package photonum;
import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connect;
	
	public DAO(Connection conn) {
		this.connect=conn;
	}
	
	public abstract boolean create(T obj);
	public abstract T read (Object obj);
	public abstract T[] readAll (Object obj);
	public abstract boolean update (T obj);
	public abstract boolean delete(T obj);
}
