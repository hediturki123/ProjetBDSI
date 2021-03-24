package photonum.objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;

public class Impression{

	private int idImpression;
	private String reference;
	private String type;
	private String titre;
	private List<Page> pages;
	private List<PhotoTirage> photosTirage;
	
	public Impression() {
		setIdImpression(lastId()+1);
	}
	
	public Impression(String reference, String type, String titre) {
		setIdImpression(lastId()+1);
		setReference(reference);
		setType(type);
		setTitre(titre);
		this.pages = new ArrayList<>();
	}

	public static int lastId() {
		try {
			PreparedStatement requete_last = PhotoNum.conn.prepareStatement("SELECT max(idPage) FROM LesImpressions");
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

	/*** getters and setters ***/
	public int getIdImpression() {
		return idImpression;
	}

	private void setIdImpression(int idImpression) {
		this.idImpression = idImpression;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public List<PhotoTirage> getPhotosTirage() {
		return photosTirage;
	}

	public void setPhotosTirage(List<PhotoTirage> photosTirage) {
		this.photosTirage = photosTirage;
	}
	
}
