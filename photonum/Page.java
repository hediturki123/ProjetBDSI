package photonum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Page extends DAO<Page> {

	private int idPage;
	private int idImpression;
	private String miseEnForme;
	
	public Page(Connection conn, int idPage, int idImpression, String miseEnForme) {
		super(conn);
		setAll(idPage, idImpression, miseEnForme);
	}
	
	public static int lastId() {
		try {
			PreparedStatement requete_last = squellete_appli.conn.prepareStatement("SELECT max(idPage)+1 FROM LesPages");
			ResultSet res = requete_last.executeQuery();
			if(res.next()) {
				return res.getInt("idPage");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
				p=new Page(this.connect, 
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
					new Page(this.connect,
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

	
	/***** getters and setters *****/
	
	protected int getIdPage() {
		return idPage;
	}

	protected void setIdPage(int idPage) {
		this.idPage = idPage;
	}

	protected int getIdImpression() {
		return idImpression;
	}

	protected void setIdImpression(int idImpression) {
		this.idImpression = idImpression;
	}

	protected String getMiseEnForme() {
		return miseEnForme;
	}

	protected void setMiseEnForme(String miseEnForme) {
		this.miseEnForme = miseEnForme;
	}
	private void setAll(int idPage, int idImpression, String miseEnForme){
		setIdImpression(idImpression);
		setIdPage(idPage);
		setMiseEnForme(miseEnForme);
	}
}
