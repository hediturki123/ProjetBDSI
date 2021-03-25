package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Client;
import photonum.objects.PhotoAlbum;

public class PhotoAlbumDAO extends DAO<PhotoAlbum>{
    
    public PhotoAlbumDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(PhotoAlbum obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesPhotosAlbum VALUES (?,?,?)");
			obj.setIdPhoto(lastId()+1);
			requete_imp.setInt(1, obj.getIdPhoto());
			requete_imp.setString(2, obj.getChemin());
			requete_imp.setString(3, obj.getTexteDescriptif());
			boolean b = requete_imp.execute();
			requete_imp.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PhotoAlbum read(Object obj) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosAlbum WHERE idPhoto=?");
			requete_select.setInt(1, (int)obj);
			PhotoAlbum res;
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				res = new PhotoAlbum(
						result.getString("chemin"),
						result.getString("texteDescriptif"));
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
	public boolean update(PhotoAlbum obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotosAlbum SET"
					+ "idPhoto=?,"
					+ "chemin=?,"
					+ "texteDescriptif=?");
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setString(2, obj.getChemin());
			requete_update.setString(2, obj.getTexteDescriptif());
			boolean b = requete_update.execute();
			requete_update.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(PhotoAlbum obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotosAlbum WHERE idPhoto=?");
			requete_delete.setInt(1, obj.getIdPhoto());
			boolean b = requete_delete.execute();
			requete_delete.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<PhotoAlbum> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosAlbum");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoAlbum> tab  = new ArrayList<PhotoAlbum>();
			PhotoAlbum res;
			while(result.next())
			{
				res = new PhotoAlbum(
						result.getString("chemin"),
						result.getString("texteDescriptif"));
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

	public static List<PhotoAlbum> readAllPhotosAlbumByClient(Client client){
		return null;
	}

	private int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPhoto) FROM LesPhotosAlbum");
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
