package photonum.dao;

import java.sql.CallableStatement;
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
import photonum.utils.AB64H;

public class CommandeDAO extends DAO<Commande>{

	/**
	 * construit un CommandeDAO avec la connexions à la BD
	 * @param conn
	 */
	public CommandeDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj une {@link Commande} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
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
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				if (e.getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
					System.err.println("La commande existe déjà !");
				} else {
					System.err.println("Quelque chose s'est mal passé avec la commande...");
				}
			}
		}
		return reussi;
	}

	/**
	 *
	 * @param id un <b>Int</b> correspondant a <b>IdCommande</b>
	 * @return La {@link Commande} correpondant à <b>IdCommande</b>
	 * @exception SQLException;
	 * */
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
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			}else{
					System.err.println("Quelque chose s'est mal passé avec la lecture des commandes...");
			}
		}

		return c;
	}

	/**
	 * @return <b>List&lt;{@link Commande}&gt;</b> la liste de tous les Commandes de la base
	 * @exception SQLException;
	 */
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
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			}else{
				System.err.println("Quelque chose s'est mal passé avec la lecture des commandes  ...");
			}
		}
		return commandes;
	}

	/**
	 * @param obj une {@link Commande} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(Commande obj) {
		boolean reussi = false;
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"UPDATE LesCommandes SET status = ? ,codePromo=? WHERE idCommande = ?"
			);
			requete.setString(1, obj.getStatus().getString());
			requete.setString(2, obj.getCodePromo());
			requete.setInt(3, obj.getIdCommande());
			reussi = requete.executeUpdate() != 0;
			requete.close();
		} catch (Exception e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec commande...");
			}
		}
		return reussi;
	}

	/**
	 * @param obj une {@link Commande} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
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
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
					System.err.println("Quelque chose s'est mal passé à la suppression de la commande ...");
			}
	
		}
		return reussi;
	}

	/**
	 * @param sc Le {@link StatutCommande}(<b>EN_COURS,PRETE_ENVOI,ENVOYEE</b>)
	 * @return <b>List&lt;{@link Commande}&gt; </b> la liste de toutes les  commande de la base en fonction de leut statut
	 * @exception SQLException;
	 */
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
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec la lecture des commandes  ...");
			}
		}

		return null;
	}

	/**
	 * @param c un {@link Client} pour trouver ces articles correspondant
	 * @return <b>List&lt;{@link Commande}&gt; </b> la liste de toutes les commandes de la base en fonction du client
	 * @exception SQLException;
	 */
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
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec la lecture des commandes  ...");
			}
		}
		return null;
	}

	/**
	 * cette fonction permet de recuperer le dernier id pour pouvoir creer une nouvelle commande(AUTO_INCREMENT)
	 * @return un <b>Int</b> correpondant au dernier id dans la table LesCommandes
	 */
	public static int getLastId() {
		int id = 0;
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idCommande) FROM LesCommandes");
			ResultSet res = requete_last.executeQuery();
			if (res.next()) {
				id = res.getInt(1);
			}
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec la lecture du dernier ID ...");
			}
		}
		return id;
	}

	/**
	 * Récupère le prix calculé d'une commande.
	 * @param c Commande dont on veut le prix.
	 * @return Prix total de la commande.
	 */
	public double getPrix(Commande c) {
		double prix = 0.0;
		try {
			PreparedStatement pstmt = PhotoNum.conn.prepareStatement("SELECT prixTotal FROM LesCommandesPrix WHERE idCommande=?");
			pstmt.setInt(1, c.getIdCommande());
			ResultSet res = pstmt.executeQuery();
			if (res.next()) prix = res.getDouble("prixTotal");
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec la lecture du prix ...");
			}
		}
		return prix;
	}

	/**
	 * Récupère le prix calculé d'une commande.
	 * @param id Identifiant de la commande dont on veut le prix.
	 * @return Prix total de la commande.
	 */
	public double getPrix(int id) {
		double prix = 0.0;
		try {
			PreparedStatement pstmt = PhotoNum.conn.prepareStatement("SELECT prixTotal FROM LesCommandesPrix WHERE idCommande=?");
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) prix = res.getDouble("prixTotal");
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec la lecture du prix ...");
			}
		}
		return prix;
	}

	public static boolean ajouterPromo(String mailClient) {
		boolean success = false;
		try {
			CallableStatement cstmt = PhotoNum.conn.prepareCall("{call code_promo_proc (?,?)}");
			cstmt.setString(1, AB64H.encode(System.currentTimeMillis()));
			cstmt.setString(2, mailClient);
			cstmt.execute();
			success = true;
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé avec l'ajout d'un code promo ...");
			}
		}
		return success;
	}

	public static boolean ajouterPromo(Client c) {
		return ajouterPromo(c.getMail());
	}
}
