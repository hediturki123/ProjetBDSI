package photonum.dao;
import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	protected Connection connect;
	/**
	 * <ul>
	 * 	<li>permet de construire un objet DAO avec la connection a la BD (conn)
	 *
	 * @param Connection conn
	 */
	public DAO(Connection conn) {
		this.connect=conn;
	}
	
	public abstract boolean create(T obj);
	public abstract T read (Object obj);
	public abstract List<T> readAll ();
	public abstract boolean update (T obj);
	public abstract boolean delete(T obj);
}
