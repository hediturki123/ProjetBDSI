package photonum.objects;

public class Photo  {

	private int idPhoto;
	private String chemin;
	private String mailClient;
	
	public Photo(String chemin, String mailClient) {
		this.mailClient = mailClient;
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

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}
	
}
