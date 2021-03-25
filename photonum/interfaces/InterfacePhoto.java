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
	
	public static void creationPhotoTirage(String chemin ,int nbFois, PhotoTirage photo) {
		DAO<PhotoTirage> photoDAO = new PhotoTirageDAO(PhotoNum.conn);
		photo.setChemin(chemin);
		photo.setNbFoisTiree(nbFois);
		
		photoDAO.create(photo);
	}
	
	public static void creationPhoto(int idPage,String chemin, Photo photo)
	{
		DAO<Photo> photoDAO = new PhotoDAO(PhotoNum.conn);
		
		photo.setChemin(chemin);
		
		photoDAO.create(photo);
		
		DAO<PhotoParPage> photoPageDAO = new PhotoParPageDAO(PhotoNum.conn);
		PhotoParPage pp = new PhotoParPage(photo.getIdPhoto(),idPage);
		photoPageDAO.create(pp);
	}
}
