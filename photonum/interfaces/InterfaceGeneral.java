package photonum.interfaces;

import photonum.utils.LectureClavier;

public class InterfaceGeneral {
    public static void  menu(){
    int choix = LectureClavier.lireEntier(
        "1. Connexion client\n" +
        "2. Accès gestionnaire\n" + 
        "3. Quitter l'application\n"+
        ">"
    );
    while (choix!=3) {
        while(choix!=2 &&  choix!=1){
            choix = LectureClavier.lireEntier(
            "1. Connexion client\n" +
            "2. Accès gestionnaire\n" + 
            "3. Quitter l'application\n"+
            ">"
        );
        }
        switch (choix) {
            case 1: InterfaceClient.interfaceConnexion(); break;
            case 2: InterfaceGestion.menuPrincipal();
                break;
            case 3:break;
            default : break;
        }
        choix = LectureClavier.lireEntier(
            "1. Connexion client\n" +
            "2. Accès gestionnaire\n" + 
            "3. Quitter l'application\n"+
            ">"
        );
        
    }
    System.out.println("Merci de votre visite");
}
}
