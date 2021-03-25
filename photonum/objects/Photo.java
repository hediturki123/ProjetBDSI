package photonum.objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import photonum.PhotoNum;

public class Photo  {

	private int idPhoto;
	private String chemin;
	
	public Photo(String chemin) {
		setIdPhoto(lastId()+1);
		this.chemin = chemin;
	}
	
	public static int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPhoto) FROM LesPhotos");
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
	
	public int getIdPhoto() {
		return idPhoto;
	}

	private void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
}
