package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Photo;
import photonum.objects.PhotoTirage;

public class PhotoTirageDAO extends DAO<PhotoTirage>{
	
	public PhotoTirageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(PhotoTirage obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesPhotosTirees VALUES (?,?)"
			);
			obj.setIdPhoto(lastId()+1);
			requeteCreate.setInt(1,obj.getIdPhoto());
			requeteCreate.setInt(2,obj.getNbFoisTiree());
			int reussi=requeteCreate.executeUpdate();
			requeteCreate.close();
			return reussi==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PhotoTirage read(Object obj) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosTirees WHERE idPhoto=?");
			requete_select.setInt(1, (int)obj);
			
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				PhotoTirage res = new PhotoTirage(
						result.getString("chemin"),
						result.getInt("nbPhotoTirees"));
				requete_select.close();
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PhotoTirage> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosTirees");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoTirage> tab  = new ArrayList<PhotoTirage>();
			while(result.next())
			{
				PhotoTirage res = new PhotoTirage(
						result.getString("chemin"),
						result.getInt("nbPhotoTirees"));
				requete_select.close();
				tab.add(res);
			}
			return tab;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(PhotoTirage obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotosTirees SET"
					+ "idPhoto=?,"
					+ "chemin=?,"
					+ "nbPhotoTirees=?");
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setString(2, obj.getChemin());
			requete_update.setInt(3, obj.getNbFoisTiree());
			boolean b = requete_update.execute();
			requete_update.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(PhotoTirage obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotosTirees WHERE idPhoto=?");
			requete_delete.setInt(1, obj.getIdPhoto());
			boolean b = requete_delete.execute();
			requete_delete.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPhoto) FROM LesPhotosTirees");
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
