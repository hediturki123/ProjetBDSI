package photonum.interfaces;

import java.util.function.UnaryOperator;

import photonum.utils.*;
import photonum.objects.*;

/**
 * Cette interface permet toutes les interactions avec un {@link Client}
 */
public class InterfaceClient  {

	/**
	 * on demarre le menu de l'interface client
	 * ici l'utilisateur a 3 choix qui se presente de cette manière :
	 *
	 * <h3>Exemple :</h3>
	 * <table>
	 		<tr><td>"--- Connexion Client ---"</td></tr>
	 		<tr><td>1. Se connecter</td></tr>
			<tr><td>2. Créer un nouveau compte</td></tr>
			<tr><td>3. Retour au menu principal</td></tr>
	* </table>

	<ul>
	<li>1 -> permet a l'utilisateur de se connecter en entrant son mail , et sont mot de passe</li>
	<li>2 -> permet de creer un nouveau compte</li>
	<li>3-> Sortir du menu et revenir au menuprincipal</li>
	</ul>
	*/
    public static void interfaceConnexion(){
		int choix = -1;
		while (choix != 3) {
			choix = LectureClavier.lireEntier(
				"--- Connexion Client ---\n" +
				"\t1. Se connecter\n" +
				"\t2. Créer un nouveau compte\n" +
				"\t3. Retour au menu principal\n" +
				"> "
			);
			switch (choix) {
				case 1: connexion(); break;
				case 2: creationCompte(); break;
				case 3: break;
				default: System.err.println("Veuillez indiquer un nombre entre 1 et 3."); break;
			}
		}
	}

	/**
	 *<p> Permet de demander les identifiants de connexion (mail, mdp) </p>
	 * <h3>Exemple :</h3>
	 * <table>
	 		<tr><td>>>> Connexion...</td></tr>
	 		<tr><td>Mail :</td></tr>
			<tr><td>XXXXXXX@XXX.XX</td></tr>
			<tr><td>Mot de passe :</td></tr>
			<tr><td>************</td></tr>
			<tr><td>>>> Connexion réussie !");</td></tr>
	* </table>
		<br>
		<h4>Si la connexion est refuser:</h4>
		alors le client devra recommencer ça saisi et aural le message <b>>>> Mot de passe ou identifiant incorrect !</b>
		<b>Il vous reste  XX essais <b>
	 */
    private static void connexion() {
		System.out.println(">>> Connexion...");
		Client clientCourant = null;

		UnaryOperator<Client> connect = c -> {
			System.out.println("Mail :");
			String mail = LectureClavier.lireChaine();
			System.out.println("Mot de passe :");
			String mdp = LectureClavier.lireChaine();
			return Client.connexion(mail, mdp);
		};
		clientCourant = connect.apply(null);
		int essais = 3;
		while (clientCourant == null && essais > 0) {
			essais--;
			System.err.println(">>> Mot de passe ou identifiant incorrect ! ("+ essais + " essais restants)");
			clientCourant = connect.apply(null);

		}
		if (clientCourant != null) {
			System.out.println(">>> Connexion réussie !");
			menu(clientCourant);
		} else {
			System.out.println(">>> Réessayez de vous connecter ultérieurement.");
		}
    }

	/**
	 * permet de faire la creation complete d'un compte client
	 * en lui demandant Mail , mdp , nom , prenom , et toutes sont adresse
	 * Au moment ou le mail
	 * <h3>Exemple :</h3>
	 * <table>
	 *	<tr><td>Veuillez entrer votre adresse mail : </td></tr>
	 *	<tr><td>XXXXXXX@XXX.XX</td></tr>
	 *	<tr><td>Mot de passe :</td></tr>
	 *	<tr><td>************</td></tr>
	 *	<tr><td>Veuillez entrer votre nom :</td></tr>
	 *	<tr><td>XXXX</td></tr>
	 *	<tr><td>Veuillez entrer votre prenom :</td></tr>
	 *	<tr><td>XXXX</td></tr>
	 *	<tr><td>veuillez entrez votre numero de rue</td></tr>
	 *	<tr><td> XXX</td></tr>
	 *	<tr><td>veuillez entrez votre rue</td></tr>
	 *	<tr><td>XXXXX XXXXXX XXXXXXX</td></tr>
	 *	<tr><td>veuillez entrez votre ville</td></tr>
	 *	<tr><td>XXXXXXX</td></tr>
	 *	<tr><td>veuillez entrez votre code postal</td></tr>
	 *	<tr><td>XXXXX</td></tr>
	 *	<tr><td>veuillez entrez votre pays</td></tr>
     *  <tr><td>XXXXX</td></tr>
	 * </table>
	 * <h4>Probleme</h4>
	 */
    private static void creationCompte(){
		System.out.println(">>> Veuillez remplir les champs suivants s'il vous plaît.");
		System.out.println("Adresse mail :");
		String mail = LectureClavier.lireChaine();

		System.out.println("Mot de passe :");
		String mdp = LectureClavier.lireChaine();

		System.out.println("Nom de famille :");
		String nom = LectureClavier.lireChaine();

		System.out.println("Prénom :");
		String prenom = LectureClavier.lireChaine();

		int numeroRue = LectureClavier.lireEntier("Numéro de rue :");

		System.out.println("Intitulé de la rue (rue de la Pomme, avenue des Poires...) :");
		String nomRue = LectureClavier.lireChaine();

		System.out.println("Ville :");
		String ville = LectureClavier.lireChaine();

		int cp = LectureClavier.lireEntier("Code postal :");

		System.out.println("Pays :");
		String pays = LectureClavier.lireChaine();

		Client c = new Client(mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays);

		if (c.nouveauCompte()) {
			System.out.println(">>> Compte créé avec succès !");
			menu(c);
		} else {
			System.out.println(">>> Quelque chose s'est mal passé lors de la création du compte...");
		}
	}

