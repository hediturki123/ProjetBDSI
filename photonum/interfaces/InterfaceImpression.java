package photonum.interfaces;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.DAO;
import photonum.dao.ImpressionDAO;
import photonum.dao.ProduitDAO;
import photonum.objects.*;
import photonum.utils.*;

/**
 * cette class permet de faire l'interaction avec l'utilisateur pour les {@link Impression}
 */
public class InterfaceImpression {

	private static ImpressionDAO impDAO = new ImpressionDAO(PhotoNum.conn);
	public static void interfaceCreationImpression(Client client) {
		Impression imp = new Impression();
		impDAO.create(imp);
		
		System.out.println("Quel type d'impression voulez-vous créer?");
		System.out.println("1. Tirage\n2. Album\n3. Calendrier\n4. Cadre\n5.");
		int choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		while(choix!= 1 && choix!= 2 && choix!= 3 && choix!= 4) {
			System.out.println("Vous devez choisir un nombre entre 1 et 4.");
			choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		}
		switch(choix) {
		case 1:
			imp.setType(TypeImpression.TIRAGE);
			createTirage(imp,client);
			break;
		case 2:
			imp.setType(TypeImpression.ALBUM);
			createAlbum(imp,client);
			break;
		case 3:
			imp.setType(TypeImpression.CALENDRIER);
			createCalendrier(imp,client);
			break;
		case 4:
			imp.setType(TypeImpression.CADRE);
			createCadre(imp,client);
			break;
		default:
			imp.setType(TypeImpression.TIRAGE);
			createTirage(imp,client);
			break;
		}
	}
	
	private static void createCadre(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre cadre.\nVous devez donc créer une unique page.");
		Page p = new Page(impression.getIdImpression(),"");
		InterfacePage.interfaceCreationPage(impression.getIdImpression(),client,p);
		List<Page> pages = new ArrayList<>();
		pages.add(p);
		impression.setPages(pages);
		createImpression(impression,client);
	}

	private static void createCalendrier(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Calendrier.\nVous devez donc créer 12 pages.");
		List<Page> pages = new ArrayList<>();
		Page p = new Page(impression.getIdImpression(),"");
		for(int i = 0; i<12; i++) {
			InterfacePage.interfaceCreationPage(impression.getIdImpression(),client,p);
			pages.add(p);
		}
		impression.setPages(pages);
		createImpression(impression,client);
	}

