package photonum.objects;
import java.sql.Date;

public class FichierImage {
	private String chemin;
	private String mailProprio;
	private String infoPVD;
	private int resolution;
	private boolean estPartage;
	private Date dateUpload;

	public FichierImage(String chemin, String mailProprio, String infoPVD, int resolution,
			boolean estPartage, Date dateUpload) {
		setAll(chemin, mailProprio, infoPVD, resolution, estPartage, dateUpload);
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
	/*********************************************/
}
