import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Impression extends DAO<Impression>{

	private int idImpression;
	private String reference;
	private String type;
	private String titre;
	
	public Impression(Connection conn, int idImpression, String reference, String type, String titre) {
		super(conn);
		this.idImpression = idImpression;
		this.reference = reference;
		this.type = type;
		this.titre = titre;
	}

	@Override
	public boolean create(Impression obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesImpressions VALUES (?,?,?,?)");
			requete_imp.setInt(1, idImpression);
			requete_imp.setString(2, reference);
			requete_imp.setString(3, type);
			requete_imp.setString(4, titre);
			
			return requete_imp.execute();
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
			result.first();
			//Impression res = new Impression(this.connect,result.);
			return null;
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
			
			return requete_update.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Impression obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM `LesImpressions` WHERE idImpressions=?"
					+ "AND reference=?"
					+ "AND type=?"
					+ "AND titre?");
			requete_delete.setInt(1, obj.getIdImpression());
			requete_delete.setString(2, obj.getReference());
			requete_delete.setString(3, obj.getType());
			requete_delete.setString(4, obj.getTitre());
			
			return requete_delete.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Impression[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*** getters and setters ***/
	protected int getIdImpression() {
		return idImpression;
	}

	protected void setIdImpression(int idImpression) {
		this.idImpression = idImpression;
	}

	protected String getReference() {
		return reference;
	}

	protected void setReference(String reference) {
		this.reference = reference;
	}

	protected String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	protected String getTitre() {
		return titre;
	}

	protected void setTitre(String titre) {
		this.titre = titre;
	}
}
