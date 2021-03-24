package photonum.interfaces;
import photonum.squellete_appli;
import photonum.objects.Client;
import photonum.objects.Page;
import photonum.utils.*;

public class InterfacePage {

	public Page interfaceCreationPage(int idImpression) {
		System.out.println("Vous allez ici cr�er une page pour votre impression.");
		System.out.println("Rentrez votre mise en forme");
		String mef = LectureClavier.lireChaine();
		Page p = new Page(squellete_appli.conn,Page.lastId(),idImpression,mef);
		return p;
	}
}
