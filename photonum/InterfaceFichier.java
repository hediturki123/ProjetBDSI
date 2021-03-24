package photonum;
public class InterfaceFichier {
    
    public static void interfaceDemandeFichier(Client c){
        int choix=LectureClavier.lireEntier("1. Rajouter une image\n2. Supprimer une image");
        while(choix!=1 && choix!=2){
            System.err.println("votre entrez n'est pas valide");
            choix=LectureClavier.lireEntier("1. Rajouter une image\n2. Supprimer une image");
        }
        switch(choix){
            case 1:ajouterFichierImage(c);
                break;
            case 2:supprimerFichierImage(c);
                break;           
        }
        
    }

    public static void ajouterFichierImage(Client c){
        System.out.println("donnez moi le chemin du fichier");
        String chemin=LectureClavier.lireChaine();
        System.out.println("choissier le format :\n 1. Paysage\n2. Portrait");
        String infoPVD=LectureClavier.lireChaine();
        while(!infoPVD.equals("Paysage") && !infoPVD.equals("Portrait")){
            System.err.println("vous devez choisier entre ces deux orientation");
            System.out.println("choissier le format :\n 1. Paysage\n2. Portrait");
            infoPVD=LectureClavier.lireChaine();
        }
        int resolution=LectureClavier.lireEntier("donnez moi la resolution");
        
    }

    public static void supprimerFichierImage(Client c){

    }


}
