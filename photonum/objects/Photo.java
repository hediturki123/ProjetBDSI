package photonum.objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import photonum.PhotoNum;

public class Photo  {

	private int idPhoto;
	private String chemin;
	
	public Photo(String chemin) {
		setIdPhoto(-1);
		this.chemin = chemin;
	}
	
	/***** getters and setters *****/
	
	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
}
