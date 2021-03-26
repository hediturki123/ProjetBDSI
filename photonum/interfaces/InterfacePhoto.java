package photonum.interfaces;

import photonum.objects.*;

public class InterfacePhoto {

	public static void creationPhotoTirage(String chemin ,int nbFois, PhotoTirage photo) {
		photo.setChemin(chemin);
		photo.setNbFoisTiree(nbFois);
		photo.nouveauTirage();
	}

	public static void creationPhoto(int idPage,String chemin, Photo photo) {
		photo.setChemin(chemin);
		photo.nouvellePhoto();
		PhotoParPage pp = new PhotoParPage(photo.getIdPhoto(),idPage);
		pp.associerPhotoPage();
	}

	public static void creationPhotoAlbum(int idPage, String chemin, PhotoAlbum photoAlbum, String texte){
		photoAlbum.setChemin(chemin);
		photoAlbum.nouvellePhotoAlbum();
		PhotoParPage pp = new PhotoParPage(photoAlbum.getIdPhoto(),idPage);
		pp.associerPhotoPage();
	}
}
