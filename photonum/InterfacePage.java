package photonum;

public class InterfacePage {

	public Page interfaceCreationPage(int idImpression) {
		System.out.println("Vous allez ici créer une page pour votre impression.");
		System.out.println("Rentrez votre mise en forme");
		String mef = LectureClavier.lireChaine();
		Page p = new Page(squellete_appli.conn,Page.lastId(),idImpression,mef);
		return p;
	}
}
