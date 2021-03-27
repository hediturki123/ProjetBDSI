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

	/**
	 * @param idImpression d'une {@link Impression} pour laquel nous voulon creer une {@link Page}
	 * @param client le {@link Client} courant
	 * @param page La {@link Page} courante
	 */
	public static void interfaceCreationPage(int idImpression, Client client,Page page) {
		System.out.println("Vous allez ici créer une page pour votre impression.");

		List<Photo> resultat = new ArrayList<>();

		List<Photo> photosExi = client.getPhotos();

		if(photosExi.size()!=0) {
			System.out.println("Votre liste de photo:");
			int i = 1;
			int choix;
			for(Photo photo: photosExi) {
				System.out.println(i+". Vous avez cette photo: "+photo.toString());
				i++;
			}
			for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("1.Arrêter d'ajouter des photos ou 2.continuer"))
			{
				choix = LectureClavier.lireEntier("Choisissez la photo que vous voulez pour votre page");
				while(choix<1 && choix>photosExi.size()){
					choix=LectureClavier.lireEntier(
						"choisissez une photo existante.\n"
					);
				}
				resultat.add(photosExi.get(choix-1));
			}
		}else {
			System.out.println("Vous n'avez pas de photos.");
		}
		page.nouvellePage();
		

		System.out.println("Voulez vous créer des photos à mettre dans votre page?");
		int choix = LectureClavier.lireEntier("1.Oui/2.Non");
		List<FichierImage> listImg = client.getImages();
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
				page.mettreAJour();
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

		List<Photo> resultat = new ArrayList<>();

		List<PhotoAlbum> photosExi = client.getPhotosAlbum();

		if(photosExi.size()!=0) {
			System.out.println("Votre liste de photo:");
			int i = 1;
			int choix;
			for(PhotoAlbum photo: photosExi) {
				System.out.println(i+".Vous avez cette photo: "+photo.toString());
				i++;
			}
			for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("1.quitter ou 2.continuer"))
			{
				choix = LectureClavier.lireEntier("Choisissez la photo que vous voulez pour votre page");
				while(choix<1 && choix>photosExi.size()){
					choix=LectureClavier.lireEntier(
						"choisissez une photo existante.\n"
					);
				}
				resultat.add(photosExi.get(choix-1));
			}

		}else {
			System.out.println("Vous n'avez pas de photos.");
		}
		DAO<Page> pageDAO = new PageDAO(PhotoNum.conn);
		pageDAO.create(page);


		System.out.println("Voulez vous créer des photos à mettre dans votre page?");
		int choix = LectureClavier.lireEntier("1.Oui/2.Non");
		List<FichierImage> listImg = client.getImages();
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
