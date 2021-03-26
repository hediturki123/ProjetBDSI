package photonum.interfaces;

import photonum.utils.LectureClavier;


/**
 * cette class permet de demarrer le menu de l'application
 */
public class InterfaceGenerale {

    /**
     * Demarrage du menu de l'application
     * sous cette forme :
     *  <h3>Exemple</h3>
     * <table>
	 		<tr><td>=== PHOTONUM ===</td></tr>
	 		<tr><td>1. Connexion client</td></tr>
			<tr><td>2. Accès gestionnaire</td></tr>
            <tr><td>3. Quitter l'application</td></tr>
	* </table>
     */
    public static void  menu() {
        int choix = -1;
        while (choix !=3) {
            choix = LectureClavier.lireEntier(
                "=== PHOTONUM ===\n" +
                "\t1. Connexion client\n" +
                "\t2. Accès gestionnaire\n" +
                "\t3. Quitter l'application\n"+
                ">"
            );
            switch (choix) {
                case 1: InterfaceClient.interfaceConnexion(); break;
                case 2: InterfaceGestion.menuPrincipal(); break;
                case 3: break;
                default: System.err.println("Veuillez indiquer un nombre entre 1 et 3."); break;
            }
        }
        System.out.println("À bientôt sur PhotoNum !");
    }
}
