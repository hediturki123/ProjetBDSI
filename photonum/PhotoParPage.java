package photonum;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PhotoParPage extends Photo {
	private int idPage;

	public PhotoParPage(Connection conn) {
		super(conn);
	}

	public PhotoParPage(Connection conn, int idPhoto, String chemin, int idPage) {
		super(conn, idPhoto, chemin);
		setIdPage(idPage);
	}


	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
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
