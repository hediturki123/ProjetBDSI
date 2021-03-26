package photonum.objects;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ImpressionDAO;

public class Page{

	private int idPage;
	private int idImpression;
	private String miseEnForme;
	private List<Photo> photos;

	private final static ImpressionDAO IM_DAO = new ImpressionDAO(PhotoNum.conn);

	public Page(int idImpression, String miseEnForme) {
		setAll(idImpression, miseEnForme);
	}

	@Override
	public String toString() {
		String s = "Cette page a pour ID: "+getIdPage();
		s += ".\nElle est liée a l'impression d'ID: "+getIdImpression();
		s += ".\nElle a comme mise en forme: "+getMiseEnForme();
		s += ".\nElle est composé de ces photos:\n";
		for(Photo p : getPhotos()) {
			s += "\t" + p.toString() + ".\n";
		}
		return s;
	}

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

	private void setAll(int idImpression, String miseEnForme){
		setIdImpression(idImpression);
		setMiseEnForme(miseEnForme);
	}

	public Impression getImpression() {
		return IM_DAO.read(idImpression);
	}
}