package photonum.interfaces;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ImpressionDAO;
import photonum.objects.*;
import photonum.utils.*;

public class InterfaceImpression {

	public void interfaceCreationImpression(Client client) {
		Impression imp = new Impression();
		System.out.println("Quel type d'impression voulez-vous créer?");
		System.out.println("1. Tirage\n2. Album\n3. Calendrier\n4. Cadre\n5.");
		int choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		while(choix!= 1 && choix!= 2 && choix!= 3 && choix!= 4) {
			System.out.println("Vous devez choisir un nombre entre 1 et 4.");
			choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		}
		switch(choix) {
		case 1:
			imp.setType("tirage");
			createTirage(imp,client);
			break;
		case 2:
			imp.setType("album");
			createAlbum(imp,client);
			break;
		case 3:
			imp.setType("calendrier");
			createCalendrier(imp,client);
			break;
		case 4:
			imp.setType("cadre");
			createCadre(imp,client);
			break;
		default:
			imp.setType("tirage");
			createTirage(imp,client);
			break;
		}
	}
	
	private void createCadre(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre cadre.\nVous devez donc créer une unique page.");
		Page p = InterfacePage.interfaceCreationPage(impression.getIdImpression(),client);
		List<Page> pages = new ArrayList<>();
		pages.add(p);
		impression.setPages(pages);
		createImpression(impression);
	}

	private void createCalendrier(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Calendrier.\nVous devez donc créer 12 pages.");
		List<Page> pages = new ArrayList<>();
		for(int i = 0; i<12; i++) {
			pages.add(InterfacePage.interfaceCreationPage(impression.getIdImpression(),client));
		}
		impression.setPages(pages);
		createImpression(impression);
	}

	private void createAlbum(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Album.\nVous pouvez donc créer le nombre de pages que vous voulez.");
		List<Page> pages = new ArrayList<>();
		for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
			pages.add(InterfacePage.interfaceCreationPage(impression.getIdImpression(),client));
			System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
		}
		impression.setPages(pages);
		createImpression(impression);
	}

	private void createTirage(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Tirage.\nVous allez donc créer des photos spécifiques aux tirages");
		List<PhotoTirage> photos = new ArrayList<>();
		String chemin;
		int nbFois;
		for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
			System.out.println("Rentrez le chemin de votre photo");
			chemin = LectureClavier.lireChaine();
			
			System.out.println("Rentrez le nombre de fois que vous voulez cette photo");
			nbFois = LectureClavier.lireEntier("1, 2, 3, 4, ...");
			
			photos.add(InterfacePhoto.creationPhotoTirage(chemin, nbFois));
			System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
		}
		impression.setPhotosTirage(photos);
		createImpression(impression);
	}
	
	private void createImpression(Impression impression) {
		System.out.println("Vous devez maintenant sélectioner le format de votre "+impression.getType()+".");
		String format = LectureClavier.lireChaine();
		System.out.println("Vous devez maintenant sélectioner la qualité de votre "+impression.getType()+".");
		String qualite = LectureClavier.lireChaine();
		impression.setReference(format + qualite);

		System.out.println("Choisissez maintenant le titre de votre "+impression.getType()+".");
		impression.setTitre(LectureClavier.lireChaine());
		
		ImpressionDAO impDAO = new ImpressionDAO(PhotoNum.conn);
		boolean reussi = impDAO.create(impression);
		if(reussi) 
		{
			System.out.println("Votre "+impression.getType()+" a bien été créé.");
		}
		else
		{
			System.out.println("Une erreur est survenue, votre "+impression.getType()+" n'a pas pu être créé. Veuillez réessayer.");
		}
	}
	
	public static void interfaceVueImpression(Client client) {
		
	}
}
