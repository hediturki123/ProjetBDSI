package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Client;
import photonum.objects.Commande;
import photonum.objects.StatutCommande;

public class CommandeDAO extends DAO<Commande>{
	public CommandeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Commande obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"INSERT INTO LesCommandes VALUES (?, ?, ?, ?, ?)"
			);
			requete.setString(1, obj.getMail());
			requete.setDate(2, obj.getDateCommande());
			requete.setBoolean(3, obj.getEstLivreChezClient());
			
			switch(obj.getStatus()) {
				case EN_COURS :
					requete.setString(4, "enCours");
				case PRETE_ENVOI :
					requete.setString(4, "preteEnvoi");
				case ENVOYEE :
					requete.setString(4, "envoyee");
			}

			requete.setString(5, obj.getCodePromo());

			boolean reussi = requete.execute();
			requete.close();
			return reussi;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Commande read(Object id) {
		try {
			int identifiant = (int) id;
			Commande c=null;
			StatutCommande statut;
			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesCommandes WHERE idCommande = ?"
			);
			requete.setInt(1, identifiant);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				switch(resultat.getString("status")){
					case "enCours":statut=StatutCommande.EN_COURS;
						break;
					case "preteEnvoi":statut=StatutCommande.PRETE_ENVOI;
						break;
					case "envoyee":statut=StatutCommande.ENVOYEE;
					default :
					statut=StatutCommande.EN_COURS;
					
				}
				c = new Commande(
					resultat.getString("mail"),
					resultat.getDate("date"),
					resultat.getBoolean("estLivreChezClient"),
					statut,
					resultat.getString("codePromo")
				);
			}
			requete.close();
			return c;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Commande> readAll() {
		
		List<Commande> commandes = new ArrayList<Commande>();
		
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesCommandes"
			);
			ResultSet resultat = requete.executeQuery();
			
			while (resultat.next()) {
				
				StatutCommande statut;
				switch(resultat.getString("status")) {
					case "enCours":
						statut = StatutCommande.EN_COURS;
						break;
					case "preteEnvoi" :
						statut = StatutCommande.PRETE_ENVOI;
						break;
					case "envoyee" :
						statut = StatutCommande.ENVOYEE;
						break;
					default :
						statut = null;
						break;
				}

				commandes.add(new Commande(
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					statut,
					resultat.getString("codePromo")
				));
			}

			requete.close();
			return commandes;

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public boolean update(Commande obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"UPDATE LesCommandes SET status = ? WHERE idCommande = ?"
			);
			requete.setInt(1, obj.getIdCommande());

			switch(obj.getStatus()) {
				case EN_COURS :
					requete.setString(2, "enCours");
				case PRETE_ENVOI :
					requete.setString(2, "preteEnvoi");
				case ENVOYEE :
					requete.setString(2, "envoyee");
			}

			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Commande obj) {
		 try {
			PreparedStatement requete = this.connect.prepareStatement(
				"DELETE FROM LesCommandes WHERE idCommande = ?"
			);
			requete.setInt(1, obj.getIdCommande());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		 } catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Commande> readAllByStatus(StatutCommande sc) {

		List<Commande> commande = new ArrayList<Commande>();

		try {

			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesCommandes WHERE status = ?"
			);
			requete.setString(1, sc.toString());
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) {
				StatutCommande statut;
				switch(resultat.getString("status")) {
					case "enCours":
						statut = StatutCommande.EN_COURS;
						break;
					case "preteEnvoi" :
						statut = StatutCommande.PRETE_ENVOI;
						break;
					case "envoyee" :
						statut = StatutCommande.ENVOYEE;
						break;
					default :
						statut = null;
						break;
				}

				Commande c = new Commande(
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					statut,
					resultat.getString("codePromo")
				);
				commande.add(c);
			}

			return commande;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Commande> readAllByClient(Client c) {

		List<Commande> commande = new ArrayList<Commande>();

		try {

			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesCommandes WHERE mail=?"
			);
			requete.setString(1, c.getMail());
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) {
				StatutCommande statut;
				switch(resultat.getString("status")) {
					case "enCours":
						statut = StatutCommande.EN_COURS;
						break;
					case "preteEnvoi" :
						statut = StatutCommande.PRETE_ENVOI;
						break;
					case "envoyee" :
						statut = StatutCommande.ENVOYEE;
						break;
					default :
						statut = null;
						break;
				}

				Commande co = new Commande(
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					statut,
					resultat.getString("codePromo")
				);
				commande.add(co);
			}

			return commande;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
