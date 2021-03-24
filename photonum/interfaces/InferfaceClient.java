package photonum.interfaces;

import photonum.*;
import photonum.dao.ClientDAO;
import photonum.utils.*;
import photonum.objects.*;

public class InferfaceClient  {
    
    public static void interfaceConnexion(){
		int choix=LectureClavier.lireEntier("1 . se connecter ? \n2. creer un nouveau compte ? ");
		while(choix !=1 && choix!=2){
			System.out.println("vous devez choisir entre 1 ou 2 !");
			choix=LectureClavier.lireEntier("1 . se connecter ? \n2. creer un nouveau compte ? ");
		}
		if(choix==1){
			connexion();
		}else{
			creationCompte();
		}
	}
    
    public static void connexion(){
		ClientDAO c=new ClientDAO(PhotoNum.conn);
		System.out.println("veuillez entrez votre adresse mail");
		String mailConnexion=LectureClavier.lireChaine();
		System.out.println("veuillez entrez votre mot de passe");
		String mdpconnexion=LectureClavier.lireChaine();
        String [] args= new String[2];
        args[0]=mailConnexion;
	    args[1]=mdpconnexion;
        
		while(c.read(args)==null){
			System.err.println("mot de passe/ identifiant incorrect");
			System.out.println("veuillez entrez votre adresse mail");
			mailConnexion=LectureClavier.lireChaine();
			System.out.println("veuillez entrez votre mot de passe");
			mdpconnexion=LectureClavier.lireChaine();
			args[0]=mailConnexion;
			args[1]=mdpconnexion;
		}
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

	}
    
    //ici dans cette fonction mmettre les fonctionnalité du client et l'envoyer dans les bonne interface
    public static void menu(Client c){
		int choix=LectureClavier.lireEntier("1. Afficher mes informations  \n2. Telecharger un fichier \n3. \n4. \n5. Se deconnecter");
		while(choix!=5){
			switch (choix){
				case 1:afficherInfo(c);
					break;
				case 2:InterfaceFichier.interfaceDemandeFichier(c);
					break;
				case 3:
				break;
				case 4:break;
				default:System.out.println("Veuilllez choisir entre 1,2,3,4,5 ! ");
						choix=LectureClavier.lireEntier("Alors ?");
            }
            choix=LectureClavier.lireEntier("1. Afficher mes informations  \n2. Impression \n3. \n4. \n 5. Se deconnecter");
		}
		System.out.println("Merci de votre visite !");
 
	}
public static void afficherInfo(Client c){
	System.out.println(c.toString());
}
}
