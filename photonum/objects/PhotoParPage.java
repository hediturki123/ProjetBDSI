package photonum.objects;


public class PhotoParPage extends Photo {
	private int idPage;

	public PhotoParPage(String chemin, int idPage) {
		super(chemin);
		setIdPage(idPage);
	}


	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}


}
