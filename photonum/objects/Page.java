package photonum.objects;
import photonum.squellete_appli;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Page{

	private int idPage;
	private int idImpression;
	private String miseEnForme;
	
	public Page(int idPage, int idImpression, String miseEnForme) {
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

	

	
	/***** getters and setters *****/
	
	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}

	public int getIdImpression() {
		return idImpression;
	}

	public void setIdImpression(int idImpression) {
		this.idImpression = idImpression;
	}

	public String getMiseEnForme() {
		return miseEnForme;
	}

	public void setMiseEnForme(String miseEnForme) {
		this.miseEnForme = miseEnForme;
	}
	private void setAll(int idPage, int idImpression, String miseEnForme){
		setIdImpression(idImpression);
		setIdPage(idPage);
		setMiseEnForme(miseEnForme);
	}
}
