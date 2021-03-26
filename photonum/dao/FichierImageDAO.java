package photonum.dao;

import photonum.objects.FichierImage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Client;

public class FichierImageDAO extends DAO<FichierImage> {

	/**
	 * construit un FichierDAO avec la connexions à la BD
	 * @param conn
	 */
	public FichierImageDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj un {@link FichierImage} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(FichierImage obj) {
		try{
			PreparedStatement requeteAjout=this.connect.prepareStatement(
				"INSERT INTO LesFichiersImage values (?,?,?,?,?,?)");
				requeteAjout.setString(1, obj.getChemin());
				requeteAjout.setString(2, obj.getMailProprio());
				requeteAjout.setString(3, obj.getInfoPVD());
				requeteAjout.setInt(4, obj.getResolution());
				requeteAjout.setBoolean(5, obj.isEstPartage());
				requeteAjout.setDate(6, obj.getDateUpload());


			boolean reussi=requeteAjout.execute();
			requeteAjout.close();
			obj.setAll(obj.getChemin(), obj.getMailProprio(), obj.getInfoPVD(), obj.getResolution(), obj.isEstPartage(), obj.getDateUpload());
			return reussi;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param id un <b>String[2]</b> contenant <b>mailProprio</b> et <b>chemin</b>
	 * @return Le {@link FichierImage} correspondant à <b>mailProprio</b> et <b>chemin</b>
	 * @exception SQLException;	 
	 * */
	@Override
	public FichierImage read(Object id) {
		String [] args = (String []) id;
		FichierImage img;
		try {
			PreparedStatement requete_read=this.connect.prepareStatement(
			"SELECT * FROM LesFichiersImage where mailProprio=? and chemin=?");
			requete_read.setString(1, args[0]);
			requete_read.setString(2, args[1]);
			ResultSet resultat=requete_read.executeQuery();
			if(!resultat.next()){
				img=null;
			}else{
				img=new FichierImage(
				resultat.getString("chemin"),
				resultat.getString("mailProprio"), 
				resultat.getString("infoPVD"), 
				resultat.getInt("resolution"), 
				resultat.getBoolean("estPartage"), 
				resultat.getDate("dateUpload"));
			}
			requete_read.close();
			return img;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param obj un {@link FichierImage} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(FichierImage obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
				"UPDATE LesFichiersImage SET "+
				"chemin=?,"+
				"mailProprio=?,"+
				"infoPvd=?,"+
				"resolution=?,"+
				"estPartage=?,"+
				"dateUpload=?"+
				"where mailProprio=? and chemin=?");
			requeteUpdate.setString(1, obj.getChemin());
			requeteUpdate.setString(2, obj.getMailProprio());
			requeteUpdate.setString(3, obj.getInfoPVD());
			requeteUpdate.setInt(4, obj.getResolution());
			requeteUpdate.setBoolean(5, obj.isEstPartage());
			requeteUpdate.setDate(6, obj.getDateUpload());
			requeteUpdate.setString(7, obj.getMailProprio());
			requeteUpdate.setString(8, obj.getChemin());
			
			int reussi=requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi==1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param obj un {@link FichierImage} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean delete(FichierImage obj) {
		try {
			PreparedStatement requeteSuppr=this.connect.prepareStatement(
				"DELETE FROM lesFichiersImage where mailProprio=? and chemin=?");
				requeteSuppr.setString(1, obj.getMailProprio());
				requeteSuppr.setString(2, obj.getChemin());
			int reussi=requeteSuppr.executeUpdate();
			requeteSuppr.close();
			return reussi==1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		/**
	 * @return <b>List&lt;{@link FichierImage}&gt;</b> la liste de tous les FichierImage de la base
	 * @exception SQLException;
	 */
	@Override
	public List<FichierImage> readAll() {
		ArrayList<FichierImage> tab=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from LesFichiersImage ");
			ResultSet resultat= requeteAll.executeQuery();
			while(resultat.next()){
				tab.add(
					new FichierImage(
					resultat.getString("chemin"),
					resultat.getString("mailProprio"),
					resultat.getString("infoPVD"),
					resultat.getInt("resolution"),
					resultat.getInt("estPartage")==1 ? true:false,
					resultat.getDate("dateUpload")
				));
			}
			requeteAll.close();
			return tab;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param c une {@link Commande} pour trouver ces articles correspondant 
	 * @return <b>List&lt;{@link FichierImage}&gt;</b> la liste de toutes les adresseDeLivraison de la base en fonction du client
	 * @exception SQLException;
	 */

	public List<FichierImage> readAllByClient(Client c){
		ArrayList<FichierImage> tabImg=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"SELECT * FROM LesFichiersImage where mailProprio=? or estPartage=1"
			);
			requeteAll.setString(1,c.getMail());
			ResultSet resultat=requeteAll.executeQuery();
			while(resultat.next()){
				tabImg.add(
					new FichierImage(
						resultat.getString("chemin"),
						resultat.getString("mailProprio"),
						resultat.getString("infoPVD"),
						resultat.getInt("resolution"),
						resultat.getBoolean("estPartage"),
						resultat.getDate("dateUpload"))
				);
			}
			requeteAll.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabImg;
	}

	/**
	 * Ici on précise si le fichier est partagé ou non  
	 * @param c un {@link Client} pour trouvez ses FichierImage 
	 * @param estPartagee savoir si les fichiers sont partagés ou non 
	 * @return <b>List&lt;{@link FichierImage}&gt; </b> la liste de tous les fichiersImages de la base en fonction du client
	 * 	et si il sont partarger ou non 
	 * @exception SQLException;
	 */
	public List<FichierImage> readAllByClient(Client c,boolean estPartagee){
		ArrayList<FichierImage> tabImg=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"SELECT * FROM LesFichiersImage where mailProprio=? and estPartage=?"
			);
			requeteAll.setString(1,c.getMail());
			requeteAll.setBoolean(2, estPartagee);
			ResultSet resultat=requeteAll.executeQuery();
			while(resultat.next()){
				tabImg.add(
					new FichierImage(
						resultat.getString("chemin"),
						resultat.getString("mailProprio"),
						resultat.getString("infoPVD"),
						resultat.getInt("resolution"),
						resultat.getBoolean("estPartage"),
						resultat.getDate("dateUpload"))
				);
			}
			requeteAll.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabImg;
	}
	/**
	 * Permet d'appeler la procedure <b>expi_img_proc</b> qui regarde si les fichiers on plus de 10 jours
	 * et si oui alors elle supprime les fichier de la BD
	 * @return <b>boolean</b> la procédure <b>expi_img_proc</b> a bien fonctionnée
	 */
	public static boolean cleanExpiredImages() {
		boolean success = false;
		try {
			CallableStatement cstmt = PhotoNum.conn.prepareCall("{call expi_img_proc}");
			cstmt.execute();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}

