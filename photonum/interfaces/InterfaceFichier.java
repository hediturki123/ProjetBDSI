package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import photonum.utils.*;
import photonum.objects.*;

public class InterfaceFichier {

    /**
     * permet de demarrer le menu de gestion des fichiers
     * qui se presente de la maniere suivante
     * <h3>Exemple</h3>
     * <table>
	 		<tr><td>"--- Menu images ---"</td></tr>
	 		<tr><td>1. Rajouter une imager</td></tr>
			<tr><td>2. Supprimer une image</td></tr>
            <tr><td>3. Partager une image</td></tr>
            <tr><td>4. Revenir au menu</td></tr>
	* </table>
     * @param c le {@link Client} courant
     */
    public static void interfaceDemandeFichier(Client c){
        int choix = -1;
        while (choix != 4) {
            choix = LectureClavier.lireEntier(
                "--- Mes images ---\n" +
                "\t1. Rajouter une image\n"+
                "\t2. Supprimer une image\n"+
                "\t3. Partager une image\n"+
                "\t4. Revenir au menu\n"+
                "> "
            );
            switch (choix) {
                case 1: ajouterFichierImage(c); break;
                case 2: supprimerFichierImage(c); break;
                case 3: partagerFichier(c); break;
                case 4: break;
                default: System.err.println("Veuillez indiquer un nombre entre 1 et 4."); break;
            }
        }
    }

    /**
     * Permet au client de choisir dans ses {@link FichierImage} les quels partager
     * @param c le {@link Client} courant
     */
    public static void partagerFichier(Client c){
        int choix = -1;
        List<FichierImage> imagesClient = c.getImages();

        if (imagesClient.size() > 0) {
            int last=imagesClient.size()+1;
            boolean choisi=false;
            while (choix!=last && !choisi) {  
                String message="Vos fichiers d'image : \n";
                 for (int i = 1; i <= imageClient.size(); i++) {
                    message+="\t"+i + ". " + imageClient.get(i - 1).getChemin()+"\n";
                }
                message+="\t"+last+". Sortir";
                message+="\nchoissisez quel fichier voulez-vous paratger ? ";
                choix=LectureClavier.lireEntier(message);

                if(choix<0 || choix>last){
                    System.out.println("\nVous n'avez pas choisi une image existante, veuillez recommencer.");
                }else if (choix!=last){
                    choisi=true;
                }
            }
            if(choisi){
                //TODO tester si ça fonctionne ( partager un fichier )
                if (LectureClavier.lireOuiNon("Êtes-vous sur de vouloir le partager ? (o/n)")) {
                    imagesClient.get(choix - 1).setEstPartage(true);
                    if (imagesClient.get(choix - 1).mettreAJour()) {
                        System.out.println("Votre fichier est maintenant partagé !");
                    } else {
                        System.out.println("Votre fichier n'a pas pu être partagé, veuillez réessayer.");
                    }
                } else {
                    System.out.println("Votre image n'a pas été partagée.");
                }
                if(c.getImages(false).size() > 0)
                    if (LectureClavier.lireOuiNon("\nVoulez-vous partager une autre image ? (o/n)")) {
                        partagerFichier(c);
                    }
                else{
                    System.out.println("Toutes vos images sont déjà publiques.");
                }
            }
        } else {
            System.out.println("Vous n'avez aucun fichier image à partager.");
        }
    }
    /**
     * Permet au client d'ajouter un {@link FichierImage}
     * @param c le {@link Client} courant
     */
    public static void ajouterFichierImage(Client c){
        FichierImage img;
        System.out.println("\nDonnez le chemin du fichier à ajouter :");
        String chemin = LectureClavier.lireChaine();
        int choix = LectureClavier.lireEntier("Choisissez son format :\n1. Paysage\n2. Portrait");
        while (choix != 1 && choix != 2) {
            System.err.println("Vous devez choisir entre ces deux orientations.");
            choix = LectureClavier.lireEntier("Choisissez son format :\n1. Paysage\n2. Portrait");
        }
        String infoPVD = choix == 1 ? "Paysage" : "Portrait";
        int resolution = LectureClavier.lireEntier("donnez moi la resolution(entier)");
        img = new FichierImage(chemin, c.getMail(), infoPVD, resolution, false, Date.valueOf(LocalDate.now()));
        img.nouvelleImage();
    }
    /**
     * Permet au client de supprimer un {@link FichierImage}
     * @param c le {@link Client} courant
     */
    public static void supprimerFichierImage(Client c){
        int choix;
        List<FichierImage> imageClient = c.getImages(false);
        if (imageClient.size() > 0) {
            System.out.println("Vos fichiers d'image : ");
            for (int i = 1; i <= imageClient.size(); i++) {
                System.out.println(i + ". " + imageClient.get(i - 1).getChemin());
            }
            choix = LectureClavier.lireEntier("\nchoissisez quel photos vous voulez supprimez ? ");

            while (!(choix > 0 && choix <= imageClient.size())) {
                System.out.println("\nVous n'avez pas choissi un image existantes, veuillez recommencer");
                System.out.println("Vos fichiers d'image : ");
                for (int i = 1; i <= imageClient.size(); i++) {
                    System.out.println(i + ". " + imageClient.get(i - 1).toString());
                }
                choix = LectureClavier.lireEntier("Choisissez quelles photos vous voulez supprimer :");
            }
            if (LectureClavier.lireOuiNon("Êtes-vous sûr de vouloir supprimer cette image ? (o/n)")) {
                if (imageClient.get(choix - 1).supprimer()) {
                    System.out.println("Votre fichier a bien été supprimé !");
                } else {
                    System.out.println("Votre fichier n'a pas pu être supprimé, veuillez reessayer.");
                }
            } else {
                System.out.println("Votre image n'a pas été supprimée.");
            }
            if (c.getImages(false).size() > 0)
                if (LectureClavier.lireOuiNon("\nVoulez-vous supprimer une autre image ? (o/n)")) {
                    supprimerFichierImage(c);
                }
            else{
                System.out.println("Vous n'avez plus aucun fichier à supprimer.");
            }
        } else {
            System.out.println("Vous avez aucun fichier associé votre compte, pensez à en ajouter avant d'en supprimer !");
        }
    }
    /**
     * Permet au client d'afficher les {@link FichierImage} Partager
     * @param c le {@link Client} courant
     */
    public static void afficherImagesPartage(Client c){
        List<FichierImage> imagePartageClient = c.getImages(true);
        if (imagePartageClient.size() > 0) {
            System.out.println("\nVos images partagé sont : ");
            for (int i = 1; i <= imagePartageClient.size(); i++) {
                System.out.println(i + ". " + imagePartageClient.get(i - 1).toString());
            }
        } else {
            System.out.println("vous n'avez aucune image partagées");
        }
    }

}
