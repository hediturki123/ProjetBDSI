package photonum.interfaces;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.CodePromoDAO;
import photonum.objects.Article;
import photonum.objects.Client;
import photonum.objects.CodePromo;
import photonum.objects.Commande;
import photonum.utils.LectureClavier;


/**
 * cette class permet toutes les interaction avec un utilisateur
  pour tous ce qui concerne les {@link CodePromo}    
 */
public class InterfaceCodePromo {

    public static CodePromoDAO cpDao=new CodePromoDAO(PhotoNum.conn);
    
    
    public static void PresentationCodePromo(Client c){
        List<CodePromo> codeClients=cpDao.readAllByClient(c.getMail(),false);
        if(codeClients.size()!=0){
            System.out.println("voici vos codes promo toujours actif :");
            for(int i=1;i<=codeClients.size();i++){
                System.out.println("code n°"+i+" :"+codeClients.get(i-1).getCode());
            }
        }else{
            System.out.println("Désolé mais vous n'avez aucun code promo ....\n");
        }
    }

    public static void  utilisationCodePromo(Client c, Commande cmd,List<Article> articles){
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
        cmd.setCodePromo(cpUtiliser.getCode());
        InterfaceCommande.livraison(c, cmd, articles);

    }
}
