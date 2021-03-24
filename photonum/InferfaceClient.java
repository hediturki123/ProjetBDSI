package photonum;
public class InferfaceClient  {
    
    public static void interfaceConnexion(){
        Client c= new Client(squellete_appli.conn);
		int choix=LectureClavier.lireEntier("1 . se connecter ? \n2. creer un nouveau compte ? ");
		while(choix !=1 && choix!=2){
			System.out.println("vous devez choisir entre 1 ou 2 !");
			choix=LectureClavier.lireEntier("1 . se connecter ? \n2. creer un nouveau compte ? ");
		}
		if(choix==1){
			connexion(c);
		}else{
			creationCompte(c);
		}
	}
    
    public static void connexion(Client c){
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
        c=c.read(args);
        menu(c);
    }

    public static void creationCompte(Client c){
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
        
        c.create(new Client(c.connect, mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays));
        menu(c);
	}
    
    //ici dans cette fonction mmettre les fonctionnalité du client et l'envoyer dans les bonne interface
    public static void menu(Client C){
		int choix=LectureClavier.lireEntier("1. Afficher mes informations  \n2. Impression \n3. \n4. \n5. Se deconnecter");
		while(choix!=5){
			switch (choix){
				case 1:
					break;
				case 2:break;
				case 3:break;
				case 4:break;
				default:System.out.println("Veuilllez choisir entre 1,2,3,4,5 ! ");
						choix=LectureClavier.lireEntier("Alors ?");
            }
            choix=LectureClavier.lireEntier("1. Afficher mes informations  \n2. Impression \n3. \n4. \n 5. Se deconnecter");
		}
		System.out.println("Merci de votre visite !");
 
	}	
}
