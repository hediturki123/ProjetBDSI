package photonum;
import java.sql.Connection;

public class Produit extends DAO<Produit>{

	public Produit(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
		
	}

	@Override
	public boolean create(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Produit read(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit[] readAll(Object object) {
		// TODO Auto-generated method st
		return null;
	}

	@Override
	public boolean update(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
