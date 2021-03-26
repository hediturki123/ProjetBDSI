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
	
    /**
	 * construit une PhotoDAO avec la connexions à la BD
	 * @param conn
	 */
    public PhotoDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj une {@link Photo} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
    @Override
	public boolean create(Photo obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesPhotos VALUES (?,?,?)");
			obj.setIdPhoto(lastId()+1);
			requete_imp.setInt(1, obj.getIdPhoto());
			requete_imp.setString(2, obj.getChemin());
			requete_imp.setString(3, obj.getMailClient());
			boolean b = requete_imp.execute();
			requete_imp.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * @param id un <b>Int</b> coorespondant à l'<b>idPhoto</b>
	 * @return {@link Photo} correspondante
	 * @exception SQLException;	 
	 * */
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
						result.getString("chemin"),
						result.getString("mailClient"));
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
	/**
	 * @param obj une {@link Photo} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(Photo obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotos SET"
					+ " idPhoto=?,"
					+ "chemin=?,"
					+ "mailClient=?");
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setString(2, obj.getChemin());
			requete_update.setString(3, obj.getMailClient());
			boolean b = requete_update.execute();
			requete_update.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param obj une {@link Photo} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(Photo obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotos WHERE idPhoto=?");
			requete_delete.setInt(1, obj.getIdPhoto());
			boolean b = requete_delete.execute();
			requete_delete.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param mail le mail d'un {@link Client}
	 * @return <b>List&lt;{@link Photo}&gt;</b> la liste de toutes les {@link PhotoNum} de la base en fonction du client
	 * @exception SQLException;
	 */
	public List<Photo> readAllPhotosByClient(String mail){
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotos WHERE mailClient=?");
			requete_select.setString(1, mail);
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<Photo> tab  = new ArrayList<Photo>();
			while(result.next())
			{
				Photo res = new Photo(
						result.getString("chemin"),
						result.getString("mailClient"));
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
	/**
	 * @return <b>List&lt;{@link Photo}&gt;</b> la liste de toutes les {@link Photo} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<Photo> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotos");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<Photo> tab  = new ArrayList<Photo>();
			while(result.next())
			{
				Photo res = new Photo(
						result.getString("chemin"),
						result.getString("mailClient"));
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
	
	/**
	 * cette fonction permet de recuperer le dernier Id pour pouvoir creer une nouvelle commande(AUTO_INCREMENT)
	 * @return un <b>Int</b> correpondant au dernier id dans la table LesPhotos
	 */
	private int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPhoto) FROM LesPhotos");
			ResultSet res = requete_last.executeQuery();
			if(res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}