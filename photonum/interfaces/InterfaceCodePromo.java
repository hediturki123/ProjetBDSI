package photonum.interfaces;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.CodePromoDAO;
import photonum.objects.Client;
import photonum.objects.CodePromo;

public class InterfaceCodePromo {

    public static void PresentationCodePromo(Client c){
        CodePromoDAO cpDao=new CodePromoDAO(PhotoNum.conn);
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
}
