package photonum.dao;
import java.sql.Connection;

import photonum.objects.PhotoAlbum;

public class PhotoAlbumDAO extends DAO<PhotoAlbum>{
    
    public PhotoAlbumDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(PhotoAlbum obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PhotoAlbum read(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhotoAlbum[] readAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PhotoAlbum obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PhotoAlbum obj) {
		// TODO Auto-generated method stub
		return false;
	}


}
