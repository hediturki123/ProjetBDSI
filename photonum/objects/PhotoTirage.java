package photonum.objects;

public class PhotoTirage extends Photo {

	private int nbFoisTiree;
	private int idImpression;

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
	
}
