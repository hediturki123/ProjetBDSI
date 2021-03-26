package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.PhotoAlbum;

public class PhotoAlbumDAO extends DAO<PhotoAlbum>{
	/**
	 * construit une PhotoAlbumDAO avec la connexions à la BD
	 * @param conn
	 */
    public PhotoAlbumDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj une {@link PhotoAlbum} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(PhotoAlbum obj) {
		try {
			PreparedStatement requete_imp = this.connect.prepareStatement(
					"INSERT INTO LesPhotosAlbum VALUES (?,?)");
			PreparedStatement requete_imp2 = this.connect.prepareStatement(
					"INSERT INTO LesPhotos VALUES (?,?,?)");
			
			obj.setIdPhoto(lastId()+1);
			requete_imp.setInt(1, obj.getIdPhoto());
			requete_imp.setString(2, obj.getTexteDescriptif());
			
			requete_imp2.setInt(1, obj.getIdPhoto());
			requete_imp2.setString(2, obj.getChemin());
			requete_imp2.setString(3, obj.getMailClient());
			
			boolean b1 = requete_imp.execute();
			boolean b2 = requete_imp2.execute();
			
			requete_imp.close();
			requete_imp2.close();
			return b1 && b2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param id un <b>Int</b> correspondant à l'<b>idPhoto</b>
	 * @return {@link PhotoAlbum} correspondante
	 * @exception SQLException;	 
	 * */

	@Override
	public PhotoAlbum read(Object obj) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosAlbum NATURAL JOIN LesPhotos WHERE idPhoto=?");
			requete_select.setInt(1, (int)obj);
			PhotoAlbum res;
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				res = new PhotoAlbum(
						result.getString("chemin"),
						result.getString("mailClient"),
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

	/**
	 * @param obj une {@link PhotoAlbum} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(PhotoAlbum obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotosAlbum SET"
					+ "idPhoto=?,"
					+ "texteDescriptif=?");
			PreparedStatement requete_update2 = this.connect.prepareStatement(
					"UPDATE LesPhotos SET"
					+ "idPhoto=?,"
					+ "chemin=?,"
					+ "mailClient=?");
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setString(2, obj.getTexteDescriptif());
			
			requete_update2.setInt(1, obj.getIdPhoto());
			requete_update2.setString(2, obj.getChemin());
			requete_update2.setString(3, obj.getMailClient());
			
			boolean b1 = requete_update.execute();
			boolean b2 = requete_update2.execute();
			
			requete_update.close();
			requete_update2.close();
			return b1 && b2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param obj une {@link PhotoAlbum} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(PhotoAlbum obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotosAlbum WHERE idPhoto=?");
			PreparedStatement requete_delete2 = this.connect.prepareStatement(
					"DELETE FROM LesPhotos WHERE idPhoto=?");
			requete_delete.setInt(1, obj.getIdPhoto());
			requete_delete2.setInt(1, obj.getIdPhoto());
			
			boolean b1 = requete_delete.execute();
			boolean b2 = requete_delete2.execute();
			
			requete_delete.close();
			requete_delete2.close();
			
			return b1 && b2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @return <b>List&lt;{@link PhotoAlbum}&gt;</b> la liste de toutes les {@link PhotoAlbum} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<PhotoAlbum> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosAlbum NATURAL JOIN LesPhotos");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoAlbum> tab  = new ArrayList<PhotoAlbum>();
			PhotoAlbum res;
			while(result.next())
			{
				res = new PhotoAlbum(
						result.getString("chemin"),
						result.getString("mailClient"),
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
	/**
	 * @param mail le mail d'un {@link Client}
	 * @return <b>List&lt;{@link PhotoAlbum}&gt; </b> la liste de toutes les {@link PhotoAlbum} de la base en fonction du client
	 * @exception SQLException;
	 */
	public static List<PhotoAlbum> readAllPhotosAlbumByClient(String mail){
		try {
			PreparedStatement requete_select = PhotoNum.conn.prepareStatement("SELECT * FROM LesPhotosAlbum NATURAL JOIN LesPhotos WHERE mailClient=?");
			requete_select.setString(1, mail);
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoAlbum> tab  = new ArrayList<PhotoAlbum>();
			while(result.next())
			{
				PhotoAlbum res = new PhotoAlbum(
						result.getString("chemin"),
						result.getString("mailClient"),
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
	/**
	 * cette fonction permet de recuperer le dernier Id pour pouvoir creer une nouvelle commande(AUTO_INCREMENT)
	 * @return un <b>Int</b> correpondant au dernier id dans la table LesPages
	 */
	private int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPhoto) FROM LesPhotos");
			ResultSet res = requete_last.executeQuery();
			if(res.next()) {
				requete_last.close();
				return res.getInt("idPhoto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
