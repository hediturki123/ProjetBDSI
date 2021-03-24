import java.sql.Connection;

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
	

}
