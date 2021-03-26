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
	private static PhotoAlbumDAO albumDAO = new PhotoAlbumDAO(PhotoNum.conn);
	private static FichierImageDAO fichierImageDAO = new FichierImageDAO(PhotoNum.conn);
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
		int choix = LectureClavier.lireEntier("1.Oui/2.Non");//TODO changer par un lire oui non 
		String chemin;
		List<FichierImage> listImg = fichierImageDAO.readAllByClient(client);
		if(listImg.size()!=0){
			if(choix == 1) {
				Photo photo = new Photo("",client.getMail());
				int i = 1;
				System.out.println("Vos fichiers images disponibles:");
				for(FichierImage img : listImg)
				{
					System.out.println(i + ". " + img.getChemin());
					i++;
				}
				
				int choix2;

				for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("1.quitter ou 2.continuer")) {
					choix2 = LectureClavier.lireEntier("Choisissez le fichier image que vous voulez pour créer votre photo");
					while(choix2<1 && choix2>listImg.size()){
						choix2=LectureClavier.lireEntier(
							"choisissez un fichier image existant.\n"
						);
					}
					InterfacePhoto.creationPhoto(page.getIdPage(),listImg.get(choix2-1).getChemin(),photo);
					resultat.add(photo);
				}
				page.setPhotos(resultat);

				System.out.println("Rentrez votre mise en forme");
				String mef = LectureClavier.lireChaine();
				page.setMiseEnForme(mef);

				pageDAO.update(page);
			}
		}
		else{
			System.out.println("Vous n'avez pas de fichier image pour créer des photos, téléchargez-en.");
			page=null;

		}
		
	}
	
	/**
	 * 
	 * @param idImpression une {@link Impression} pour laquel nous voulons creer une {@link photo}
	 * @param client
	 * @param page
	 */
	public static void interfaceCreationPageAlbum(int idImpression, Client client, Page page) {
		System.out.println("Vous allez ici créer une page pour votre impression.");

		List<PhotoAlbum> resultat = new ArrayList<>();

		List<PhotoAlbum> photosExi = albumDAO.readAllPhotosAlbumByClient(client.getMail());

		if(photosExi.size()!=0) {
			System.out.println("Votre liste de photo:");
			for(PhotoAlbum photo: photosExi) {
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
		int choix = LectureClavier.lireEntier("1.Oui/2.Non");
		String chemin;
		List<FichierImage> listImg = fichierImageDAO.readAllByClient(client, true);
		if(listImg.size()!=0){
			if(choix == 1) {
				PhotoAlbum photo = new PhotoAlbum("",client.getMail(),"");
				int i =1;
				System.out.println("Vos fichiers images disponibles:");
				for(FichierImage img : listImg)
				{
					System.out.println(i + ". " + img.getChemin());
				}
				
				int choix2;

				for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("1.quitter ou 2.continuer")) {
					choix2 = LectureClavier.lireEntier("Choisissez le fichier image que vous voulez pour créer votre photo");
					while(choix2<1 && choix2>listImg.size()){
						choix2=LectureClavier.lireEntier(
							"choisissez un fichier image existant.\n"
						);
					}
					System.out.println("Rentrez le texte descriptif.");
					String texte = LectureClavier.lireChaine();
					InterfacePhoto.creationPhotoAlbum(page.getIdPage(),listImg.get(choix2-1).getChemin(),photo,texte);
					resultat.add(photo);
				}
				page.setPhotos(resultat);

				System.out.println("Rentrez votre mise en forme");
				String mef = LectureClavier.lireChaine();
				page.setMiseEnForme(mef);

				pageDAO.update(page);
			}
		}
		else{
			System.out.println("Vous n'avez pas de fichier image pour créer des photos, téléchargez-en.");
		}
	}
}
