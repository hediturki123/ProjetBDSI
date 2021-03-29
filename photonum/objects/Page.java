package photonum.objects;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ImpressionDAO;
import photonum.dao.PageDAO;
import photonum.dao.PhotoDAO;

public class Page{

	private int idPage;
	private int idImpression;
	private String miseEnForme;

	private final static ImpressionDAO IM_DAO = new ImpressionDAO(PhotoNum.conn);
	private final static PageDAO PG_DAO = new PageDAO(PhotoNum.conn);
	private final static PhotoDAO PH_DAO = new PhotoDAO(PhotoNum.conn);

	public Page(int idImpression, String miseEnForme) {
		setAll(idImpression, miseEnForme);
	}

	@Override
	public String toString() {
		String s = "Cette page a pour ID: "+getIdPage();
		s += ".\n\t\tElle est liée a l'impression d'ID: "+getIdImpression();
		s += ".\n\t\tElle a comme mise en forme: "+getMiseEnForme();
		s += ".\n\t\tElle est composée de ces photos:\n";
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
		return PH_DAO.readAllByPage(this);
	}

	private void setAll(int idImpression, String miseEnForme){
		setIdImpression(idImpression);
		setMiseEnForme(miseEnForme);
	}

	public Impression getImpression() {
		return IM_DAO.read(idImpression);
	}

	public boolean nouvellePage() {
		return PG_DAO.create(this);
	}

	public boolean mettreAJour() {
		return PG_DAO.update(this);
	}
}