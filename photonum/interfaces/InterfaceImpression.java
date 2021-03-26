package photonum.interfaces;

import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.FichierImageDAO;
import photonum.dao.ImpressionDAO;
import photonum.dao.ProduitDAO;
import photonum.dao.PhotoTirageDAO;
import photonum.objects.*;
import photonum.utils.*;

/**
 * cette class permet de faire l'interaction avec l'utilisateur pour les {@link Impression}
 */
public class InterfaceImpression {

	private static ImpressionDAO impressionDAO = new ImpressionDAO(PhotoNum.conn);
	private static ProduitDAO produitDAO = new ProduitDAO(PhotoNum.conn);
	private static FichierImageDAO fichierImageDAO = new FichierImageDAO(PhotoNum.conn);
	private static PhotoTirageDAO photoTirageDAO = new PhotoTirageDAO(PhotoNum.conn);


	public static void interfaceCreationImpression(Client client) {

		System.out.println("Quel type d'impression voulez-vous créer?");
		System.out.println("\t1. Tirage\n\t2. Album\n\t3. Calendrier\n\t4. Cadre\n");
		int choix = LectureClavier.lireEntier(">");

		while(choix != 1 && choix != 2 && choix != 3 && choix != 4) {
			System.out.println("Vous devez choisir un nombre entre 1 et 4.");
			choix = LectureClavier.lireEntier(">");
		}
		Impression impression = new Impression();
		String reference = "reference1";
		impression.setReference(reference);
		impression.setType(TypeImpression.TIRAGE);
		impressionDAO.create(impression);
		switch(choix) {
		case 1:
			impression.setType(TypeImpression.TIRAGE);
			createTirage(impression, client);
			break;
		case 2:
			impression.setType(TypeImpression.ALBUM);
			createAlbum(impression, client);
			break;
		case 3:
			impression.setType(TypeImpression.CALENDRIER);
			createCalendrier(impression, client);
			break;
		case 4:
			impression.setType(TypeImpression.CADRE);
			createCadre(impression, client);
			break;
		default:
			impression.setType(TypeImpression.TIRAGE);
			createTirage(impression, client);
			break;
		}

		
	}
	
	
	private static void createCadre(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre cadre.\nVous devez donc créer une unique page.");
		Page page = new Page(impression.getIdImpression(), "");
		InterfacePage.interfaceCreationPage(impression.getIdImpression(), client, page);
		List<Page> pages = new ArrayList<>();
		pages.add(page);
		impression.setPages(pages);
		createImpression(impression, client);
	}

