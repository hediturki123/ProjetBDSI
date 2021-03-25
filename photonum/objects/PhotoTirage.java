package photonum.objects;

public class PhotoTirage extends Photo {

	private int nbFoisTiree;

	public PhotoTirage(String chemin, String mailClient, int nbFois) {
		super(chemin, mailClient);
		setNbFoisTiree(nbFois);
	}

	public int getNbFoisTiree() {
		return nbFoisTiree;
	}

	public void setNbFoisTiree(int nbFoisTiree) {
		this.nbFoisTiree = nbFoisTiree;
	}
}
