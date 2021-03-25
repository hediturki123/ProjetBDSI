package photonum.interfaces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import photonum.PhotoNum;
import photonum.dao.ArticleDAO;
import photonum.dao.CodePromoDAO;
import photonum.dao.CommandeDAO;
import photonum.dao.ImpressionDAO;
import photonum.dao.ProduitDAO;
import photonum.objects.*;
import photonum.utils.LectureClavier;


public class InterfaceCommande {
    private static CommandeDAO cmdDao=new CommandeDAO(PhotoNum.conn);
    //TODO creation commande
    public static void creationCommande(Client c){
        Commande cmd=new Commande();
        cmd.setIdCommande(cmdDao.getLastId()+1);
        choixImpression(c, cmd);

    }

    public static void choixImpression(Client c,Commande cmd){
        ImpressionDAO impDao=new ImpressionDAO(PhotoNum.conn);
        List<Impression> impressionClient=impDao.readAllByClient(c);
        List<Article> articleChoisi= new ArrayList<>();
        if(impressionClient.size() != 0) {
            int choix=-1;
            while(choix!=0){
                while(!(choix>0 && choix<=impressionClient.size())){
                    System.out.println("choissiez l'impression");
                    for(int i=1;i<=impressionClient.size();i++) {
                        System.out.println(i+". "+impressionClient.get(i-1).getTitre());
                    }
                    choix=LectureClavier.lireEntier(
                            "choisissez une impression pour la voir plus en detail dans la liste ci-dessus \n"+
                            "ou taper sur 0"
                    );
                }
                articleChoisi.add(
                    new Article(cmd.getIdCommande(),
                    impressionClient.get(choix-1).getIdImpression(),
                    LectureClavier.lireEntier("Quantite : "))
                );
            }
            utilisationCodePromo(c,cmd,articleChoisi);
		}else {
			System.out.println("Vous n'avez pas d'impressions.");
		}
        
    }

    public static void  utilisationCodePromo(Client c, Commande cmd,List<Article> articles){
        CodePromoDAO cpDao=new CodePromoDAO(PhotoNum.conn);
        List<CodePromo> cpDispo=cpDao.readAllByClient(c.getMail(), false);
        CodePromo cpUtiliser=new CodePromo();
        if(LectureClavier.lireOuiNon("voulez vous utiliser un code promo ? (o/n)")){
            if(cpDispo.size()>0){
                int choix=-1;
                while(!(choix>0 && choix<=cpDispo.size())){
                    System.out.println("vos code promo :");
                    for(int i=1;i<=cpDispo.size();i++){
                        System.out.println("    "+i+". "+cpDispo.get(i-1));
                    }
                    choix=LectureClavier.lireEntier(" choissisez dans la liste ci-dessus");
                }
                cpUtiliser=new CodePromo(cpDispo.get(choix-1).getMail(),cpDispo.get(choix-1).getCode(),cpDispo.get(choix-1).getEstUtilise());
            }else{
                System.out.println("Sha !! pas assez bon client pour avoir un code promo baltringue !!");
            }
        }else{
            System.out.println("pas de souci , mais tant pis pour vous SHA !");
        }
        livraison(c, cmd, articles, cpUtiliser);

    }

    public static void livraison(Client c,Commande cmd,List<Article> articles,CodePromo cp){
            if(LectureClavier.lireOuiNon("Voulez vous être livrée chez vous")){

            }else{

            }
    }


    public static void ValidationCommande(Client c,Commande cmd,List<Article> articles,CodePromo cp){
        System.out.println("voici le descriptif de votre commande :");
            System.out.println(cmd);
        if(LectureClavier.lireOuiNon(""))
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
