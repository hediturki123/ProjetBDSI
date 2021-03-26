package photonum.interfaces;

import photonum.utils.LectureClavier;

public class InterfaceGenerale {

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
