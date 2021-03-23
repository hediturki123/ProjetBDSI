import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Photo extends DAO<Photo> {

	private int idPhoto;
	private String chemin;
	
	public Photo(Connection conn, int idPhoto, String chemin) {
		super(conn);
		this.idPhoto = idPhoto;
		this.chemin = chemin;
	}
	
	public Photo(Connection conn) {
		super(conn);
	}
	@Override
	public boolean create(Photo obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesPhotos VALUES (?,?)");
			requete_imp.setInt(1, obj.getIdPhoto());
			requete_imp.setString(2, obj.getChemin());
			boolean b = requete_imp.execute();
			requete_imp.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Photo read(Object id) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotos WHERE idPhoto=?");
			requete_select.setInt(1, (int)id);
			
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				Photo res = new Photo(this.connect,
						result.getInt("idPhoto"),
						result.getString("chemin"));
				requete_select.close();
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Photo[] readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotos");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<Photo> tab  = new ArrayList<Photo>();
			while(result.next())
			{
				Photo res = new Photo(this.connect,
						result.getInt("idPhoto"),
						result.getString("chemin"));
				requete_select.close();
				tab.add(res);
			}
			return (Photo[])tab.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Photo obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotos SET"
					+ "idPhoto=?,"
					+ "chemin=?");
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setString(2, obj.getChemin());
			boolean b = requete_update.execute();
			requete_update.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Photo obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotos WHERE idPhoto=?"
					+ "AND chemin=?");
			requete_delete.setInt(1, obj.getIdPhoto());
			requete_delete.setString(2, obj.getChemin());
			boolean b = requete_delete.execute();
			requete_delete.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/***** getters and setters *****/
	
	protected int getIdPhoto() {
		return idPhoto;
	}

	protected void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	protected String getChemin() {
		return chemin;
	}

	protected void setChemin(String chemin) {
		this.chemin = chemin;
	}
}
