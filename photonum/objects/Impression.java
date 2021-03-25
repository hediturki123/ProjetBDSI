package photonum.objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import photonum.PhotoNum;

public class Impression{

	private int idImpression;
	private String mailClient;
	private String reference;
	private String type;
	private String titre;
	private List<Page> pages;
	private List<PhotoTirage> photosTirage;
	
	public Impression() {
		setIdImpression(-1);
	}
	
	public Impression(String mailClient ,String reference, String type, String titre) {
		setIdImpression(-1);
		setMailClient(mailClient);
		setReference(reference);
		setType(type);
		setTitre(titre);
		this.pages = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		String s="Le titre de l'impression est: ";
		s += getTitre();
		s += ".\n C'est un: "+getType();
		s += ".\n Sa référence est: "+getReference();
		if(getType().equals("tirage")) {
			s += ".\n Les photos de ce tirage sont: ";
			for(PhotoTirage p: getPhotosTirage()) {
				
			}
		}
		return s;
	}

	/*** getters and setters ***/
	public int getIdImpression() {
		return idImpression;
	}

	public void setIdImpression(int idImpression) {
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

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}
	
}
