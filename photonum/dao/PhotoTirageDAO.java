package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Client;
import photonum.objects.PhotoTirage;

public class PhotoTirageDAO extends DAO<PhotoTirage>{
	/**
	 * construit une PhotoTirageDAO avec la connexions à la BD
	 * @param conn
	 */
	public PhotoTirageDAO(Connection conn) {
		super(conn);
	}
	/**
	 * @param obj une {@link PhotoTirage} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(PhotoTirage obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesPhotosTirees VALUES (?,?)"
			);
			PreparedStatement requeteCreate2=this.connect.prepareStatement(
					"INSERT INTO LesPhotos VALUES (?,?,?)");
			
			obj.setIdPhoto(lastId()+1);
			requeteCreate.setInt(1,obj.getIdPhoto());
			requeteCreate.setInt(2,obj.getNbFoisTiree());
			
			requeteCreate2.setInt(1, obj.getIdPhoto());
			requeteCreate2.setString(2, obj.getChemin());
			requeteCreate2.setString(3, obj.getMailClient());
			
			int reussi1=requeteCreate.executeUpdate();
			int reussi2=requeteCreate2.executeUpdate();
			
			requeteCreate.close();
			requeteCreate2.close();
			
			return reussi1==1 && reussi2==1;
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
	public PhotoTirage read(Object obj) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosTirees NATURAL JOIN LesPhotos WHERE idPhoto=?");
			requete_select.setInt(1, (int)obj);
			
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				PhotoTirage res = new PhotoTirage(
						result.getString("chemin"),
						result.getString("mailClient"),
						result.getInt("nbPhotoTirees"));
				requete_select.close();
				return res;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return <b>List&lt;{@link PhotoTirage}&gt;</b> la liste de toutes les {@link PhotoTirage} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<PhotoTirage> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosTirees NATURAL JOIN LesPhotos");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoTirage> tab  = new ArrayList<PhotoTirage>();
			while(result.next())
			{
				PhotoTirage res = new PhotoTirage(
						result.getString("chemin"),
						result.getString("mailClient"),
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
	/**
	 * @param obj une {@link PhotoTirage} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(PhotoTirage obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotosTirees SET "
					+ "idPhoto=?,"
					+ "nbPhotoTirees=?");
			PreparedStatement requete_update2 = this.connect.prepareStatement(
					"UPDATE LesPhotos SET "
					+ "idPhoto=?,"
					+ "chemin=?,"
					+ "mailClient=?");
			
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setInt(2, obj.getNbFoisTiree());
			
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
	 * @param obj une {@link PhotoTirage} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(PhotoTirage obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotosTirees WHERE idPhoto=?");
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

	public List<PhotoTirage> readAllPhotosTirageByClient(Client client){
		try {
			PreparedStatement requete_select = PhotoNum.conn.prepareStatement("SELECT * FROM LesPhotosTirees NATURAL JOIN LesPhotos WHERE mailClient=?");
			requete_select.setString(1, client.getMail());
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoTirage> tab  = new ArrayList<PhotoTirage>();
			while(result.next())
			{
				PhotoTirage res = new PhotoTirage(
						result.getString("chemin"),
						result.getString("mailClient"),
						result.getInt("nbFois"));
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
