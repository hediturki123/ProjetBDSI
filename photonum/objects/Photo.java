package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.ClientDAO;
import photonum.dao.FichierImageDAO;

public class Photo  {

	private int idPhoto;
	private String chemin;
	private String mailClient;

	private final static FichierImageDAO FI_DAO = new FichierImageDAO(PhotoNum.conn);
	private final static ClientDAO CL_DAO = new ClientDAO(PhotoNum.conn);

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

	public FichierImage getImage() {
		return FI_DAO.read(chemin);
	}

	public Client getProprietaire() {
		return CL_DAO.read(mailClient);
	}
}
