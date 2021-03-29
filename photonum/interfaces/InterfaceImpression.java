package photonum.interfaces;

import java.util.ArrayList;
import java.util.List;

import photonum.objects.*;
import photonum.utils.*;

/**
 * cette class permet de faire l'interaction avec l'utilisateur pour les {@link Impression}
 */
public class InterfaceImpression {

	public static void interfaceCreationImpression(Client client) {

		System.out.println(
			"Quel type d'impression voulez-vous créer ?\n"+
			"\t1. Tirage\n"+
			"\t2. Album\n"+
			"\t3. Calendrier\n"+
			"\t4. Cadre\n"
		);
		int choix = LectureClavier.lireEntier(">");

		while (choix < 1 || choix > 4) {
			System.out.println("Vous devez choisir un nombre entre 1 et 4.");
			choix = LectureClavier.lireEntier(">");
		}
		Impression impression = new Impression();
		String reference = "reference1";
		impression.setReference(reference);
		impression.setType(TypeImpression.TIRAGE);
		impression.nouvelleImpression();
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
		createImpression(impression, client);
	}

	private static void createCalendrier(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre calendrier.\nVous devez donc créer 12 pages.");
		List<Page> pages = new ArrayList<>();
		Page page = new Page(impression.getIdImpression(), "");
		boolean b = true;
		for(int i = 1; i < 13 && b && page!=null; i++) {
			InterfacePage.interfaceCreationPage(impression.getIdImpression(), client, page);
			pages.add(page);
			System.out.println("Page n°" + i + " :");
			b = LectureClavier.lireOuiNon("\tQuitter la création (n) / Continuer la création");
		}
		if(b && page!=null)
		{
			createImpression(impression,client);
		}
		else if(!b && page==null){
			System.out.println("Allez dans le menu image pour cela !");
		}else{
			System.out.println("Votre impression n'a pas été enregistrée.");
		}
	}

	private static void createAlbum(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre album.\nVous pouvez donc créer le nombre de pages que vous voulez.");
		List<Page> pages = new ArrayList<>();
		Page p = new Page(impression.getIdImpression(),"");
		for(boolean b = true; b; b = LectureClavier.lireOuiNon("Continuer l'ajout de photos (o) / Terminer (n)")) {
			InterfacePage.interfaceCreationPageAlbum(impression.getIdImpression(),client,p);
			pages.add(p);
			System.out.println("Selectionnez 1 pour quitter, ou un autre nombre pour continuer la création des pages.");
		}
		createImpression(impression,client);
	}

	private static void createTirage(Impression impression, Client client) {
		System.out.println("Vous allez ici créer votre tirage.\nCommencez par désigner des photos spécifiques à vos tirages !");
		List<PhotoTirage> photos = new ArrayList<>();
		List<PhotoTirage> photosExi = client.getPhotosTirage();
		int i = 1;
		if(photosExi.size() != 0) {

			System.out.println("Liste de vos photos :");

			for(PhotoTirage photo: photosExi) {
				System.out.println("\t" + i + ". " + photo.toString());
				i++;
			}

			System.out.println("Selectionnez la photo que vous voulez ajouter à votre tirage.");
			int choix = LectureClavier.lireEntier("0 Pour quitter, un autre nombre pour selectionner la photo liée :");

			while(choix!=0)
			{
				if(choix <= photosExi.size())
				{

					photosExi.get(choix-1).setIdImpression(impression.getIdImpression());
					photosExi.get(choix-1).setNbFoisTiree(LectureClavier.lireEntier("Nombre de tirages de la photo :"));
					photosExi.get(choix-1).mettreAJour();
					System.out.println("La photo n°" + choix + " a bien été modifiée.");
				}
				choix = LectureClavier.lireEntier("0 Pour quitter, un autre nombre pour selectionner la photo liée.");
			}
		}
		else {
			System.out.println("Vous n'avez pas de photos prévues pour un tirage.");
		}

		boolean choix2 = LectureClavier.lireOuiNon("Voulez-vous préparer des photos pour un tirage ? (o/n)");

		if (choix2){
			PhotoTirage photo = new PhotoTirage("", client.getMail(), 0, impression.getIdImpression());
			int nbFois;
			int j = 1;
			List<FichierImage> listImg = client.getImages();
			if(listImg.size() != 0){

				System.out.println("Vos images :");
				for(FichierImage img : listImg)
				{
					System.out.println("\t" + j + ". " + img.getChemin());
					j++;
				}

				int choix3;

				for(boolean b = true; b; b = LectureClavier.lireOuiNon("Continuer l'ajout de photos (o) / Terminer (n)")) {
					choix3 = LectureClavier.lireEntier("Choisissez le fichier image que vous voulez pour créer votre photo :");
					while(!(choix3 > 0 && choix3 <= listImg.size())){
						choix3 = LectureClavier.lireEntier(
							"Choisissez un fichier image existant :"
						);
					}
					System.out.println("Combien de fois voulez-vous tirer cette photo ? (1, 2, 3, 4...)");
					nbFois = LectureClavier.lireEntier("");
					while (nbFois < 1) {
						nbFois = LectureClavier.lireEntier("Choisissez un nombre positif.");
					}
					InterfacePhoto.creationPhotoTirage(listImg.get(choix3-1).getChemin(), nbFois, photo);
					photos.add(photo);
				}
			}
			else{
				System.out.println("Vous n'avez pas d'image.");
			}
		}

		//impression.ajoutPhotosTirage(photos);
		createImpression(impression,client);
	}

