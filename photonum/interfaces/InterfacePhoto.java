package photonum.interfaces;

import photonum.PhotoNum;
import photonum.dao.PhotoDAO;
import photonum.dao.PhotoParPageDAO;
import photonum.dao.PhotoTirageDAO;
import photonum.dao.PhotoAlbumDAO;
import photonum.objects.*;

public class InterfacePhoto {

	private static PhotoTirageDAO photoTirageDAO = new PhotoTirageDAO(PhotoNum.conn);
	private static PhotoDAO photoDAO = new PhotoDAO(PhotoNum.conn);
	private static PhotoParPageDAO photoPageDAO = new PhotoParPageDAO(PhotoNum.conn);
	private static PhotoAlbumDAO photoAlbumDAO = new PhotoAlbumDAO(PhotoNum.conn);

	public static void creationPhotoTirage(String chemin ,int nbFois, PhotoTirage photo) {
		photo.setChemin(chemin);
		photo.setNbFoisTiree(nbFois);
		photoTirageDAO.create(photo);
	}

	public static void creationPhoto(int idPage,String chemin, Photo photo) {
		photo.setChemin(chemin);
		photoDAO.create(photo);
		PhotoParPage pp = new PhotoParPage(photo.getIdPhoto(),idPage);
		photoPageDAO.create(pp);
	}

	public static void creationPhotoAlbum(int idPage, String chemin, PhotoAlbum photoAlbum, String texte){
		photoAlbum.setChemin(chemin);
		photoAlbumDAO.create(photoAlbum);
		PhotoParPage pp = new PhotoParPage(photoAlbum.getIdPhoto(),idPage);
		photoPageDAO.create(pp);
	}
}
