package photonum.interfaces;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ArticleDAO;
import photonum.dao.CommandeDAO;
import photonum.objects.*;
import photonum.utils.LectureClavier;


public class InterfaceCommande {

    public static void affichageCommande(Client c){
        CommandeDAO cmdDao=new CommandeDAO(PhotoNum.conn);
        List<Commande> commandesClient = cmdDao.readAllByClient(c);
        if(commandesClient.size()!=0){
            System.out.println("Voici toutes vos commandes :");
            for(int i=1;i<=commandesClient.size();i++){
                System.out.println(i+". "+commandesClient.get(i-1).getIdCommande()+
                "  | le "+commandesClient.get(i-1)+
                "  | status : "+commandesClient.get(i-1).getStatus()+
                "\n");
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
            }
        }else{
            System.out.println("Désolé mais vous n'avez pas fait encore de commande");
        }
    }
    public static void afficherDetailCommande(Commande c){
        ArticleDAO artDao=new ArticleDAO(PhotoNum.conn);
        List<Article> tabArticles = artDao.readAllByCommande(c);
        System.out.println("\n\nLes details de votre commande :");
        for(int i=1;i<=tabArticles.size();i++){
			System.out.println("    "+tabArticles.get(i-1).toString());
        }
    }
}
