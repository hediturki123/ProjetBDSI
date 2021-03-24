package photonum.interfaces;

import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.*;
import photonum.objects.*;
import photonum.utils.LectureClavier;

public class InterfacePage {

	public static Page interfaceCreationPage(int idImpression, Client client) {
		System.out.println("Vous allez ici créer une page pour votre impression.");
		PhotoDAO dao = new PhotoDAO(PhotoNum.conn);
		List<Photo> resultat = new ArrayList<>();
		
		List<Photo> photosExi = dao.readAllPhotosByClient(client.getMail());
		System.out.println("Votre liste de photo:");
		for(Photo p: photosExi) {
			System.out.println("Vous avez cette photo: "+p.getChemin());
			System.out.println("Voulez vous la mettre dans votre page?\n 1.Oui\n 2.Non");
			int choix = LectureClavier.lireEntier("Oui/Non");
			while(choix != 1 && choix != 2)
			{
				System.out.println("Choisissez 1 ou 2, oui ou non");
				choix = LectureClavier.lireEntier("Oui/Non");
			}
			if(choix == 1)
				resultat.add(p);
		}
		
		System.out.println("Voulez vous créer des photos à mettre dans votre page?");
		int choix = LectureClavier.lireEntier("Oui/Non");
		if(choix==1) {
			for(boolean b = true; b; b = 1 != LectureClavier.lireEntier("quitter ou continuer")) {
				resultat.add(InterfacePhoto.creationPhoto(idImpression,client));
				System.out.println("Selectionnez 1 pour quitter et un autre nombre pour continuer la création de pages");
			}
		}
		
		
		System.out.println("Rentrez votre mise en forme");
		String mef = LectureClavier.lireChaine();
		
		Page p = new Page(idImpression,mef);
		return p;
	}
}
