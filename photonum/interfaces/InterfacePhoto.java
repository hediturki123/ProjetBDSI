package photonum.interfaces;
import photonum.PhotoNum;
import photonum.dao.DAO;
import photonum.dao.PhotoDAO;
import photonum.dao.PhotoParPageDAO;
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
	
	public static PhotoTirage creationPhotoTirage(String chemin ,int nbFois) {
		DAO<PhotoTirage> photoDAO = new PhotoTirageDAO(PhotoNum.conn);
		PhotoTirage photo = new PhotoTirage(chemin, nbFois);
		
		photoDAO.create(photo);
		return photo;
	}
	
	public static Photo creationPhoto(int idPage,String chemin)
	{
		DAO<Photo> photoDAO = new PhotoDAO(PhotoNum.conn);
		
		Photo p = new Photo(chemin);
		
		photoDAO.create(p);
		
		DAO<PhotoParPage> photoPageDAO = new PhotoParPageDAO(PhotoNum.conn);
		PhotoParPage pp = new PhotoParPage(p.getIdPhoto(),idPage);
		photoPageDAO.create(pp);
		return p;
	}
}
