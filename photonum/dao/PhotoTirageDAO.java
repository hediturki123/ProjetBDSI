package photonum.dao;

import java.sql.Connection;
import java.util.List;

import photonum.objects.PhotoTirage;

public class PhotoTirageDAO extends DAO<PhotoTirage>{
	
	public PhotoTirageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(PhotoTirage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PhotoTirage read(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhotoTirage> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PhotoTirage obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PhotoTirage obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
