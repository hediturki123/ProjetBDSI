package photonum.interfaces;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.AdresseDAO;
import photonum.objects.Adresse;
import photonum.objects.Client;
import photonum.utils.LectureClavier;


/**
 * cette class permet toutes les interaction avec un utilisateur
 * cela concerne ici que les Adresses de livraison
 */
public class InterfaceAdresse {


    /***
     * Cette fonction vas permettre de demander à l'utilsateur de rentrer toutes les infos d'une adresse
    
      <h3>Exemple :</h3>
	 * <table>
	 		<tr><td>veuillez entrez votre numero de rue</td></tr>
	 		<tr><td>XXX</td></tr>
			<tr><td>veuillez entrez votre rue</td></tr>
			<tr><td>XXXXX XXXXXX XXXXXXX</td></tr>
			<tr><td>veuillez entrez votre ville</td></tr>
            <tr><td>XXXXXXX</td></tr>
            <tr><td>veuillez entrez votre code postal</td></tr>
            <tr><td>XXXXX</td></tr>
            <tr><td>veuillez entrez votre pays</td></tr>
            <tr><td>XXXXX</td></tr>
	* </table>
     * @param mailClient
     * @param a une reference d'{@link Adresse}
     */

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
    /**
     * permet a l'utilisateur lors de ça commande de choisir sont adresse de livraison
     * <ul>
     *  <li> Si il en a déjà : alors il choisi dans une liste </li>
     *  <li> Sinon : il en creer une avec la fonction {@link InterfaceAdresse.creerAdresse()}
     *</ul>

     * @param c
     * @param addr
     */
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
