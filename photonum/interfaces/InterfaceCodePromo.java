package photonum.interfaces;

import java.util.List;

import javax.print.attribute.standard.PresentationDirection;

import photonum.PhotoNum;
import photonum.dao.CodePromoDAO;
import photonum.objects.Client;
import photonum.objects.CodePromo;

public class InterfaceCodePromo {

    public static void PresentationCodePromo(Client c){
        System.out.println("voici vos codes promo toujours actif :");
        CodePromoDAO cpDao=new CodePromoDAO(PhotoNum.conn);
        List<CodePromo> codeClients=cpDao.readAllByClient(c.getMail(),false);
        for(int i=1;i<=codeClients.size();i++){
            System.out.println("code n°"+i+" :"+codeClients.get(i-1).getCode());
        }
    }
}
