package photonum.objects;


public class PhotoParPage{
	private int idPage;
	private int idPhoto;
	
	public PhotoParPage(int idPhoto, int idPage) {
		setIdPage(idPage);
		setIdPhoto(idPhoto);
	}


	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}


}
