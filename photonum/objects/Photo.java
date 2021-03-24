package photonum.objects;

public class Photo  {

	private int idPhoto;
	private String chemin;
	
	public Photo(int idPhoto, String chemin) {
		this.idPhoto = idPhoto;
		this.chemin = chemin;
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
