import java.sql.Connection;

public class Page extends DAO<Page> {

	public Page(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Page obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Page obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Page obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