	private static void createImpression(Impression impression, Client client) {
		if(impression.getType() == TypeImpression.TIRAGE)
		{
			List<PhotoTirage> listPhoto = impression.getPhotosTirage();
			if(listPhoto.size() != 0)
			{
				List<Produit> listProd = Produit.getAll();


				int i = 1;
				for(Produit prod : listProd) {
					System.out.println(i + ". " + prod.toString());
					i++;
				}
				int choix = LectureClavier.lireEntier(
						"Choisissez une référence pour votre impression :"
					);
				while(choix > i) {
					System.out.println("Cette référence n'existe pas. Essayez-en une autre.");
					choix = LectureClavier.lireEntier(
							"Choisissez une référence pour votre impression :"
						);
				}

				impression.setReference(listProd.get(choix - 1).getReference());
				impression.setMailClient(client.getMail());
				System.out.println("Choisissez maintenant le titre de votre " + impression.getType() + ".");
				impression.setTitre(LectureClavier.lireChaine());
				boolean reussi =impression.mettreAJour();

				if(reussi) {
					System.out.println("Votre " + impression.getType() + " a bien été créé.");
				} else {
					System.out.println("Une erreur est survenue, votre " + impression.getType() + " n'a pas pu être créé. Veuillez réessayer.");
				}
			}
			else{
				System.out.println("Il n'y a aucune photo prévue pour un tirage.");
			}
		} else {
			List<Page> listPage = impression.getPages();
			if(listPage.size() != 0){
				List<Photo> listPhoto = listPage.get(0).getPhotos();
				if(listPhoto != null && listPhoto.size() != 0) {
					List<Produit> listProd = Produit.getAll();

					int i = 1;
					for(Produit prod : listProd) {
						System.out.println(i + ". " + prod.toString());
						i++;
					}
					int choix = LectureClavier.lireEntier(
							"Choisissez une référence pour votre impression :"
						);
					while(choix > i) {
						System.out.println("Prenez une référence qui existe.");
						choix = LectureClavier.lireEntier(
								"Choisissez une référence pour votre impression :"
							);
					}

					impression.setReference(listProd.get(choix - 1).getReference());
					impression.setMailClient(client.getMail());
					System.out.println("Choisissez maintenant le titre de votre " + impression.getType() + ".");
					impression.setTitre(LectureClavier.lireChaine());
					boolean reussi = impression.mettreAJour();

					if(reussi) {
						System.out.println("Votre " + impression.getType() + " a bien été créé.");
					} else {
						System.out.println("Une erreur est survenue, votre " + impression.getType() + " n'a pas pu être créée. Veuillez réessayer.");
					}
				}
				else
				{
					System.out.println("Il n'y a pas de photos dans la page.");
				}
			}
			else
			{
				System.out.println("Il n'y a pas de page.");
			}
		}
	}

	//Affiche toutes les impressions du client, peut selectionner une impression pour avoir du detail
	public static void interfaceVueImpression(Client client) {

		List<Impression> list = client.getImpressions();
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			int last=list.size()+1;
			while(choix!=last){
				String menu="";
				for(int i=1;i<=list.size();i++){
					menu += "\t" + i +". "+list.get(i-1).getTitre()+"\n";
				}
				menu += "\t" + last + ". Sortir";

				choix=LectureClavier.lireEntier(menu);
				if(choix<0 || choix>last){
					System.out.println("Vous n'avez pas choisi d'impression.");
				}else if (choix!=last){
					System.err.println(list.get(choix-1).toString()+"\n");
					for(Page p : list.get(choix-1).getPages()){
						System.out.println("\t"+p.toString());
					}
				}
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
				case 5: break;
				default: System.err.println("Veuillez indiquer un nombre entre 1 et 5."); break;
			}
		}
	}

	public static void interfaceSuppressionImpression(Client client) {

		List<Impression> list = client.getImpressions();
		if(list.size() != 0) {
			System.out.println("Voici toutes les impressions de votre compte ("+client.getMail()+") :");
			int choix=-1;
			int last=list.size()+1;
			boolean supprimer=false;
			while(choix!=last && !supprimer){
				String message="";
					for(int i=1;i<=list.size();i++){
						message+=i+". "+list.get(i-1).getTitre()+"\n";
					}
					message+=last+". Sortir";
					choix=LectureClavier.lireEntier(message);
					if(choix<0 || choix>last){
						System.out.println("Vous n'avez pas choisi d'impression.");
					}else if (choix!=last){
						if(LectureClavier.lireOuiNon("Êtes-vous sûr de vouloir supprimer cette impression ? (o/n)")){
							supprimer = list.get(choix-1).supprimer();
						}
					}
			}
			if (LectureClavier.lireOuiNon("Voulez-vous supprimer une autre impression ? (o/n)")){
				interfaceSuppressionImpression(client);
			}
		}else {
			System.out.println("Vous n'avez pas d'impression.");
		}
	}

	public static void interfaceModificationImpression(Client client) {
		List<Impression> list = client.getImpressions();
		if(list.size() != 0) {
			System.out.println("Voici toutes vos impressions (du compte): "+client.getMail());
			int choix=-1;
			int last=list.size()+1;
			while(choix!=last){
					String message="";
					for(int i=1;i<=list.size();i++){
						message+=i+". "+list.get(i-1).getTitre()+"\n";
					}
					message+=last+". Sortir";
					choix=LectureClavier.lireEntier(message);

					if(choix<0 || choix>last){
						System.out.println("Vous n'avez pas choisi d'impression.");
					}else if (choix!=last){
						System.out.println("Quel est le nouveau titre ?");
						String titre = LectureClavier.lireChaine();
						System.out.println("Quelle est la nouvelle référence ?");

						List<Produit> listProd = Produit.getAll();
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
						list.get(choix-1).mettreAJour();
					}
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}

	}

}
