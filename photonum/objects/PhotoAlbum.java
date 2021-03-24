package photonum.objects;


public class PhotoAlbum extends Photo {
	private String texteDescrptif;

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