	private static void createCalendrier(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre Calendrier.\nVous devez donc créer 12 pages.");
		List<Page> pages = new ArrayList<>();
		Page page = new Page(impression.getIdImpression(), "");
		boolean b = true;
		for(int i = 0; i < 12 && b && page!=null; i++) {
			InterfacePage.interfaceCreationPage(impression.getIdImpression(), client, page);
			pages.add(page);
			System.out.println("Page n° : " + i);
			b = LectureClavier.lireEntier("1.Quitter la création.\n2.Continuer la création.") != 1;
		}
		if(b && page!=null)
		{
			impression.setPages(pages);
			createImpression(impression,client);
		}
		else if(!b && page==null){
			System.out.println("Allez dans le menu image pour cela !");
		}else{
			System.out.println("Pas de souci , mais votre impression n'a pas été enregistré");
		}
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
		List<PhotoTirage> photosExi = photoTirageDAO.readAllPhotosTirageByClient(client);
		int i = 1;
		if(photosExi.size() != 0) {
			
			System.out.println("Votre liste de photo:");
			
			for(PhotoTirage photo: photosExi) {
				System.out.println("\t" + i + ". Vous avez cette photo: " + photo.toString());
				i++;
			}
			
			System.out.println("Selectionnez la photo que vous voulez ajouter à votre Tirage");
			int choix = LectureClavier.lireEntier("0 Pour quitter, un autre nombre pour selectionner la photo liée");
			
			while(choix!=0)
			{
				if(choix <= photosExi.size())
				{
					photos.add(photosExi.get(choix-1));
					System.out.println("La photo numéro " + choix + " a bien été ajoutée.");
				}
				choix = LectureClavier.lireEntier("0 Pour quitter, un autre nombre pour selectionner la photo liée");
			}	
		}
		else{
			System.out.println("Vous n'avez pas de photos Tirage.");
		}

		System.out.println("Voulez vous créer des photos Tirage?");
		int choix2 = LectureClavier.lireEntier("1.Oui\n2.Non");
		
		if(choix2==1){
			PhotoTirage photo = new PhotoTirage("", client.getMail(), 0, impression.getIdImpression());
			int nbFois;
			int j = 1;
			List<FichierImage> listImg = fichierImageDAO.readAllByClient(client);
			if(listImg.size() != 0){
			
				System.out.println("Vos fichiers images disponibles:");
				for(FichierImage img : listImg)
				{
					System.out.println("\t" + j + ". " + img.getChemin());
					j++;
				}
				
				int choix3;

				for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("1.quitter ou 2.continuer")) {
					choix3 = LectureClavier.lireEntier("Choisissez le fichier image que vous voulez pour créer votre photo");
					while(!(choix3 > 0 && choix3 <= listImg.size())){
						choix3 = LectureClavier.lireEntier(
							"choisissez un fichier image existant \n"
						);
					}
					System.out.println("Rentrez le nombre de fois que vous voulez cette photo");
					nbFois = LectureClavier.lireEntier("1, 2, 3, 4, ...");
					while(nbFois<1)
					{
						nbFois = LectureClavier.lireEntier("Choisissez un nombre positif");
					}
					InterfacePhoto.creationPhotoTirage(listImg.get(choix3-1).getChemin(), nbFois, photo);
					photos.add(photo);
					
				}
			}
			else{
				System.out.println("Vous n'avez pas de fichier Image.");
			}
		}
		
