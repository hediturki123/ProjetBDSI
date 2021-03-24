package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import photonum.objects.Page;

public class PageDAO extends DAO<Page>{

	public PageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Page obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesPages VALUES (?,?,?)"
			);
			requeteCreate.setInt(1,obj.getIdPage());
			requeteCreate.setInt(2, obj.getIdImpression());
			requeteCreate.setString(3, obj.getMiseEnForme());

			int reussi = requeteCreate.executeUpdate();
			requeteCreate.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page read(Object id) {
		int [] args=(int[])id;
		Page p;
		try {
			PreparedStatement requeteRead=this.connect.prepareStatement(
				"SELECT * FROM LesPages where idPage=? and idImpression=?"
			);
			requeteRead.setInt(1,args[0]);
			requeteRead.setInt(2,args[1]);
			ResultSet resultat = requeteRead.executeQuery();
			if(!resultat.next()){
				p=null;
			}else{
				p=new Page(
				resultat.getInt("idPage"), 
				resultat.getInt("idImpression"), 
				resultat.getString("miseEnForme"));
			}
			requeteRead.close();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page[] readAll(Object obj) {
		ArrayList<Page> tab = new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from LesPages"
			);
			ResultSet resultat=requeteAll.executeQuery();
			while(resultat.next()){
				tab.add(
					new Page(
					 resultat.getInt("idPage"),
					 resultat.getInt("idImpression"),
					 resultat.getString("miseEnForme"))
				);
			}
			requeteAll.close();
			return (Page []) tab.toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Page obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
				"UPDATE INTO LesPages set"+
				"miseEnForme=? where idPage=? and idImpression=?"
			);
			requeteUpdate.setString(1,obj.getMiseEnForme());
			requeteUpdate.setInt(1,obj.getIdPage());
			requeteUpdate.setInt(2,obj.getIdImpression());

			int reussi=requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Page obj) {
		try {
			PreparedStatement requetDelete=this.connect.prepareStatement(
				"DELETE FROM lesPages where idPage=? and idImpression=?"
			);
			requetDelete.setInt(1, obj.getIdPage());
			requetDelete.setInt(2, obj.getIdImpression());

			int reussi=requetDelete.executeUpdate();
			requetDelete.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
