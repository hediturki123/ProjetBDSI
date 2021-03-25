package photonum.objects;


public class PhotoAlbum extends Photo {
	private String texteDescriptif;

	public PhotoAlbum(String chemin, String textDescriptif) {
		super(chemin);
		setTexteDescriptif(textDescriptif);
	}

	public String getTexteDescriptif() {
		return texteDescriptif;
	}

	public void setTexteDescriptif(String textDescriptif) {
		this.texteDescriptif = textDescriptif;
	}

}
