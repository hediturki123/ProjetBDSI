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
		boolean reussi = false;
		try {
			PreparedStatement pstmt = this.connect.prepareStatement(
				"INSERT INTO LesCommandes VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
			);
			cmd.setIdCommande(getLastId()+1);
			pstmt.setInt(1, cmd.getIdCommande());
			pstmt.setString(2, cmd.getMail());
			pstmt.setDate(3, cmd.getDateCommande());
			pstmt.setBoolean(4, cmd.getEstLivreChezClient());
			pstmt.setString(5, cmd.getStatus().getString());
			pstmt.setString(6, cmd.getCodePromo());
			pstmt.setInt(7, cmd.getNumeroRue());
			pstmt.setString(8, cmd.getNomRue());
			pstmt.setString(9, cmd.getVille());
			pstmt.setInt(10, cmd.getCodePostal());
			pstmt.setString(11, cmd.getPays());
			reussi = pstmt.executeUpdate() != 0;
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reussi;
	}

	@Override
	public Commande read(Object id) {
		Commande c = null;
		try {
			int identifiant = (int) id;
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
					resultat.getString("codePromo"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")
				);
			}
			requete.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
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
					resultat.getString("codePromo"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")
				));
			}

			requete.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}

	@Override
	public boolean update(Commande obj) {
		boolean reussi = false;
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"UPDATE LesCommandes SET status = ? WHERE idCommande = ?"
			);
			requete.setString(1, obj.getStatus().getString());
			requete.setInt(2, obj.getIdCommande());
			reussi = requete.executeUpdate() != 0;
			requete.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reussi;
	}

	@Override
	public boolean delete(Commande obj) {
		boolean reussi = false;
		 try {
			PreparedStatement requete = this.connect.prepareStatement(
				"DELETE FROM LesCommandes WHERE idCommande = ?"
			);
			requete.setInt(1, obj.getIdCommande());
			reussi = requete.executeUpdate() != 0;
			requete.close();
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		return reussi;
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
					resultat.getString("codePromo"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")
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
				Commande co = new Commande(resultat.getInt("idCommande")
					, resultat.getString("mail"),
					resultat.getDate("dateCommande"),
					resultat.getBoolean("estLivreChezClient"),
					StatutCommande.fromString(resultat.getString("status")),
					resultat.getString("codePromo"),
					resultat.getInt("numeroRue"),
					resultat.getString("nomRue"),
					resultat.getString("ville"),
					resultat.getInt("cp"),
					resultat.getString("pays")
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
