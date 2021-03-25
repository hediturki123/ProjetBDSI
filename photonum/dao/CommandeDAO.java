package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Client;
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
			requete.setString(5, cmd.getStatus().getString());
			requete.setString(6, cmd.getCodePromo());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Commande read(Object id) {
		try {
			int identifiant = (int) id;
			Commande c = null;
			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesCommandes WHERE idCommande = ?"
			);
			requete.setInt(1, identifiant);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				c = new Commande(
					resultat.getInt("idCommande"),
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					StatutCommande.fromString(resultat.getString("status")),
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
				commandes.add(new Commande(
					resultat.getInt("idCommande"),
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					StatutCommande.fromString(resultat.getString("status")),
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
			requete.setString(1, obj.getStatus().getString());
			requete.setInt(2, obj.getIdCommande());
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
			requete.setString(1, sc.getString());
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) {
				commande.add(new Commande (
					resultat.getInt("idCommande"),
					resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					StatutCommande.fromString(resultat.getString("status")),
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

	public List<Commande> readAllByClient(Client c) {

		List<Commande> commande = new ArrayList<Commande>();

		try {

			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesCommandes WHERE mail=?"
			);
			requete.setString(1, c.getMail());
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) {
				Commande co = new Commande(
					0, resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					StatutCommande.fromString(resultat.getString("status")),
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
