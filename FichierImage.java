import java.sql.Connection;

public class FichierImage extends DAO<FichierImage> {

	public FichierImage(Connection conn) {
		// TODO Auto-generated constructor stub
		super(conn);
	}

	@Override
	public boolean create(FichierImage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FichierImage read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(FichierImage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(FichierImage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FichierImage[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
