package photonum.interfaces;

import photonum.*;
import photonum.dao.ClientDAO;
import photonum.utils.*;
import photonum.objects.*;

public class InterfaceClient  {
    
    public static void interfaceConnexion(){
		int choix=LectureClavier.lireEntier("1 . se connecter ? \n2. creer un nouveau compte ?\n3. retour ");
		while(choix !=1 && choix!=2 && choix!=3){
			System.out.println("vous devez choisir entre 1 ou 2 !");
			choix=LectureClavier.lireEntier("1 . se connecter ? \n2. creer un nouveau compte ? ");
		}
		switch(choix){
			case 1:connexion();
					break;
			case 2:creationCompte();
					break;
			case 3:
				break;
		}
	}
    
    public static void connexion(){
		ClientDAO c=new ClientDAO(PhotoNum.conn);
		Client clientCourant;
		System.out.println("veuillez entrez votre adresse mail");
		String mailConnexion=LectureClavier.lireChaine();
		System.out.println("veuillez entrez votre mot de passe");
		String mdpconnexion=LectureClavier.lireChaine();
        String [] args= new String[2];
        args[0]=mailConnexion;
	    args[1]=mdpconnexion;
        
		while((clientCourant=c.read(args))==null){
			System.err.println("\nmot de passe/ identifiant incorrect");
			System.out.println("veuillez entrez votre adresse mail");
			mailConnexion=LectureClavier.lireChaine();
			System.out.println("veuillez entrez votre mot de passe");
			mdpconnexion=LectureClavier.lireChaine();
			args[0]=mailConnexion;
			args[1]=mdpconnexion;
		}
		menu(clientCourant);
    }

    public static void creationCompte(){
		System.out.println("veuillez entrez votre adresse mail");
		String mail=LectureClavier.lireChaine();
		
		System.out.println("veuillez entrez votre mdp");
		String mdp=LectureClavier.lireChaine();
		
		System.out.println("veuillez entrez votre nom");
		String nom=LectureClavier.lireChaine();

		System.out.println("veuillez entrez votre prenom");
		String prenom=LectureClavier.lireChaine();

		int numeroRue=LectureClavier.lireEntier("veuillez entrez votre numero de rue");

		System.out.println("veuillez entrez votre rue");
		String nomRue=LectureClavier.lireChaine();

		System.out.println("veuillez entrez votre ville");
		String ville=LectureClavier.lireChaine();

		int cp=LectureClavier.lireEntier("veuillez entrez votre code postal");

		System.out.println("veuillez entrez votre pays ");
		String pays=LectureClavier.lireChaine();

		ClientDAO clientDao=new ClientDAO(PhotoNum.conn);
		Client c = new Client(mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays);
	
		if(clientDao.create(c)){
			menu(c);
		}else{
			System.out.println("votre creation n'a pas marcher veuillez recommencer");
			creationCompte();
		}

	}
    
    //ici dans cette fonction mmettre les fonctionnalité du client et l'envoyer dans les bonne interface
    public static void menu(Client c){
		int choix=LectureClavier.lireEntier("\n1. Afficher mes informations  \n2. Gerer les fichiers \n3. \n4. \n5. Se deconnecter");
		while(choix!=5){
			switch (choix){
				case 1:menuInfo(c);
					break;
				case 2:InterfaceFichier.interfaceDemandeFichier(c);
					break;
				case 3:
				break;
				case 4:break;
				default:System.out.println("Veuilllez choisir entre 1,2,3,4,5 ! ");
						choix=LectureClavier.lireEntier("Alors ?");
            }
            choix=LectureClavier.lireEntier("\n1. Afficher mes informations  \n2. Gerer les fichiers \n3. \n4. \n5. Se deconnecter");
		}
		System.out.println("Merci de votre visite !");
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
	while(!(choix>0 && choix<6)){
		choix=LectureClavier.lireEntier(
		"1. Mes informations personnelles\n"+
		"2. Mes Codes promos\n"+
		"3. Mes Commandes\n" +
		"4. Mes Impression\n" +
		"5. Mes Images Partagées\n"+
		"6. Retour au menu"
	);
	}
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
 }
 public static void afficherInfo(Client c){
	 System.out.println(c.toString());
 }
}
