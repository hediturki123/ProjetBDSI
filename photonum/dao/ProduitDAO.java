package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Produit;

public class ProduitDAO extends DAO<Produit>{

	/**
	 * construit un ProduitDAO avec la connexions à la BD
	 * @param conn
	 */
	public ProduitDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj un {@link Produit} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(Produit obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"INSERT INTO LesProduits VALUES (?, ?, ?)"
			);
			requete.setString(1, obj.getReference());
			requete.setDouble(2, obj.getPrix());
			requete.setInt(3, obj.getStock());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				if (e.getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
					System.err.println("Le produit existe déjà!");
				} else {
					System.err.println("Quelque chose s'est mal passé avec la création du produit.");
				}
			}
		}
		return false;
	}

	/**
	 * @param id un <b>String</b> correspondant à la reference du {@link Produit}
	 * @return Le {@link Produit} correspondant à la ref
	 * @exception SQLException;
	 * */
	@Override
	public Produit read(Object ref) {
		try {
			String reference = (String) ref;
			Produit p = null;
			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesProduits WHERE reference = ?"
			);
			requete.setString(1, reference);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				p = new Produit(
					resultat.getString("reference"),
					resultat.getDouble("prix"),
					resultat.getInt("stock")
				);
			}
			requete.close();
			return p;

		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la lecture du produit.");
			}
		}
		return null;
	}

	/**
	 * @return <b>List&lt;{@link Produit}&gt;</b> la liste de tous les {@link Produit} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<Produit> readAll() {

		List<Produit> produits = new ArrayList<Produit>();

		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"SELECT * FROM LesProduits"
			);
			ResultSet resultat = requete.executeQuery();

			while (resultat.next()) {
				produits.add(new Produit(
					resultat.getString("reference"),
					resultat.getFloat("prix"),
					resultat.getInt("stock")
				));
			}
			requete.close();
			return produits;

		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la lecture de tous les produits.");
			}
		}
		return null;
	}

	@Override
	public boolean update(Produit obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"UPDATE LesProduits SET prix = ?, stock = ? WHERE reference = ?"
			);
			requete.setDouble(1, obj.getPrix());
			requete.setInt(2, obj.getStock());
			requete.setString(3, obj.getReference());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		} catch (Exception e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la modification du produit.");
			}
		}
		return false;
	}
	/**
	 * @param obj un {@link Produit} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(Produit obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"DELETE FROM LesProduits WHERE reference = ?"
			);
			requete.setString(1, obj.getReference());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		 } catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la suppression du produit.");
			}
		}
		return false;
	}

}
