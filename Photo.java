import java.sql.Connection;

public class Photo extends DAO<Photo> {

	public Photo(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Photo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Photo read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Photo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Photo obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
