package photonum.interfaces;

import java.util.List;

import photonum.objects.Adresse;
import photonum.objects.Client;
import photonum.utils.LectureClavier;

/**
 * cette class permet toutes les interaction avec un utilisateur
 * cela concerne ici que les Adresses de livraison
 */
public class InterfaceAdresse {

    /***
     * Cette fonction va permettre de demander à l'utilisateur de rentrer toutes les infos d'une adresse.

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
     * @param a Référence de l'{@link Adresse} à créer.
     */
    public static void creerAdresse(String mailClient, Adresse a){
        System.out.println(">>> Veuillez remplir les champs suivants s'il vous plaît.");
        int numeroRue=LectureClavier.lireEntier("Numéro de rue :");

		System.out.println("Intitulé de la rue (rue de la Pomme, avenue des Poires...) :");
		String nomRue=LectureClavier.lireChaine();

		System.out.println("Ville :");
		String ville=LectureClavier.lireChaine();

		int cp=LectureClavier.lireEntier("Code postal :");

		System.out.println("Pays :");
        String pays=LectureClavier.lireChaine();

        a = new Adresse(mailClient, numeroRue, nomRue, ville, cp, pays);
        System.out.println(">>> Nouvelle adresse créée avec succès !");
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
    public static void choixAdresseLivraison(Client c, Adresse addr) {
        List<Adresse> addrLivraison = c.getAdressesLivraison();
        if (addrLivraison.size() > 0) {
            int choix = -1;
            while (!(choix > 0 && choix <= addrLivraison.size())) {
                System.out.println("Vos adresses de livraison :");
                for (int i = 1; i <= addrLivraison.size(); i++) {
                    System.out.println(i + ". " + addrLivraison.get(i - 1).toString());
                }
                choix = LectureClavier.lireEntier("À quelle adresse de livraison voulez-vous envoyer votre commande ? ");
            }
            addr = addrLivraison.get(choix - 1);
        } else {
            System.out.println("Vous n'avez aucune adresse de livraison. Créez-en une maintenant !");
            creerAdresse(c.getMail(), addr);
            c.ajouterAdresseLivraison(addr);
        }
    }

}
