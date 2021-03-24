package photonum.objects;

import java.sql.Connection;

public class PhotoAlbum extends Photo{
	private String texteDescriptif;

	public PhotoAlbum(Connection conn) {
		super(conn);
	}

	public PhotoAlbum(int idPhoto, String chemin, String textDescrptif) {
		super(idPhoto, chemin);
		setTextDescrptif(textDescrptif);
	}

	public String getTextDescrptif() {
		return texteDescrptif;
	}

	public void setTextDescrptif(String textDescrptif) {
		this.texteDescrptif = textDescrptif;
	}

}
