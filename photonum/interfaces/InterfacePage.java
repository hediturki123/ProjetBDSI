package photonum.interfaces;

import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.*;
import photonum.objects.*;
import photonum.utils.LectureClavier;
/**
 * cette class permet de faire l'interaction avec l'utilisateur pour des {@link Page}
 */
public class InterfacePage {
	private static PhotoDAO dao = new PhotoDAO(PhotoNum.conn);
	/**
	 * 
	 * @param idImpression d'une {@link Impression} pour laquel nous voulon creer une {@link Page}
	 * @param client le {@link Client} courant 
	 * @param page La {@link Page} courante 
	 */
	public static void interfaceCreationPage(int idImpression, Client client,Page page) {
		System.out.println("Vous allez ici créer une page pour votre impression.");

		List<Photo> resultat = new ArrayList<>();

		List<Photo> photosExi = dao.readAllPhotosByClient(client.getMail());

		if(photosExi.size()!=0) {
			System.out.println("Votre liste de photo:");
			for(Photo photo: photosExi) {
				System.out.println("Vous avez cette photo: "+photo.toString());
				System.out.println("Voulez vous la mettre dans votre page?\n 1.Oui\n 2.Non");
				int choix = LectureClavier.lireEntier("Oui/Non");
				while(choix != 1 && choix != 2)
				{
					System.out.println("Choisissez 1 ou 2, oui ou non");
					choix = LectureClavier.lireEntier("Oui/Non");
				}
				if(choix == 1)
					resultat.add(photo);
			}
		}else {
			System.out.println("Vous n'avez pas de photos.");
		}
		DAO<Page> pageDAO = new PageDAO(PhotoNum.conn);
		pageDAO.create(page);


		System.out.println("Voulez vous créer des photos à mettre dans votre page?");
		int choix = LectureClavier.lireEntier("Oui/Non");
		String chemin;
		if(choix==1) {
			Photo photo = new Photo("",client.getMail());
			for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
				System.out.println("Rentrez le chemin de votre photo");
				chemin = LectureClavier.lireChaine();
				InterfacePhoto.creationPhoto(page.getIdPage(),chemin,photo);
				resultat.add(photo);
				System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
			}
		}
		page.setPhotos(resultat);

		System.out.println("Rentrez votre mise en forme");
		String mef = LectureClavier.lireChaine();
		page.setMiseEnForme(mef);

		pageDAO.update(page);
	}
	
	/**
	 * 
	 * @param idImpression une {@link Impression} pour laquel nous voulons creer une {@link photo}
	 * @param client
	 * @param page
	 */
	public static void interfaceCreationPageAlbum(int idImpression, Client client, Page page) {
		System.out.println("Vous allez ici créer une page pour votre impression.");
		List<Photo> resultat = new ArrayList<>();

		List<Photo> photosExi = dao.readAllPhotosByClient(client.getMail());

		if(photosExi.size()!=0) {
			System.out.println("Votre liste de photo:");
			for(Photo photo: photosExi) {
				System.out.println("Vous avez cette photo: "+photo.toString());
				System.out.println("Voulez vous la mettre dans votre page?\n 1.Oui\n 2.Non");
				int choix = LectureClavier.lireEntier("Oui/Non");
				while(choix != 1 && choix != 2)
				{
					System.out.println("Choisissez 1 ou 2, oui ou non");
					choix = LectureClavier.lireEntier("Oui/Non");
				}
				if(choix == 1)
					resultat.add(photo);
			}
		}else {
			System.out.println("Vous n'avez pas de photos.");
		}
		DAO<Page> pageDAO = new PageDAO(PhotoNum.conn);
		pageDAO.create(page);


		System.out.println("Voulez vous créer des photos à mettre dans votre page?");
		int choix = LectureClavier.lireEntier("Oui/Non");
		String chemin;
		if(choix==1) {
			Photo photo = new Photo("",client.getMail());
			for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
				System.out.println("Rentrez le chemin de votre photo");
				chemin = LectureClavier.lireChaine();
				InterfacePhoto.creationPhoto(page.getIdPage(),chemin,photo);
				resultat.add(photo);
				System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
			}
		}
		page.setPhotos(resultat);

		System.out.println("Rentrez votre mise en forme");
		String mef = LectureClavier.lireChaine();
		page.setMiseEnForme(mef);

		pageDAO.update(page);
	}
}
