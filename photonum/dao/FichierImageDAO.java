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
	public FichierImageDAO(Connection conn) {
		super(conn);
	}

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
				resultat.getInt("estPartage")==0?true:false,
				resultat.getDate("dateUpload"));
			}
			requete_read.close();
			return img;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(FichierImage obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
				"UPDATE INTO LesFichiersImage set"+
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
			requeteUpdate.setInt(5, obj.isEstPartage() ? 0:1);
			requeteUpdate.setDate(6, obj.getDateUpload());
			int reussi=requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi==1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

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

	public List<FichierImage> readAllByClient(Client c){
		ArrayList<FichierImage> tabImg=new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"SELECT * FROM LesFichiersImage where mailProprio=?"
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

	public boolean cleanExpiredImages() {
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

