import java.sql.Connection;

public class PhotoAlbum extends Photo{
	private String texteDescrptif;

	public PhotoAlbum(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public PhotoAlbum(Connection conn, int idPhoto, String chemin, String textDescrptif) {
		super(conn, idPhoto, chemin);
		setTextDescrptif(textDescrptif);
	}

	public String getTextDescrptif() {
		return texteDescrptif;
	}

	public void setTextDescrptif(String textDescrptif) {
		this.texteDescrptif = textDescrptif;
	}

}
