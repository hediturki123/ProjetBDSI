package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.PhotoAlbumDAO;

public class PhotoAlbum extends Photo {

	private String texteDescriptif;

	private final static PhotoAlbumDAO PA_DAO = new PhotoAlbumDAO(PhotoNum.conn);

	public PhotoAlbum(String chemin, String mailClient, String textDescriptif) {
		super(chemin, mailClient);
		setTexteDescriptif(textDescriptif);
	}

	public String getTexteDescriptif() {
		return texteDescriptif;
	}

	public void setTexteDescriptif(String textDescriptif) {
		this.texteDescriptif = textDescriptif;
	}

	public boolean nouvellePhotoAlbum() {
		return PA_DAO.create(this);
	}
}
