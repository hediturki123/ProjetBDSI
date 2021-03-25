package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.objects.Client;
import photonum.objects.Impression;
import photonum.objects.TypeImpression;

public class ImpressionDAO extends DAO<Impression> {

	public ImpressionDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Impression obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesImpressions VALUES (?, ?, ? , ?, ?)");
			obj.setIdImpression(lastId() + 1);
			requete_imp.setInt(1, obj.getIdImpression());
			requete_imp.setString(2, obj.getMailClient());
			requete_imp.setString(3, obj.getReference());
			requete_imp.setString(4, obj.getType().getString());
			requete_imp.setString(5, obj.getTitre());
			int b = requete_imp.executeUpdate();
			requete_imp.close();
			return b == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Impression read(Object id) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement(
				"SELECT * FROM LesImpressions WHERE idImpression = ?"
			);
			requete_select.setInt(1, (int)id);
			Impression res = null;
			
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				 res = new Impression(
					 	result.getInt("idImpression"),
						result.getString("mailClient"),
						result.getString("reference"),
						TypeImpression.fromString(result.getString("type")),
						result.getString("titre")
						);
				 res.setIdImpression(result.getInt("idImpressions"));
			}
			requete_select.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Impression obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesImpressions SET"
					+ "idImpression = ?,"
					+ "mailClient = ?,"
					+ "reference = ?,"
					+ "type = ?,"
					+ "titre = ?"
				);
			requete_update.setInt(1, obj.getIdImpression());
			requete_update.setString(2, obj.getMailClient());
			requete_update.setString(3, obj.getReference());
			requete_update.setString(4, obj.getType().getString());
			requete_update.setString(5, obj.getTitre());
			int resultat = requete_update.executeUpdate();
			requete_update.close();
			return resultat == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Impression obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesImpressions WHERE idImpression = ?");
			requete_delete.setInt(1, obj.getIdImpression());
			int b = requete_delete.executeUpdate();
			requete_delete.close();
			return b == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Impression> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement(
				"SELECT * FROM LesImpressions"
			);
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<Impression> impressions  = new ArrayList<Impression>();
			while(result.next())
			{
				impressions.add(new Impression(
						result.getInt("idImpression"),
						result.getString("mailClient"),
						result.getString("reference"),
						TypeImpression.fromString(result.getString("type")),
						result.getString("titre")
						)
					);
			}
			requete_select.close();
			return impressions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Impression> readAllByClient(Client client){
		try {
			ArrayList<Impression> impressions  = new ArrayList<Impression>();
			PreparedStatement requete_all = this.connect.prepareStatement(
				"SELECT * FROM LesImpressions WHERE mailClient = ?"
			);

			requete_all.setString(1, client.getMail());
			ResultSet result = requete_all.executeQuery();
			
			
			while(result.next())
			{
				impressions.add(new Impression(
						result.getInt("idImpression"),
						result.getString("mailClient"),
						result.getString("reference"),
						TypeImpression.fromString(result.getString("type")),
						result.getString("titre")
						)
					);
			}
			requete_all.close();
			return impressions;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private int lastId() {
		try {
			PreparedStatement requete_last = this.connect.prepareStatement(
				"SELECT max(idImpression) FROM LesImpressions"
				);
			ResultSet res = requete_last.executeQuery();
			if(res.next()) {
				return res.getInt("idImpression");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
