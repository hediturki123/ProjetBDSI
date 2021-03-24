package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import photonum.objects.Photo;
import photonum.objects.PhotoParPage;

public class PhotoParPageDAO extends DAO<PhotoParPage> {
    
    public PhotoParPageDAO(Connection conn) {
		super(conn);
	}

    
	@Override
	public boolean create(Photo obj){
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesPhotosParPages VALUES (?,?)"
			);
			requeteCreate.setInt(1,obj.getIdPhoto());
			requeteCreate.setInt(2,getIdPhoto());
			int reussi=requeteCreate.executeUpdate();
			requeteCreate.close();
			return reussi==1;
		} catch (Exception e) {
			//TODO: handle exception
		}
		return false;
	}

	@Override
	public PhotoParPage read(Object id) {
		return null;
	}

	@Override
	public Photo[] readAll(Object obj) {
		return null;
	}
	
	@Override
	public boolean update(Photo obj) {
		return false;
	}

}
