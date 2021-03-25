package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Commande;
import photonum.objects.StatutCommande;

public class CommandeDAO extends DAO<Commande>{
	public CommandeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Commande cmd) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"INSERT INTO LesCommandes VALUES (?, ?, ?, ?, ?, ?)"
			);
			cmd.setIdCommande(getLastId()+1);
			requete.setInt(1, cmd.getIdCommande());
			requete.setString(2, cmd.getMail());
			requete.setDate(3, cmd.getDateCommande());
			requete.setBoolean(4, cmd.getEstLivreChezClient());

			switch(cmd.getStatus()) {
				case EN_COURS: requete.setString(5, "enCours"); break;
				case PRETE_ENVOI:
					requete.setString(5, "preteEnvoi"); break;
				case ENVOYEE: requete.setString(5, "envoyee"); break;
			}

			requete.setString(6, cmd.getCodePromo());

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
					resultat.getInt("idCommande"),
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
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
					resultat.getInt("idCommande"),
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
			requete.setInt(2, obj.getIdCommande());

			switch(obj.getStatus()) {
				case EN_COURS :
					requete.setString(1, "enCours");
				case PRETE_ENVOI :
					requete.setString(1, "preteEnvoi");
				case ENVOYEE :
					requete.setString(1, "envoyee");
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

				commande.add(new Commande (
					resultat.getInt("idCommande"),
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					statut,
					resultat.getString("codePromo")
				));
			}
			requete.close();
			return commande;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getLastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idCommande) FROM LesCommandes");
			ResultSet res = requete_last.executeQuery();
			if(res.next()) {
				return res.getInt("idCommande");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
