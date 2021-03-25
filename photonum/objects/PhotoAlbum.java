package photonum.objects;


public class PhotoAlbum extends Photo {
	private String texteDescriptif;

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

}
