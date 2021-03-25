package photonum.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import photonum.utils.*;
import photonum.objects.*;
import photonum.*;
import photonum.dao.FichierImageDAO;

public class InterfaceFichier {
    
    public static void interfaceDemandeFichier(Client c){
        int choix=LectureClavier.lireEntier("\n1. Rajouter une image\n2. Supprimer une image");
        while(choix!=1 && choix!=2){
            System.err.println("votre entrez n'est pas valide");
            choix=LectureClavier.lireEntier("\n1. Rajouter une image\n2. Supprimer une image");
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

            while(choix<1 && choix>imageClient.size()){
                System.out.println("\nvous n'avez pas choissi un image existantes, veuillez recommencer");
                System.out.println("Vos fichiers d'image : ");
                for(int i=1;i<=imageClient.size();i++){
                    System.out.println(i+". "+imageClient.get(i-1).getChemin());
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


}
