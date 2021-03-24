package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import photonum.utils.*;
import photonum.objects.*;
import photonum.*;
import photonum.dao.DAO;
import photonum.dao.FichierImageDAO;

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
        FichierImage img;
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
        img=new FichierImage(chemin,c.getMail(), infoPVD, resolution,false,Date.valueOf(LocalDate.now()));
        DAO<FichierImage> imgDAO = new FichierImageDAO(PhotoNum.conn);
        imgDAO.create(img);
    }

    public static void supprimerFichierImage(Client c){
    	//TODO A VERIFIER
        DAO<FichierImage> imgDAO=new FichierImageDAO(PhotoNum.conn);
        FichierImage [] imageClient=imgDAO.readAll(c);
        if(imageClient.length>0){
            System.out.println("Vos fichiers d'image : ");
            for(int i=1;i<=imageClient.length;i++){
                System.out.println(i+". "+imageClient[i-1].getChemin());
            }
            int choix=LectureClavier.lireEntier("choissisez quel photos vous voulez supprimez ? ");

            while(choix<1 && choix>imageClient.length){
                System.out.println("vous n'avez pas choissi un image existantes, veuillez recommencer");
                System.out.println("Vos fichiers d'image : ");
                for(int i=1;i<=imageClient.length;i++){
                    System.out.println(i+". "+imageClient[i-1].getChemin());
                }
                choix=LectureClavier.lireEntier("choissisez quel photos vous voulez supprimez ? ");
            }
            if(LectureClavier.lireOuiNon("êtes vous sur de la supression")){
                if(imgDAO.delete(imageClient[choix-1])){
                     System.out.println("votre fichier a bien été supprimer");
                }else{
                    System.out.println("votre fichier n'a pas pu etre supprimer veuillez reessayer");
                }
            }else{
                System.out.println("pas de souci votre image n'a pas été supprimer");
            }
            if(LectureClavier.lireOuiNon("voulez vous supprimer une autre image ? ")){
                supprimerFichierImage(c);
            }
        }else{
            System.out.println("Vous avez aucun fichier dans votre compte , pensez a en ajouter avant dans supprimer :) ");
        }

        
    }


}
