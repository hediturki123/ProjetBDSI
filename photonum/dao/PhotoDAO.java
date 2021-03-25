package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.*;

public class PhotoDAO extends DAO<Photo>{
	
    
    public PhotoDAO(Connection conn) {
		super(conn);
	}

    @Override
	public boolean create(Photo obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesPhotos VALUES (?,?)");
			obj.setIdPhoto(lastId()+1);
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
			Photo res;
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				res = new Photo(
						result.getString("chemin"));
				res.setIdPhoto(result.getInt("idPhoto"));
			}
			else {
				res = null;
			}
			requete_select.close();
			return res;
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

	public List<Photo> readAllPhotosByClient(String mail){
		return null;
	}

	@Override
	public List<Photo> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotos");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<Photo> tab  = new ArrayList<Photo>();
			while(result.next())
			{
				Photo res = new Photo(
						result.getString("chemin"));
				res.setIdPhoto(result.getInt("idPhoto"));
				tab.add(res);
			}
			requete_select.close();
			return tab;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPhoto) FROM LesPhotos");
			ResultSet res = requete_last.executeQuery();
			if(res.next()) {
				return res.getInt("idPhoto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}