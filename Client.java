import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
	public Client(Connection conn, String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
			String ville, int cp, String pays) {
		super(conn);
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.ville = ville;
		this.cp = cp;
		this.pays = pays;
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
			PreparedStatement requete_selection=this.connect.prepareStatement(
					"SELECT * from LesClients where mail=?");
			requete_selection.setString(1,(String)obj);
			ResultSet result=requete_selection.executeQuery();
			result.first();
			return null;
			
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
		return false;
	}

	@Override
	public Client[] readAll() {
		// TODO Auto-generated method stub
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
	
	/**************************************/

}
