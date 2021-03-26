package photonum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;
import photonum.objects.Impression;
import photonum.objects.Page;

public class PageDAO extends DAO<Page>{

	/**
	 * construit une PageDAO avec la connexions à la BD
	 * @param conn
	 */
	public PageDAO(Connection conn) {
		super(conn);
	}

	/**
	 * @param obj une {@link Page} à creer dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean create(Page obj) {
		try {
			PreparedStatement requeteCreate=this.connect.prepareStatement(
				"INSERT INTO LesPages VALUES (?,?,?)"
			);
			obj.setIdPage(lastId() + 1);
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

	/**
	 * 
	 * @param id un <b>Int[2]</b> contenant <b>IdPage</b> et <b>idImpression</b>
	 * @return {@link Page} appartenant à l'impression <b>idImpression</b>
	 * @exception SQLException;	 
	 * */

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
				p = new Page(
					resultat.getInt("idImpression"),
					resultat.getString("miseEnForme")
				);
				p.setIdPage(resultat.getInt("idPage"));
			}
			requeteRead.close();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param obj une {@link Page} à update dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
	@Override
	public boolean update(Page obj) {
		try {
			PreparedStatement requeteUpdate=this.connect.prepareStatement(
				"UPDATE LesPages SET"+
				" miseEnForme=? WHERE idPage=? AND idImpression=?"
			);
			requeteUpdate.setString(1,obj.getMiseEnForme());
			requeteUpdate.setInt(2,obj.getIdPage());
			requeteUpdate.setInt(3,obj.getIdImpression());

			int reussi=requeteUpdate.executeUpdate();
			requeteUpdate.close();
			return reussi==1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param obj une {@link Page} à delete dans la BD
	 * @return <b>boolean</b> l'action c'est bien passée
	 * @exception SQLException;
	 */
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

	/**
	 * @return <b>List&lt;{@link Page}&gt;</b> la liste de toutes les {@link Page} de la base
	 * @exception SQLException;
	 */
	@Override
	public List<Page> readAll() {
		ArrayList<Page> tab = new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from LesPages"
			);
			ResultSet resultat=requeteAll.executeQuery();
			while(resultat.next()){
				Page p = new Page(
					resultat.getInt("idImpression"),
					resultat.getString("miseEnForme")
				);
				p.setIdPage(resultat.getInt("idPage"));
				tab.add(p);
			}
			requeteAll.close();
			return tab;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Page> readAllByImpression(Impression impression){
		ArrayList<Page> tab = new ArrayList<>();
		try {
			PreparedStatement requeteAll=this.connect.prepareStatement(
				"select * from LesPages WHERE idImpression = ?"
			);
			requeteAll.setInt(1, impression.getIdImpression());
			ResultSet resultat=requeteAll.executeQuery();
			while(resultat.next()){
				Page p = new Page(
					resultat.getInt("idImpression"),
					resultat.getString("miseEnForme")
				);
				p.setIdPage(resultat.getInt("idPage"));
				tab.add(p);
			}
			requeteAll.close();
			return tab;
		} catch (Exception e) {
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
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPage) FROM LesPages");
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
