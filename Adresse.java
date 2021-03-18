import java.sql.Connection;

public class Adresse extends DAO<Adresse>{

	public Adresse(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Adresse obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adresse read(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Adresse obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Adresse obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adresse[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
