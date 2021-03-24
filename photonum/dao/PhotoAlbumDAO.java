package photonum.dao;
import java.sql.Connection;

import photonum.objects.PhotoAlbum;

public class PhotoAlbumDAO extends DAO<PhotoAlbum>{
    
    public PhotoAlbumDAO(Connection conn) {
		super(conn);
	}


}
