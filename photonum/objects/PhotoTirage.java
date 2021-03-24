package photonum.objects;

import java.sql.Connection;

public class PhotoTirage extends Photo {
	private int nbFoisTiree;

	public PhotoTirage(Connection conn) {
		super(conn);
	}

	public PhotoTirage(Connection conn, int idPhoto, String chemin, int nbFoisTiree) {
		super(conn, idPhoto, chemin);
		this.nbFoisTiree = nbFoisTiree;
	}

	public int getNbFoisTiree() {
		return nbFoisTiree;
	}

	public void setNbFoisTiree(int nbFoisTiree) {
		this.nbFoisTiree = nbFoisTiree;
	}
}
