import java.sql.Connection;

public class Impression extends DAO<Impression>{

	public Impression(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Impression obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Impression read(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Impression obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Impression obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Impression[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
