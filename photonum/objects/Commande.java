package photonum.objects;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Commande {

	private int idCommande;
	private String mail;
	private Date dateCommande;
	private boolean estLivreChezClient;
	private StatutCommande status;
	private String codePromo;

	public Commande(){
		
	}
	
	public Commande(int idCommande, String mail, Date dateCommande, boolean estLivreChezClient, StatutCommande status, String codePromo) {
		this.idCommande = idCommande;
		this.mail = mail;
		this.dateCommande = dateCommande;
		this.estLivreChezClient = estLivreChezClient;
		this.status = status;
		this.codePromo = codePromo;
	}

	public int getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public boolean isEstLivreChezClient() {
		return this.estLivreChezClient;
	}

	public boolean getEstLivreChezClient() {
		return this.estLivreChezClient;
	}

	public void setEstLivreChezClient(boolean estLivreChezClient) {
		this.estLivreChezClient = estLivreChezClient;
	}

	public StatutCommande getStatus() {
		return this.status;
	}

	public void setStatus(StatutCommande status) {
		this.status = status;
	}

	public String getCodePromo() {
		return this.codePromo;
	}

	public void setCodePromo(String codePromo) {
		this.codePromo = codePromo;
	}

	@Override
	public String toString() {
		String s = String.join(" | ",
			idCommande+"",
			mail,
			new SimpleDateFormat("dd/MM/yyyy").format(dateCommande),
			estLivreChezClient ? "Domicile" : "Point relais",
			status.getFancyString(),
			(codePromo == null ? "---" : codePromo)
		);
		return s;
	}


	public void genererFacture(int id) {
		
	}

}
