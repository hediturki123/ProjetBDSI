package photonum.interfaces;
import photonum.objects.*;
import photonum.utils.*;

public class InterfaceImpression {

	public void interfaceImpression() {
		Impression imp = new Impression(squellete_appli.conn);
		System.out.println("Quel type d'impression voulez-vous cr�er?");
		System.out.println("1. Tirage\n2. Album\n3. Calendrier\n4. Cadre");
		int choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		while(choix!= 1 && choix!= 2 && choix!= 3 && choix!= 4) {
			System.out.println("Vous devez choisir un nombre entre 1 et 4.");
			choix = LectureClavier.lireEntier("1, 2, 3 ou 4?");
		}
		switch(choix) {
		case 1:
			imp.setType("tirage");
			createTirage();
			break;
		case 2:
			imp.setType("album");
			createAlbum();
			break;
		case 3:
			imp.setType("calendrier");
			createCalendrier();
			break;
		case 4:
			imp.setType("cadre");
			createCadre();
			break;
		default:
			imp.setType("tirage");
			createTirage();
			break;
		}
	}
	
	private void createCadre() {
		System.out.println("Vous allez ici cr�er votre cadre.");
		//Page.InterfacePage();
	}

	private void createCalendrier() {
		// TODO Auto-generated method stub
		
	}

	private void createAlbum() {
		// TODO Auto-generated method stub
		
	}

	private void createTirage() {
		// TODO Auto-generated method stub
		
	}
}
