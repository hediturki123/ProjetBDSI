package photonum.objects;

public class PhotoTirage extends Photo {

	private int nbFoisTiree;

	public PhotoTirage(String chemin, int nbFois) {
		super(chemin);
		setNbFoisTiree(nbFois);
	}

	public int getNbFoisTiree() {
		return nbFoisTiree;
	}

	public void setNbFoisTiree(int nbFoisTiree) {
		this.nbFoisTiree = nbFoisTiree;
	}
}
