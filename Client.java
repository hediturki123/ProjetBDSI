import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Client extends DAO<Client> {
	private String mail;
	private String nom;
	private String prenom;
	private String mdp;
	private int numeroRue;
	private String nomRue;
	private String ville;
	private int cp;
	private String pays;
	
	public Client(Connection conn){
		super(conn);
	}
	
	public Client(Connection conn, String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
			String ville, int cp, String pays) {
		super(conn);
		this.setAll(mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays);
	}

	public void interfaceConnexion(){
		System.out.println("1 . se connecter ? \n2. creer un nouveau compte ? ");
		int choix=LectureClavier.lireEntier("1/2 ?");
		while(choix !=1 && choix!=2){
			System.out.println("vous devez choisir entre 1 ou 2 !");
			choix=LectureClavier.lireEntier("1/2 ?");
		}
		if(choix==1){
			this.connexion();
		}else{
			this.creationCompte();
		}
	}
	public void connexion(){
		System.out.println("veuillez entrez votre adresse mail");
		String mailConnexion=LectureClavier.lireChaine();
		System.out.println("veuillez entrez votre mot de passe");
		String mdpconnexion=LectureClavier.lireChaine();
		String [] args={mailConnexion,mdpconnexion};
		Client c;
		while(( c = this.read(args))==null){
			System.out.println("mot de passe/ identifiant incorrect");
			System.out.println("veuillez entrez votre adresse mail");
			mailConnexion=LectureClavier.lireChaine();
			System.out.println("veuillez entrez votre adresse mail");
			mdpconnexion=LectureClavier.lireChaine();
			args[0]=mailConnexion;
			args[1]=mdpconnexion;
		}
		if(c!=null){
			this.setAll(c.mail, c.nom, c.prenom,c.mdp, c.numeroRue, c.nomRue, c.ville, c.cp, c.pays);
		}
		this.Menu();
	}

	public void Menu(){
		System.out.println("1. Afficher mes informations  \n2. Impression \n3. \n4. \n 5. Se deconnecter");
		int choix=LectureClavier.lireEntier("?");
		while(choix!=5){
			switch (choix){
				case 1:System.out.println(this.mail);
				System.out.println(this.nom);
				System.out.println(this.numeroRue);
				System.out.println(this.nomRue);
				System.out.println(this.ville);
				System.out.println(this.cp);
					break;
				case 2:break;
				case 3:break;
				case 4:break;
				default:System.out.println("Veuilllez choisir entre 1,2,3,4,5 ! ");
						choix=LectureClavier.lireEntier("Alors ?");
			}
		}
		System.out.println("Merci de votre visite !");

	}	
	public void creationCompte(){
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

		Client c = new Client(this.connect, mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays);
			this.setMail(c.mail);
			this.setMdp(c.mdp);
			this.setNom(c.nom);
			this.setPrenom(c.prenom);
			this.setNomRue(c.nomRue);
			this.setNumeroRue(c.numeroRue);
			this.setVille(c.ville);
			this.setCp(c.cp);
			this.setPays(c.pays);
			this.create(c);
	}

	@Override
	public boolean create(Client obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement requete_ajout=this.connect.prepareStatement(
					"INSERT INTO LesClients VALUES (?,?,?,?,?,?,?,?,?)");
			requete_ajout.setString(1, mail);
			requete_ajout.setString(2, nom);
			requete_ajout.setString(3, prenom);
			requete_ajout.setString(4, mdp);
			requete_ajout.setInt(5, numeroRue);
			requete_ajout.setString(6, nomRue);
			requete_ajout.setString(7, ville);
			requete_ajout.setInt(8,cp);
			requete_ajout.setString(9, pays);
			
			return requete_ajout.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Client read(Object obj) {
		// TODO Auto-generated method 
		try {
			String [] args=(String[]) obj;
			Client c;
			PreparedStatement requete_selection=this.connect.prepareStatement(
					"SELECT * from LesClients where mail=? and mdp=?");
			requete_selection.setString(1,args[0]); // icic l'obj sera l'adresse mail
			requete_selection.setString(2,args[1]);
			ResultSet resultat=requete_selection.executeQuery();
			if(!resultat.next()){
				c=null;
			} //ici last renvoie false si il n'y a aucune row selectionner
			else{
			 c= new Client
			(this.connect,
			resultat.getString("mail"),
			resultat.getString("nom"),
			resultat.getString("prenom"),
			resultat.getString("mdp"),
			resultat.getInt("numeroRue"),
			resultat.getString("nomRue"),
			resultat.getString("ville"),
			resultat.getInt("cp"),
			resultat.getString("pays"));
			}
			requete_selection.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement requete_delette=this.connect.prepareStatement(
				"DELETE FROM Lesclients where mail=?"
			);
			requete_delette.setString(1,obj.mail);
			int ok=requete_delette.executeUpdate();
			requete_delette.close();
			return ok==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Client[] readAll() {
		// TODO Auto-generated method stub
		ArrayList<Client> c= new ArrayList<Client>();
		try{
		PreparedStatement requete_all=this.connect.prepareStatement(
			"SELECT * FROM lesClients");
			ResultSet resultat=requete_all.executeQuery();
			while(resultat.next()){
				 c.add(
					new Client
					(this.connect,
					resultat.getString("mail"),
					resultat.getString("nom"),
					resultat.getString("prenom"),
					resultat.getString("mdp"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")));
			}
			requete_all.close();
			return (Client[]) c.toArray();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/*******getter and setter********/
		public String getMail() {
			return mail;
		}
	
		public void setMail(String mail) {
			this.mail = mail;
		}
	
		public String getPrenom() {
			return prenom;
		}
	
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
	
		public String getNom() {
			return nom;
		}
	
		public void setNom(String nom) {
			this.nom = nom;
		}
	
		public String getMdp() {
			return mdp;
		}
	
		public void setMdp(String mdp) {
			this.mdp = mdp;
		}
	
		public int getNumeroRue() {
			return numeroRue;
		}
	
		public void setNumeroRue(int numeroRue) {
			this.numeroRue = numeroRue;
		}
	
		public String getNomRue() {
			return nomRue;
		}
	
		public void setNomRue(String nomRue) {
			this.nomRue = nomRue;
		}
	
		public String getVille() {
			return ville;
		}
	
		public void setVille(String ville) {
			this.ville = ville;
		}
	
		public int getCp() {
			return cp;
		}
	
		public void setCp(int cp) {
			this.cp = cp;
		}
	
		public String getPays() {
			return pays;
		}
	
		public void setPays(String pays) {
			this.pays = pays;
		}
		private void setAll(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
		String ville, int cp, String pays){
			this.setMail(mail);
			this.setMdp(mdp);
			this.setNom(nom);
			this.setPrenom(prenom);
			this.setNomRue(nomRue);
			this.setNumeroRue(numeroRue);
			this.setVille(ville);
			this.setCp(cp);
			this.setPays(pays);
		}
	
	/**************************************/

}
