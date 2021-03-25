package photonum.objects;
import photonum.PhotoNum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Page{

	private int idPage;
	private int idImpression;
	private String miseEnForme;
	private List<Photo> photos;
	
	public Page(int idImpression, String miseEnForme) {
		setIdPage(-1);
		setAll(idImpression, miseEnForme);
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
	
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	private void setAll(int idPage, String miseEnForme){
		setIdPage(idPage);
		setMiseEnForme(miseEnForme);
	}
}
