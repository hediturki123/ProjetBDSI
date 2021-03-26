package photonum.interfaces;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import photonum.*;
import photonum.dao.ClientDAO;
import photonum.utils.*;
import photonum.objects.*;

public class InterfaceClient  {

	private static ClientDAO clientDao = new ClientDAO(PhotoNum.conn);

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
				case 1:
					connexion(); break;
				case 2:
					creationCompte(); break;
				case 3:
				default:
					break;
			}
		}
	}

    private static void connexion() {
		System.out.println(">>> Connexion...");
		Client clientCourant = null;

		UnaryOperator<Client> connect = c -> {
			System.out.println("Mail :");
			String mailConnexion=LectureClavier.lireChaine();
			System.out.println("Mot de passe :");
			String mdpConnexion=LectureClavier.lireChaine();
			String[] args = {mailConnexion, mdpConnexion};
			return clientDao.read(args);
		};
		clientCourant = connect.apply(null);

		while (clientCourant == null) {
			System.err.println(">>> Mot de passe ou identifiant incorrect !");
			clientCourant = connect.apply(null);
		}
		System.out.println(">>> Connexion réussie !");
		menu(clientCourant);
    }

    private static void creationCompte(){
		System.out.println("Veuillez entrer votre adresse mail :");
		String mail=LectureClavier.lireChaine();

		System.out.println("Veuillez entrer votre mdp :");
		String mdp=LectureClavier.lireChaine();

		System.out.println("Veuillez entrer votre nom :");
		String nom=LectureClavier.lireChaine();

		System.out.println("Veuillez entrer votre prenom :");
		String prenom=LectureClavier.lireChaine();
		Adresse addr=new Adresse() ;
		InterfaceAdresse.creerAdresse(mail, addr);


		Client c = new Client(mail, nom, prenom, mdp,addr.getNumeroRue(),addr.getNomRue(),addr.getVille(),addr.getCp(),addr.getPays(),true);
	
		if(clientDao.create(c)){
			menu(c);
		} else {
			System.out.println("Quelque chose s'est mal passé lors de la création du compte...");
		}
	}
 

    // TODO ici dans cette fonction mettre les fonctionnalité du client et l'envoyer dans les bonne interface
    public static void menu(Client c) {
		int choix=LectureClavier.lireEntier(
			"--- Menu Client ---\n" +
			"\t1. Afficher mes informations\n" +
			"\t2. Gérer les fichiers \n" +
			"\t3. Gérer une impression \n" +
			"\t4. Passer une commande\n" +
			"\t5. Se deconnecter\n" +
			"> "
		);
		while(choix!=5){
			switch (choix){
				case 1:menuInfo(c);
					break;
				case 2:InterfaceFichier.interfaceDemandeFichier(c);
					break;
				case 3:InterfaceImpression.menuImpression(c);
					break;
				case 4:InterfaceCommande.creationCommande(c);
					break;
				default:System.out.println("Veuilllez choisir entre 1,2,3,4,5 ! ");
						choix=LectureClavier.lireEntier("Alors ?");
            }
            choix=LectureClavier.lireEntier("\n1. Afficher mes informations  \n2. Gerer les fichiers \n3. Gerer une impression \n4. Commander \n5. Se deconnecter");
		}
		System.out.println("Merci d'utiliser nos services !");
	}
public static void menuInfo(Client c){
	System.out.println("que voulez vous faire ?");
	int choix=LectureClavier.lireEntier(
		"1. Mes informations personnelles\n"+
		"2. Mes Codes promos\n"+
		"3. Mes Commandes\n" +
		"4. Mes Impression\n" +
		"5. Mes Images Partagées\n"+
		"6. Retour au menu"
	);

	//ici faire boucler dans le
	while(choix!=6){
		while(!(choix>0 && choix<6)){
			choix=LectureClavier.lireEntier(
			"1. Mes informations personnelles\n"+
			"2. Mes Codes promos\n"+
			"3. Mes Commandes\n" +
			"4. Mes Impression\n" +
			"5. Mes Images Partagées\n"+
			"6. Retour au menu");
		};
			switch(choix){
				case 1 :afficherInfo(c);
					break;
				case 2 :InterfaceCodePromo.PresentationCodePromo(c);
					break;
				case 3 :InterfaceCommande.affichageCommande(c);
					break;
				case 4 :InterfaceImpression.interfaceVueImpression(c);
					break;
				case 5 :InterfaceFichier.afficherImagesPartage(c);
					break;
				case 6:break;

			}
			choix=LectureClavier.lireEntier(
				"1. Mes informations personnelles\n"+
				"2. Mes Codes promos\n"+
				"3. Mes Commandes\n" +
				"4. Mes Impression\n" +
				"5. Mes Images Partagées\n"+
				"6. Retour au menu");
	}
}
 public static void afficherInfo(Client c){
	 System.out.println(c.toString());
 }
}
