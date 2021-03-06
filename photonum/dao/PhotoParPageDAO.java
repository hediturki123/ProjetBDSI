package photonum.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.PhotoParPage;

public class PhotoParPageDAO extends DAO<PhotoParPage> {
	
	/**
	 * construit une PhotoParPageDAO avec la connexions à la BD
	 * @param conn
	 */
    public PhotoParPageDAO(Connection conn) {
		super(conn);
	}

    /**
	 * @param obj une {@link PhotoParPage} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(PhotoParPage obj){
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesPhotosParPages VALUES (?,?)"
			);
			requeteCreate.setInt(1,obj.getIdPhoto());
			requeteCreate.setInt(2,obj.getIdPage());
			int reussi=requeteCreate.executeUpdate();
			requeteCreate.close();
			return reussi==1;
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				if (e.getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
					System.err.println("La photo par page page existe déjà!");
				} else {
					System.err.println("Quelque chose s'est mal passé avec la création de la photo par page.");
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param id un <b>Int[2]</b> contenant <b>idPhoto</b> et <b>idPages</b>
	 * @return {@link PhotoAlbum} correspondante
	 * @exception SQLException;	 
	 * */
	@Override
	public PhotoParPage read(Object id) {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosParPages WHERE idPhoto=? AND idPage=?");
			int i[] = (int[])id;
			requete_select.setInt(1,i[0]);
			requete_select.setInt(2,i[1]);
			
			ResultSet result = requete_select.executeQuery();
			if(result.next())
			{
				PhotoParPage res = new PhotoParPage(
						result.getInt("idPhoto"),
						result.getInt("idPage"));
				requete_select.close();
				return res;
			}
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la lecture de la photo par page.");
			}
		}
		return null;
	}
	/**
	 * @return <b>List&lt;{@link PhotoParPage}&gt;</b> la liste de toutes les {@link PhotoParPage} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<PhotoParPage> readAll() {
		try {
			PreparedStatement requete_select = this.connect.prepareStatement("SELECT * FROM LesPhotosParPages");
			
			ResultSet result = requete_select.executeQuery();
			ArrayList<PhotoParPage> tab  = new ArrayList<PhotoParPage>();
			while(result.next())
			{
				PhotoParPage res = new PhotoParPage(
						result.getInt("idPhoto"),
						result.getInt("IdPage"));
				requete_select.close();
				tab.add(res);
			}
			return tab;
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la lecture de toutes les photos par page.");
			}
		}
		return null;
	}
	/**
	 * @param obj une {@link PhotoParPage} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(PhotoParPage obj) {
		try {
			PreparedStatement requete_update = this.connect.prepareStatement(
					"UPDATE LesPhotosParPages SET"
					+ "idPhoto=?,"
					+ "idPage=?");
			requete_update.setInt(1, obj.getIdPhoto());
			requete_update.setInt(2, obj.getIdPage());
			boolean b = requete_update.execute();
			requete_update.close();
			return b;
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la modification de la photo par page.");
			}
		}
		return false;
	}

	/**
	 * @param obj une {@link PhotoParPage} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(PhotoParPage obj) {
		try {
			PreparedStatement requete_delete = this.connect.prepareStatement(
					"DELETE FROM LesPhotosParPages WHERE idPhoto=? AND idPage=?");
			requete_delete.setInt(1, obj.getIdPhoto());
			requete_delete.setInt(2, obj.getIdPage());
			boolean b = requete_delete.execute();
			requete_delete.close();
			return b;
		} catch (SQLException e) {
			if (PhotoNum.DEBUG) {
				e.printStackTrace();
			} else {
				System.err.println("Quelque chose s'est mal passé pendant la suppression de la photo par page.");
			}
		}
		return false;
	}

}
