package photonum;
import java.sql.Connection;

public class PhotoAlbum extends Photo{
	private String textDescrptif;

	public PhotoAlbum(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public PhotoAlbum(Connection conn, int idPhoto, String chemin, String textDescrptif) {
		super(conn, idPhoto, chemin);
		setTextDescrptif(textDescrptif);
	}

	public String getTextDescrptif() {
		return textDescrptif;
	}

	public void setTextDescrptif(String textDescrptif) {
		this.textDescrptif = textDescrptif;
	}

}
