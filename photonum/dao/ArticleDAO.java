package photonum.dao;

import java.sql.Connection;

import photonum.objects.Article;

public class ArticleDAO  extends DAO<Article> {

    public ArticleDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Article read(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article[] readAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
