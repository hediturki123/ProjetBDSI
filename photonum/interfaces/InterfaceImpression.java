package photonum.interfaces;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ImpressionDAO;
import photonum.objects.*;
import photonum.utils.*;

public class InterfaceImpression {

	private ImpressionDAO impDAO;
	public void interfaceCreationImpression(Client client) {
		Impression imp = new Impression();
		impDAO = new ImpressionDAO(PhotoNum.conn);
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
	
	private void createCadre(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre cadre.\nVous devez donc créer une unique page.");
		Page p = new Page(impression.getIdImpression(),"");
		InterfacePage.interfaceCreationPage(impression.getIdImpression(),client,p);
		List<Page> pages = new ArrayList<>();
		pages.add(p);
		impression.setPages(pages);
		createImpression(impression,client);
	}

	private void createCalendrier(Impression impression, Client client) {
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

	private void createAlbum(Impression impression, Client client) {
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

	private void createTirage(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Tirage.\nVous allez donc créer des photos spécifiques aux tirages");
		List<PhotoTirage> photos = new ArrayList<>();
		String chemin;
		PhotoTirage photo = new PhotoTirage("", 0);
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
	
	private void createImpression(Impression impression, Client client) {
		System.out.println("Vous devez maintenant sélectioner le format de votre "+impression.getType()+".");
		String format = LectureClavier.lireChaine();
		System.out.println("Vous devez maintenant sélectioner la qualité de votre "+impression.getType()+".");
		String qualite = LectureClavier.lireChaine();
		impression.setReference(format + qualite);
		
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
		ImpressionDAO impressionDAO = new ImpressionDAO(PhotoNum.conn);

		List<Impression> list = impressionDAO.readAllByClient(client);
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

	public static void menuImpression(Client c){

	}
}
