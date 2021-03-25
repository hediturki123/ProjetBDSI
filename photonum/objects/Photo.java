package photonum.objects;

public class Photo  {

	private int idPhoto;
	private String chemin;
	
	public Photo(String chemin) {
		setIdPhoto(-1);
		this.chemin = chemin;
	}
	
	@Override
	public String toString() {
		String s ="Le chemin de cette photo est: ";
		s += getChemin();
		return s;
	}
	
	/***** getters and setters *****/
	
	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
}