		impression.setPhotosTirage(photos);
		createImpression(impression,client);
	}

	private static void createImpression(Impression impression, Client client) {
		
		if(impression.getType() == TypeImpression.TIRAGE)
		{
			List<PhotoTirage> listPhoto = impression.getPhotosTirage();
			if(listPhoto.size() != 0)
			{
				List<Produit> listProd = produitDAO.readAll();


				int i = 1;
				for(Produit prod : listProd) {
					System.out.println(i + ". " + prod.toString());
					i++;
				}
				int choix = LectureClavier.lireEntier(
						"Choisissez une reference pour votre impression\n"
					);
				while(choix > i) {
					System.out.println("Prenez une référence qui existe");
					choix = LectureClavier.lireEntier(
							"Choisissez une reference pour votre impression\n"
						);
				}

				impression.setReference(listProd.get(choix - 1).getReference());
				impression.setMailClient(client.getMail());
				System.out.println("Choisissez maintenant le titre de votre " + impression.getType() + ".");
				impression.setTitre(LectureClavier.lireChaine());
				boolean reussi = impressionDAO.update(impression);

				if(reussi) {
					System.out.println("Votre " + impression.getType() + " a bien été créé.");
				} else {
					System.out.println("Une erreur est survenue, votre " + impression.getType() + " n'a pas pu être créée. Veuillez réessayer.");
				}
			}
		}
		else
		{
			List<Page> listPage = impression.getPages();
			if(listPage.size() != 0){
				if(listPage.get(0).getPhotos() != null && listPage.get(0).getPhotos().size() != 0) {
					List<Produit> listProd = produitDAO.readAll();


					int i = 1;
					for(Produit prod : listProd) {
						System.out.println(i + ". " + prod.toString());
						i++;
					}
					int choix = LectureClavier.lireEntier(
							"Choisissez une reference pour votre impression\n"
						);
					while(choix > i) {
						System.out.println("Prenez une référence qui existe");
						choix = LectureClavier.lireEntier(
								"Choisissez une reference pour votre impression\n"
							);
					}

					impression.setReference(listProd.get(choix - 1).getReference());
					impression.setMailClient(client.getMail());
					System.out.println("Choisissez maintenant le titre de votre " + impression.getType() + ".");
					impression.setTitre(LectureClavier.lireChaine());
					boolean reussi = impressionDAO.update(impression);

					if(reussi) {
						System.out.println("Votre " + impression.getType() + " a bien été créé.");
					} else {
						System.out.println("Une erreur est survenue, votre " + impression.getType() + " n'a pas pu être créée. Veuillez réessayer.");
					}
				}
			}
		}
	}

	//Affiche toutes les impressions du client, peut selectionner une impression pour avoir du detail
	public static void interfaceVueImpression(Client client) {

		List<Impression> list = impressionDAO.readAllByClient(client);
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			int last=list.size()+1;
			while(choix!=last){
				String menu="";
				for(int i=1;i<=list.size();i++){
					menu+=i+". "+list.get(i-1).getTitre()+"\n";
				}
				menu+=last+". Suivant";

				choix=LectureClavier.lireEntier(menu);
				if(choix<0 || choix>last){
					System.out.println("vous n'avez pas choisi une impression ");
				}else if (choix!=last){
					System.err.println(list.get(choix-1).toString()+"\n");
					//TODO ici rajouter le detail des page 
				}
			}
			if (LectureClavier.lireOuiNon("Voulez vous regardez une autre impression ? (o/n)")){//TODO pas vraiment utilse du coup
				interfaceVueImpression(client);
			}
		} else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
	}

	public static void menuImpression(Client client){
		int choix = -1;
		while (choix != 5) {
			choix = LectureClavier.lireEntier(
				"--- Mes impressions ---\n"+
				"\t1. Visualiser vos impressions\n"+
				"\t2. Créer une impression\n"+
				"\t3. Modifier une impression\n"+
				"\t4. Supprimer une impression\n"+
				"\t5. Retour au menu précédent\n"+
				"> "
			);
			switch (choix) {
				case 1: interfaceVueImpression(client); break;
				case 2: interfaceCreationImpression(client); break;
				case 3: interfaceModificationImpression(client); break;
				case 4: interfaceSuppressionImpression(client); break;
				case 5:break;
				default: System.err.println("Veuillez indiquer un nombre entre 1 et 3."); break;
			}
		}
	}

	public static void interfaceSuppressionImpression(Client client) {

		List<Impression> list = impressionDAO.readAllByClient(client);
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			int last=list.size()+1;
			while(choix!=last){
				String message="";
					for(int i=1;i<=list.size();i++){
						message+=i+". "+list.get(i-1).getTitre()+"\n";
					}
					message+=last+". Suivant";
					choix=LectureClavier.lireEntier(message);
					if(choix<0 || choix>last){
						System.out.println("vous n'avez pas choisi une impression ");
					}else if (choix!=last){
						impressionDAO.delete(list.get(choix-1));
					}
			}
			if(LectureClavier.lireOuiNon("Voulez vous supprimer une autre impression ? (o/n)")){
				interfaceSuppressionImpression(client);
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
	}

	public static void interfaceModificationImpression(Client client) {
		List<Impression> list = impressionDAO.readAllByClient(client);
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			int last=list.size()+1;
			while(choix!=last){
					String message="";
					for(int i=1;i<=list.size();i++){
						message+=i+". "+list.get(i-1).getTitre()+"\n";
					}
					message+=last+". Suivant";
					choix=LectureClavier.lireEntier(message);

					if(choix<0 || choix>last){
						System.out.println("vous n'avez pas choisi une impression ");
					}else if (choix!=last){
						System.out.println("Quel est le nouveau titre?");
						String titre = LectureClavier.lireChaine();
						System.out.println("Quel est la nouvelle référence");
						
						List<Produit> listProd = produitDAO.readAll();
						int i = 1;
						for(Produit prod : listProd) {
							System.out.println(i + ". " + prod.toString());
							i++;
						}
						int choix1 = LectureClavier.lireEntier(
								"Choisissez une reference pour votre impression\n"
							);
						while(choix1 > i) {
							System.out.println("Prenez une référence qui existe");
							choix1 = LectureClavier.lireEntier(
									"Choisissez une reference pour votre impression\n"
								);
						}
						
						list.get(choix-1).setTitre(titre);
						list.get(choix-1).setReference(listProd.get(choix1-1).getReference());
						impressionDAO.update(list.get(choix-1));
					}
				}
			if(LectureClavier.lireOuiNon("Voulez vous modifier une autre impression ? (o/n)")){
				interfaceModificationImpression(client);
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}

	}

}
