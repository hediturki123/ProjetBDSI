package photonum.objects;
import java.sql.Date;

import photonum.PhotoNum;
import photonum.dao.ClientDAO;
import photonum.dao.FichierImageDAO;

public class FichierImage {

	private String chemin;
	private String mailProprio;
	private String infoPVD;
	private int resolution;
	private boolean estPartage;
	private Date dateUpload;

	private final static ClientDAO CL_DAO = new ClientDAO(PhotoNum.conn);
	private final static FichierImageDAO FI_DAO = new FichierImageDAO(PhotoNum.conn);

	public FichierImage(String chemin, String mailProprio, String infoPVD, int resolution,
			boolean estPartage, Date dateUpload) {
		setAll(chemin, mailProprio, infoPVD, resolution, estPartage, dateUpload);
	}

	public FichierImage() {
	}

	/***********setter and getter  ***********/
	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getMailProprio() {
		return mailProprio;
	}

	public void setMailProprio(String mailProprio) {
		this.mailProprio = mailProprio;
	}

	public String getInfoPVD() {
		return infoPVD;
	}

	public void setInfoPVD(String infoPVD) {
		this.infoPVD = infoPVD;
	}

	public int getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		this.resolution = resolution;
	}

	public boolean isEstPartage() {
		return estPartage;
	}

	public void setEstPartage(boolean estPartage) {
		this.estPartage = estPartage;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}

	public void setAll(String chemin, String mailProprio, String infoPVD, int resolution,
	boolean estPartage, Date dateUpload){
		setChemin(chemin);
		setMailProprio(mailProprio);
		setInfoPVD(infoPVD);
		setResolution(resolution);
		setEstPartage(estPartage);
		setDateUpload(dateUpload);
	}

	@Override
	public String toString() {
		return chemin;
	}

	/**
	 * Récupère le propriétaire du fichier image.
	 * @return Client propriétaire du fichier image.
	 */
	public Client getProprietaire() {
		return CL_DAO.read(mailProprio);
	}

	/**
	 * Crée un nouveau fichier image dans la base de données.
	 * @return <b>true</b> si la création de l'image s'est bien passée ; <b>false</b> sinon.
	 */
	public boolean nouvelleImage() {
		return FI_DAO.create(this);
	}

	/**
	 * Met à jour le fichier image dans la base de données.
	 * @return <b>true</b> si la mise à jour de l'image s'est bien passée ; <b>false</b> sinon.
	 */
	public boolean mettreAJour() {
		return FI_DAO.update(this);
	}

	/**
	 * Supprime l'instance du fichier image dans la base de données.
	 * @return <b>true</b> si la suppression de l'image s'est bien passée ; <b>false</b> sinon.
	 */
	public boolean supprimer() {
		return FI_DAO.delete(this);
	}
}
