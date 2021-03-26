package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import photonum.utils.*;
import photonum.objects.*;
import photonum.*;
import photonum.dao.FichierImageDAO;

public class InterfaceFichier {
    
    /**
     * permet de demarrer le menu de gestion des fichiers
     * qui se presente de la maniere suivante
     * <h3>Exemple</h3>
     * <table>
	 		<tr><td>"--- Menu Fichier ---"</td></tr>
	 		<tr><td>1. Rajouter une imager</td></tr>
			<tr><td>2. Supprimer une image</td></tr>
            <tr><td>3. Partager une image</td></tr>
            <tr><td>4. Revenir au menu</td></tr>
	* </table>
     * @param c le {@link Client} courant
     */
    public static void interfaceDemandeFichier(Client c){
        int choix=LectureClavier.lireEntier("\n1. Rajouter une image\n2. Supprimer une image\n3. Partager une image\n4. Revenir au menu");
        while(choix!=1 && choix!=2 && choix!=3 && choix!=4){
            System.err.println("votre entrez n'est pas valide");
            choix=LectureClavier.lireEntier("\n1. Rajouter une image\n2. Supprimer une image\n3. Partager une image\n4. Revenir au menu");
        }
        switch(choix){
            case 1:ajouterFichierImage(c);
                break;
            case 2:supprimerFichierImage(c);
                break;
            case 3:partagerFichier(c);
                break;
            case 4: break;          
        }
        
    }

    /**
     * Permet au client de choisir dans ses {@link FichierImage} les quels partager
     * @param c le {@link Client} courant 
     */
    public static void partagerFichier(Client c){
        int choix;
        FichierImageDAO imgDAO=new FichierImageDAO(PhotoNum.conn);
        List<FichierImage> imageClient=imgDAO.readAllByClient(c);
        if(imageClient.size()>0){
            System.out.println("Vos fichiers d'image : ");
            for(int i=1;i<=imageClient.size();i++){
                System.out.println(i+". "+imageClient.get(i-1).getChemin());
            }
            choix=LectureClavier.lireEntier("\nchoissisez quel fichier voulez-vous partager ? ");
            while(!(choix>0 && choix<=imageClient.size())){
                System.out.println("\nvous n'avez pas choissi un image existantes, veuillez recommencer");
                System.out.println("Vos fichiers d'image : ");
                for(int i=1;i<=imageClient.size();i++){
                    System.out.println(i+". "+imageClient.get(i-1).toString());
                }
                choix=LectureClavier.lireEntier("choissisez quel fichier voulez-vous paratger ? ");
            }
            if(LectureClavier.lireOuiNon("êtes vous sur de vouloir le partager (o/n)")){
                imageClient.get(choix-1).setEstPartage(true);
                if(imgDAO.update(imageClient.get(choix-1))){
                     System.out.println("votre fichier est maintenant partager");
                }else{
                    System.out.println("votre fichier n'a pas pu etre partager veuillez reessayer");
                }
            }else{
                System.out.println("pas de souci votre image n'a pas été partager :) ");
            }
            if(LectureClavier.lireOuiNon("\nvoulez vous partager une autre image ? (o/n) ")){
                partagerFichier(c);
            }
        }else{
            System.out.println(" vous n'avez aucun fichier , donc vous ne pouvez pas en partager");
        }

    }
    /**
     * Permet au client d'ajouter un {@link FichierImage}
     * @param c le {@link Client} courant 
     */
    public static void ajouterFichierImage(Client c){
        FichierImage img;
        System.out.println("\ndonnez moi le chemin du fichier");
        String chemin=LectureClavier.lireChaine();
        int choix=LectureClavier.lireEntier("choissier le format :\n1. Paysage\n2. Portrait");
        while(choix!=1 && choix!=2){
            System.err.println("vous devez choisier entre ces deux orientation");
            choix=LectureClavier.lireEntier("choissier le format :\n1. Paysage\n2. Portrait");
        }

        String infoPVD= choix==1 ? "Paysage" : "Portrait";
        int resolution=LectureClavier.lireEntier("donnez moi la resolution(entier)");
        img=new FichierImage(chemin,c.getMail(), infoPVD, resolution,false,Date.valueOf(LocalDate.now()));
        FichierImageDAO imgDAO = new FichierImageDAO(PhotoNum.conn);
        imgDAO.create(img);
    }
    /**
     * Permet au client de supprimer un {@link FichierImage}
     * @param c le {@link Client} courant 
     */
    public static void supprimerFichierImage(Client c){
        int choix;
        FichierImageDAO imgDAO=new FichierImageDAO(PhotoNum.conn);
        List<FichierImage> imageClient=imgDAO.readAllByClient(c);
        if(imageClient.size()>0){
            System.out.println("Vos fichiers d'image : ");
            for(int i=1;i<=imageClient.size();i++){
                System.out.println(i+". "+imageClient.get(i-1).getChemin());
            }
            choix=LectureClavier.lireEntier("\nchoissisez quel photos vous voulez supprimez ? ");

            while(!(choix>0 && choix<=imageClient.size())){
                System.out.println("\nvous n'avez pas choissi un image existantes, veuillez recommencer");
                System.out.println("Vos fichiers d'image : ");
                for(int i=1;i<=imageClient.size();i++){
                    System.out.println(i+". "+imageClient.get(i-1).toString());
                }
                choix=LectureClavier.lireEntier("choissisez quel photos vous voulez supprimez ? ");
            }
            if(LectureClavier.lireOuiNon("êtes vous sur de la supression (o/n)")){
                if(imgDAO.delete(imageClient.get(choix-1))){
                     System.out.println("votre fichier a bien été supprimer");
                }else{
                    System.out.println("votre fichier n'a pas pu etre supprimer veuillez reessayer");
                }
            }else{
                System.out.println("pas de souci votre image n'a pas été supprimer");
            }
            if(LectureClavier.lireOuiNon("\nvoulez vous supprimer une autre image ? (o/n) ")){
                supprimerFichierImage(c);
            }
        }else{
            System.out.println("Vous avez aucun fichier dans votre compte , pensez a en ajouter avant dans supprimer :) ");
        }

        
    }
    /**
     * Permet au client d'afficher les {@link FichierImage} Partager
     * @param c le {@link Client} courant 
     */
    public static void afficherImagesPartage(Client c){
        FichierImageDAO imageDAO=new FichierImageDAO(PhotoNum.conn);
        List<FichierImage> imagePartageClient= imageDAO.readAllByClient(c,true);
        System.out.println("\nVos images partagé sont : ");
        for(int i=1;i<=imagePartageClient.size();i++){
            System.out.println(i+". "+imagePartageClient.get(i-1).toString());
        }
    }


}
