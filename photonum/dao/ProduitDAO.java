package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Produit;

public class ProduitDAO extends DAO<Produit>{

	public ProduitDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Produit obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"INSERT INTO LesProduits VALUES (?, ?, ?)"
			);
			requete.setString(1, obj.getReference());
			requete.setFloat(2, obj.getPrix());
			requete.setInt(3, obj.getStock());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

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
					resultat.getFloat("prix"),
					resultat.getInt("stock")
				);
			}
			requete.close();
			return p;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

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
					resultat.getString("requete"),
					resultat.getFloat("prix"),
					resultat.getInt("stock")
				));
			}
			requete.close();
			return produits;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Produit obj) {
		try {
			PreparedStatement requete = this.connect.prepareStatement(
				"UPDATE LesProduits SET prix = ?, stock = ? WHERE reference = ?"
			);
			requete.setFloat(1, obj.getPrix());
			requete.setInt(2, obj.getStock());
			requete.setString(3, obj.getReference());
			int reussi = requete.executeUpdate();
			requete.close();
			return reussi == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

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
			e.printStackTrace();
		}
		return false;
	}

}
