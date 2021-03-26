package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.PageDAO;
import photonum.dao.PhotoDAO;

public class PhotoParPage{
	private int idPage;
	private int idPhoto;

	private final static PhotoDAO PH_DAO = new PhotoDAO(PhotoNum.conn);
	private final static PageDAO PG_DAO = new PageDAO(PhotoNum.conn);

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

	public Photo getPhoto() {
		return PH_DAO.read(idPhoto);
	}

	public Page getPage() {
		return PG_DAO.read(idPage);
	}

}
