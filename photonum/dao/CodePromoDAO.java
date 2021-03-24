package photonum.dao;

import java.sql.Connection;

import photonum.objects.CodePromo;

public class CodePromoDAO extends DAO<CodePromo>{
    
    public CodePromoDAO(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}
    
	@Override
	public boolean create(CodePromo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CodePromo read(Object  id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodePromo[] readAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(CodePromo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CodePromo obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
