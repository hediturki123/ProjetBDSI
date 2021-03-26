package photonum.interfaces;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.AdresseDAO;
import photonum.objects.Adresse;
import photonum.objects.Client;
import photonum.utils.LectureClavier;

public class InterfaceAdresse {



    public static void creerAdresse(String mailClient,Adresse a){
        int numeroRue=LectureClavier.lireEntier("veuillez entrez votre numero de rue");

		System.out.println("veuillez entrez votre rue");
		String nomRue=LectureClavier.lireChaine();

		System.out.println("veuillez entrez votre ville");
		String ville=LectureClavier.lireChaine();

		int cp=LectureClavier.lireEntier("veuillez entrez votre code postal");

		System.out.println("veuillez entrez votre pays ");
        String pays=LectureClavier.lireChaine();
        
        a=new Adresse(mailClient, numeroRue, nomRue, ville, cp, pays);

    }

    public static void choixAdresseLivraison(Client c,Adresse addr){
        AdresseDAO addrDao=new AdresseDAO(PhotoNum.conn);
        List<Adresse> addrLivraison=addrDao.readAllByClient(c);
        if(addrLivraison.size()>0){
            int choix=-1;
            while(!(choix>0 && choix<=addrLivraison.size())){
                System.out.println("vos adresse de livraison :");
                for(int i=1;i<=addrLivraison.size();i++){
                    System.out.println(i+". adresse n°"+i+" ="+addrLivraison.get(i-1).toString());
                }
                choix=LectureClavier.lireEntier("A quel adresse de livraison voulez vous envoyer votre commande ? ");
            }
            addr=addrLivraison.get(choix-1);
        }else{
            System.out.println("vous n'avez aucune adresse de livraison creer en une maintenant \n\nveuillez remplir les champs :");
            creerAdresse(c.getMail(), addr);
            addrDao.create(addr);
        }
    }

}
