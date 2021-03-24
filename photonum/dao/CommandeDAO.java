package photonum.dao;

import java.sql.Connection;
import java.util.List;

import photonum.objects.Commande;

public class CommandeDAO extends DAO<Commande>{
	public CommandeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Commande obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande read(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> readAll() {
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
