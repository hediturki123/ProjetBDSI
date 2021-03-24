package photonum;
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

	@Override
	public boolean create(Client obj) {

		try {
			PreparedStatement requeteAjout=this.connect.prepareStatement(
					"INSERT INTO LesClients VALUES (?,?,?,?,?,?,?,?,?)");
					requeteAjout.setString(1, obj.getMail());
					requeteAjout.setString(2, obj.getNom());
					requeteAjout.setString(3, obj.getPrenom());
					requeteAjout.setString(4, obj.getMdp());
					requeteAjout.setInt(5, obj.getNumeroRue());
					requeteAjout.setString(6, obj.getNomRue());
					requeteAjout.setString(7, obj.getVille());
					requeteAjout.setInt(8,obj.getCp());
					requeteAjout.setString(9, obj.getPays());
			
			boolean reussi=requeteAjout.execute();
			setAll(obj.mail, obj.nom, obj.prenom, obj.mdp, obj.numeroRue, obj.nomRue, obj.ville, obj.cp, obj.pays);
			requeteAjout.close();
			return reussi;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Client read(Object obj) {
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
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Client obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
			"UPDATE LesClients set"+
			"mdp=?,"+
			"nom=?,"+
			"prenom=?,"+
			"numeroRue=?,"+
			"nomRue=?,"+
			"ville=?,"+
			"cp=?,"+
			""+
			"where mail=?");
			requeteUpdate.setString(1, obj.getMail());
			requeteUpdate.setString(2, obj.getNom());
			requeteUpdate.setString(3, obj.getPrenom());
			requeteUpdate.setString(4, obj.getMdp());
			requeteUpdate.setInt(5, obj.getNumeroRue());
			requeteUpdate.setString(6, obj.getNomRue());
			requeteUpdate.setString(7, obj.getVille());
			requeteUpdate.setInt(8,obj.getCp());
			requeteUpdate.setString(9, obj.getPays());
			int reussi= requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi ==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		try {
			PreparedStatement requete_delette=this.connect.prepareStatement(
				"DELETE FROM LesClients where mail=?"
			);
			requete_delette.setString(1,obj.getMail());
			int reussi=requete_delette.executeUpdate();
			requete_delette.close();
			return reussi==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Client[] readAll() {
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
		public void setAll(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
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
