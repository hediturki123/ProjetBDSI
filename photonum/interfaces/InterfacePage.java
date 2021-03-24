package photonum.interfaces;

import photonum.objects.*;
import photonum.utils.LectureClavier;

public class InterfacePage {

	public static Page interfaceCreationPage(int idImpression) {
		System.out.println("Vous allez ici créer une page pour votre impression.");
		System.out.println("Rentrez votre mise en forme");
		String mef = LectureClavier.lireChaine();
		Page p = new Page(Page.lastId(),idImpression,mef);
		return p;
	}
}
