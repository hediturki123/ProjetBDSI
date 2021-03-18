import java.sql.Connection;

public class Article extends DAO<Article> {

	public Article(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Article read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article[] readAll() {
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
