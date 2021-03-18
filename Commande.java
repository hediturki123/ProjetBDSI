import java.sql.Connection;

public class Commande extends DAO<Commande> {

	public Commande(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
