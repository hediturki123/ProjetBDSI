package photonum.objects;

public class PhotoTirage extends Photo {
	public PhotoTirage(int idPhoto, String chemin) {
		super(idPhoto, chemin);
	}

	private int nbFoisTiree;

	public int getNbFoisTiree() {
		return nbFoisTiree;
	}

	public void setNbFoisTiree(int nbFoisTiree) {
		this.nbFoisTiree = nbFoisTiree;
	}
}
