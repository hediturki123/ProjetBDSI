import java.sql.Connection;

public class Client extends DAO<Client> {

	public Client(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
