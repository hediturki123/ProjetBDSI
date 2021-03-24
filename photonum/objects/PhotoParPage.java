package photonum.objects;


public class PhotoParPage extends Photo {
	private int idPage;



	public PhotoParPage(int idPhoto, String chemin, int idPage) {
		super(idPhoto, chemin);
		setIdPage(idPage);
	}


	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}


}
