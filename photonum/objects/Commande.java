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
	private int numeroRue;
	private String nomRue;
	private String ville;
	private int codePostal;
	private String pays;

	public Commande() {}

	public Commande(int idCommande, String mail, Date dateCommande, boolean estLivreChezClient, StatutCommande status,
			String codePromo, int numeroRue, String nomRue, String ville, int codePostal, String pays) {
		this.idCommande = idCommande;
		this.mail = mail;
		this.dateCommande = dateCommande;
		this.estLivreChezClient = estLivreChezClient;
		this.status = status;
		this.codePromo = codePromo;
		setAdresseLivraison(numeroRue, nomRue, ville, codePostal, pays);
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

	public int getNumeroRue() {
		return numeroRue;
	}

	public void setNumeroRue(int numeroRue) {
		this.numeroRue = numeroRue;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public void setAdresseLivraison(int numeroRue, String nomRue, String ville, int codePostal, String pays) {
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}

	@Override
	public String toString() {
		String s = String.join(" | ",
			idCommande+"",
			mail,
			new SimpleDateFormat("dd/MM/yyyy").format(dateCommande),
			estLivreChezClient ? "Domicile" : "Point relais",
			status.getFancyString(),
			(codePromo == null ? "---" : codePromo),
			numeroRue+", "+nomRue+", "+codePostal+" "+ville.toUpperCase()+" ("+pays+")"
		);
		return s;
	}
}
