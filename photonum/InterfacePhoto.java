package photonum;

public class InterfacePhoto {

	public static Photo creationPhotoDepuisImage(String path)
	{
		Photo p = new Photo(squellete_appli.conn);
		p.setChemin(path);
		return p;
	}
	
	public static void creationPhotoParPage(int idPhoto, int idPage) {
		//PhotoParPage p = new PhotoParPage(squellete_appli,idPhoto,idPage);
	}
}