	private static void createAlbum(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Album.\nVous pouvez donc créer le nombre de pages que vous voulez.");
		List<Page> pages = new ArrayList<>();
		Page p = new Page(impression.getIdImpression(),"");
		for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
			InterfacePage.interfaceCreationPage(impression.getIdImpression(),client,p);
			pages.add(p);
			System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
		}
		impression.setPages(pages);
		createImpression(impression,client);
	}

	private static void createTirage(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Tirage.\nVous allez donc créer des photos spécifiques aux tirages");
		List<PhotoTirage> photos = new ArrayList<>();
		String chemin;
		PhotoTirage photo = new PhotoTirage("", client.getMail(), 0);
		int nbFois;
		for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
			System.out.println("Rentrez le chemin de votre photo");
			chemin = LectureClavier.lireChaine();
			
			System.out.println("Rentrez le nombre de fois que vous voulez cette photo");
			nbFois = LectureClavier.lireEntier("1, 2, 3, 4, ...");
			InterfacePhoto.creationPhotoTirage(chemin, nbFois,photo);
			photos.add(photo);
			System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
		}
		impression.setPhotosTirage(photos);
		createImpression(impression,client);
	}
	
	private static void createImpression(Impression impression, Client client) {
		DAO<Produit> prodDAO = new ProduitDAO(PhotoNum.conn);
		List<Produit> listProd = prodDAO.readAll();
		int i = 1;
		for(Produit prod : listProd) {
			System.out.println(i+". "+prod.toString());
		}
		int choix=LectureClavier.lireEntier(
				"Choisissez une reference pour votre impression\n"
			);
		while(choix>i) {
			System.out.println("Prenez une référence qui existe");
			choix=LectureClavier.lireEntier(
					"Choisissez une reference pour votre impression\n"
				);
		}
		impression.setReference(listProd.get(choix-1).getReference());
		
		impression.setMailClient(client.getMail());
		
		System.out.println("Choisissez maintenant le titre de votre "+impression.getType()+".");
		impression.setTitre(LectureClavier.lireChaine());
		
		boolean reussi = impDAO.update(impression);
		if(reussi) 
		{
			System.out.println("Votre "+impression.getType()+" a bien été créé.");
		}
		else
		{
			System.out.println("Une erreur est survenue, votre "+impression.getType()+" n'a pas pu être créé. Veuillez réessayer.");
		}
	}
	
	//Affiche toutes les impressions du client, peut selectionner une impression pour avoir du detail
	public static void interfaceVueImpression(Client client) {

		List<Impression> list = impDAO.readAllByClient(client);
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			while(choix!=0){
				while(!(choix>0 && choix<=list.size())){
					for(int i=1;i<=list.size();i++){
						System.out.println(i+". "+list.get(i-1).getTitre());
					}
					choix=LectureClavier.lireEntier(
						"choisissez une impression pour la voir plus en detail dans la liste ci-dessus \n"+
						"ou taper sur 0"
					);
				}
			}
			if(choix!=0)list.get(choix-1).toString();
			if(LectureClavier.lireOuiNon("Voulez vous regardez une autre impression ? (o/n)")){
				interfaceVueImpression(client);
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
	}

	public static void menuImpression(Client client){
		System.out.println("Que voulez vous faire?");
		System.out.println("1. Visualiser vos impressions");
		System.out.println("2. Créer une impression");
		System.out.println("3. Modifier une impression");
		System.out.println("4. Supprimer une impression");
		
		int choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		switch(choix) {
		case 1:
			interfaceVueImpression(client);
			break;
		case 2:
			interfaceCreationImpression(client);
			break;
		case 3:
			interfaceModificationImpression(client);
			break;
		case 4:
			interfaceSuppressionImpression(client);
			break;
		default:
			break;	
		}
	}

	public static void interfaceSuppressionImpression(Client client) {
		
		List<Impression> list = impDAO.readAllByClient(client);
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			while(choix!=0){
				while(!(choix>0 && choix<=list.size())){
					for(int i=1;i<=list.size();i++){
						System.out.println(i+". "+list.get(i-1).getTitre());
					}
					choix=LectureClavier.lireEntier(
						"Choisissez une impression que vous voulez supprimer\n"+
						"ou taper sur 0"
					);
				}
				if(choix!=0)impDAO.delete(list.get(choix-1));
			}
			if(LectureClavier.lireOuiNon("Voulez vous supprimer une autre impression ? (o/n)")){
				interfaceSuppressionImpression(client);
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
	}

	public static void interfaceModificationImpression(Client client) {
		List<Impression> list = impDAO.readAllByClient(client);
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			while(choix!=0){
				while(!(choix>0 && choix<=list.size())){
					for(int i=1;i<=list.size();i++){
						System.out.println(i+". "+list.get(i-1).getTitre());
					}
					choix=LectureClavier.lireEntier(
						"Choisissez une impression que vous voulez modifier\n"+
						"ou taper sur 0"
					);
				}
				System.out.println("Quel est le nouveau titre?");
				String titre = LectureClavier.lireChaine();
				System.out.println("Quel est la nouvelle référence");
				String reference = LectureClavier.lireChaine();
				
				list.get(choix-1).setTitre(titre);
				list.get(choix-1).setReference(reference);
				if(choix!=0)impDAO.update(list.get(choix-1));
			}
			if(LectureClavier.lireOuiNon("Voulez vous modifier une autre impression ? (o/n)")){
				interfaceModificationImpression(client);
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
		
	}
}