	/**
	 * Permet d'arriver sur le menu principal du client
	 * Le menu ce presente de la maniere suivante :
	 * <h3>Exemple :</h3>
	 * <table>
	 *		<tr><td>--- Menu Client ---</td></tr>
	 *		<tr><td>1. Afficher mes informations</td></tr>
	 *		<tr><td>2. Gérer les fichiers </td></tr>
	 *		<tr><td>3. Gérer une impression </td></tr>
	 *		<tr><td>4. Passer une commande</td></tr>
	 *		<tr><td>5. Modifier une commande</td></tr>
	 *		<tr><td>6. Se deconnecter</td></tr>
	 * </table>
	 * @param c le {@link Client} courant
	 */
    public static void menu(Client c) {
		int choix = -1;
		while (choix != 6) {
			choix=LectureClavier.lireEntier(
				"--- Menu Client ---\n" +
				"\t1. Afficher mes infos\n" +
				"\t2. Gérer les fichiers \n" +
				"\t3. Gérer une impressions \n" +
				"\t4. Passer une commande\n" +
				"\t5. Modifier une commande\n" +
				"\t6. Se deconnecter\n" +
				"> "
			);
			switch (choix) {
				case 1: menuInfo(c); break;
				case 2: InterfaceFichier.interfaceDemandeFichier(c); break;
				case 3: InterfaceImpression.menuImpression(c); break;
				case 4: InterfaceCommande.creationCommande(c); break;
				case 5: InterfaceCommande.MenuModifCommande(c);break;
				case 6:break;
				default: System.err.println("Veuillez indiquer un nombre entre 1 et 5."); break;
            }
		}
		System.out.println("Merci d'utiliser nos services !");
	}


	/**
	 * Permet d'avoir le menu de visualisations des imformation client(info,code pormo, commandes, impression, image partagé)
	 *  Le menu ce presente de la maniere suivante :
	 * <h3>Exemple :</h3>
	 * <table>
	 *		<tr><td>--- Menu Visualisation ---</td></tr>
	 *		<tr><td>1. Mes informations personnelles</td></tr>
	 *		<tr><td>2. Mes Codes promos </td></tr>
	 *		<tr><td>3. Mes Commandes </td></tr>
	 *		<tr><td>4. Mes Impressions</td></tr>
	 *		<tr><td>5. Mes Images Partagées</td></tr>
	 *		<tr><td>6. Retour au menu</td></tr>
	 * </table>
	 * @param c le {@link Client} courant
	 */
	public static void menuInfo(Client c){
		int choix = -1;
		while (choix != 6) {
			choix = LectureClavier.lireEntier(
				"--- Mon compte ---\n" +
				"\t1. Mes Infos\n"+
				"\t2. Mes Codes Promo\n"+
				"\t3. Mes Commandes\n" +
				"\t4. Mes Impressions\n" +
				"\t5. Mes Images Partagées\n"+
				"\t6. Retour au menu\n" +
				"> "
			);
			switch(choix){
				case 1: afficherInfo(c); break;
				case 2: InterfaceCodePromo.presentationCodePromo(c); break;
				case 3: InterfaceCommande.affichageCommande(c); break;
				case 4: InterfaceImpression.interfaceVueImpression(c); break;
				case 5: InterfaceFichier.afficherImagesPartage(c); break;
				case 6: break;
				default: System.err.println("Veuillez indiquer un nombre entre 1 et 6."); break;
			}
		}
	}

	/**
	 * Affiche les informations du client à travers son toString();
	 * @param c le {@link Client} courant
	 */
	public static void afficherInfo(Client c){
		System.out.println(c.toString());
	}
}
