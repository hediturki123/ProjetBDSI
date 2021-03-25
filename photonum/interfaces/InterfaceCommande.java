package photonum.interfaces;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ArticleDAO;
import photonum.dao.CommandeDAO;
import photonum.dao.ImpressionDAO;
import photonum.objects.*;
import photonum.utils.LectureClavier;


public class InterfaceCommande {
    private static CommandeDAO cmdDao=new CommandeDAO(PhotoNum.conn);
    //TODO creation commande
    public static void creationCommande(Client c){
        Commande cmd=new Commande();
        choixImpression(c, cmd);


    }

    public static void choixImpression(Client c,Commande cmd){
        ImpressionDAO impDao=new ImpressionDAO(PhotoNum.conn);
        List<Impression> impressionClient=impDao.readAllByClient(c);
        if(impressionClient.size() != 0) {
			int i = 1;
			int choix;
			for(Impression imp: impressionClient) {
				System.out.println(i+". Le titre de l'impression est: "+imp.getTitre());
				System.out.println("Voulez voir les details de l'impression?\n1. Oui\n2. Non");
				choix = LectureClavier.lireEntier("Oui/Non");
				while(choix != 1 && choix != 2) {
					System.out.println("Choisissez 1 ou 2.");
					choix = LectureClavier.lireEntier("Oui/Non");
				}
				if(choix==1) {
					imp.toString();
				}
				i++;
			}
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
        
    }









    public static void affichageCommande(Client c){
        List<Commande> commandesClient = cmdDao.readAllByClient(c);
        if(commandesClient.size()!=0){
            System.out.println("Voici toutes vos commandes :");
            for(int i=1;i<=commandesClient.size();i++){
                System.out.println(i+". "+commandesClient.get(i-1).toString());
            }
            int choix=LectureClavier.lireEntier(
                "si vous voulez plus de detail sur une commande\n"+ 
                "choissiez une commande dans la liste ci-dessus\n"+
                "sinon taper 0"
                );
            while(choix!=0){
                    while(!(choix>0 && choix<=commandesClient.size())){
                        System.out.println("\nvous n'avez pas mis un numero de commande valide\n");
                        System.out.println("Voici toutes vos commandes :");
                        for(int i=1;i<=commandesClient.size();i++){
                            System.out.println(i+". "+commandesClient.get(i-1).toString());
                        }
                        choix=LectureClavier.lireEntier(
                            "si vous voulez plus de detail sur une commande\n"+ 
                            "choissiez une commande dans la liste ci-dessus\n"+
                            "sinon taper 0"
                            );
                    }
                afficherDetailCommande(commandesClient.get(choix-1));

                System.out.println("Voici toutes vos commandes :");
                for(int i=1;i<=commandesClient.size();i++){
                    System.out.println(i+". "+commandesClient.get(i-1).toString());
                }
                choix=LectureClavier.lireEntier(
                            "si vous voulez plus de detail sur une commande\n"+ 
                            "choissiez une commande dans la liste ci-dessus\n"+
                            "sinon taper 0"
                            );
            }
        }else{
            System.out.println("Désolé mais vous n'avez pas fait encore de commande \n");
        }
    }
    public static void afficherDetailCommande(Commande c){
        ArticleDAO artDao=new ArticleDAO(PhotoNum.conn);
        List<Article> tabArticles = artDao.readAllByCommande(c);
        System.out.println("\n\nLes details de votre commande :");
        if(tabArticles.size()>0){
            for(int i=1;i<=tabArticles.size();i++){
                System.out.println("    "+tabArticles.get(i-1).toString());
            }
        }else{
            System.out.println("    aucun article dans votre commande !\n");
        }
    }
}
