package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import photonum.objects.Impression;

public class ImpressionDAO extends DAO<Impression> {

	public ImpressionDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Impression obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesImpressions VALUES (?,?,?,?)");
			requete_imp.setInt(1, obj.getIdImpression());
			requete_imp.setString(2, obj.getReference());
			requete_imp.setString(3, obj.getType());
			requete_imp.setString(4, obj.getTitre());
			boolean b = requete_imp.execute();
			requete_imp.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Impression read(Object id) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesImpressions WHERE idImpression=?");
			requete_select.setInt(1, (int)id);
			
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				Impression res = new Impression(
						result.getString("reference"),
						result.getString("type"),
						result.getString("titre"));
				requete_select.close();
				return res;
			}
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
					+ "idImpression=?,"
					+ "reference=?,"
					+ "type=?,"
					+ "titre=?");
			requete_update.setInt(1, obj.getIdImpression());
			requete_update.setString(2, obj.getReference());
			requete_update.setString(3, obj.getType());
			requete_update.setString(4, obj.getTitre());
			boolean b = requete_update.execute();
			requete_update.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Impression obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesImpressions WHERE idImpression=?"
					+ "AND reference=?"
					+ "AND type=?"
					+ "AND titre=?");
			requete_delete.setInt(1, obj.getIdImpression());
			requete_delete.setString(2, obj.getReference());
			requete_delete.setString(3, obj.getType());
			requete_delete.setString(4, obj.getTitre());
			boolean b = requete_delete.execute();
			requete_delete.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Impression[] readAll(Object obj) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesImpressions");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<Impression> tab  = new ArrayList<Impression>();
			while(result.next())
			{
				Impression res = new Impression(
						result.getString("reference"),
						result.getString("type"),
						result.getString("titre"));
				requete_select.close();
				tab.add(res);
			}
			return (Impression[])tab.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
