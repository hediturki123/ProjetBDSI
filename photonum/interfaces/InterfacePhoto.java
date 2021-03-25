package photonum.interfaces;
import photonum.PhotoNum;
import photonum.dao.DAO;
import photonum.dao.PhotoDAO;
import photonum.dao.PhotoTirageDAO;
import photonum.objects.*;

public class InterfacePhoto {

	public static Photo creationPhotoDepuisImage(String path)
	{
		return null;
	}
	
	/*public static void creationPhotoParPage(int idPhoto, int idPage) {
		//PhotoParPage p = new PhotoParPage(squellete_appli,idPhoto,idPage);
	}*/
	
	public static PhotoTirage creationPhotoTirage(int idImpression, String chemin ,int nbFois, Client client) {
		DAO<PhotoTirage> photoDAO = new PhotoTirageDAO(PhotoNum.conn);
		PhotoTirage photo = new PhotoTirage(chemin, nbFois);
		
		photoDAO.create(photo);
		return photo;
	}
	
	public static Photo creationPhoto(int idPage,String chemin , Client client)
	{
		DAO<Photo> photoDAO = new PhotoDAO(PhotoNum.conn);
		
		Photo p = new Photo(chemin);
		
		photoDAO.create(p);
		return p;
	}
}
