package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.PhotoTirageDAO;

public class PhotoTirage extends Photo {

	private int nbFoisTiree;
	private int idImpression;

	private final static PhotoTirageDAO PT_DAO = new PhotoTirageDAO(PhotoNum.conn);

	public PhotoTirage(String chemin, String mailClient, int nbFois, int idImpression) {
		super(chemin, mailClient);
		setNbFoisTiree(nbFois);
		setIdImpression(idImpression);
	}

	public int getNbFoisTiree() {
		return nbFoisTiree;
	}

	public void setNbFoisTiree(int nbFoisTiree) {
		this.nbFoisTiree = nbFoisTiree;
	}

	public int getIdImpression() {
		return idImpression;
	}

	public void setIdImpression(int idImpression) {
		this.idImpression = idImpression;
	}

	public boolean nouveauTirage() {
		return PT_DAO.create(this);
	}
}
