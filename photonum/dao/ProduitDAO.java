package photonum.dao;

import java.sql.Connection;
import photonum.objects.Produit;

public class ProduitDAO extends DAO<Produit>{

	public ProduitDAO(Connection conn) {
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
	public Produit[] readAll(Object obj) {
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
