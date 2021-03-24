package photonum.objects;
public class Adresse {
    private String mailClient;
	private int numeroRue;
	private String nomRue;
	private String ville;
	private int cp;
    private String pays;
    
    public Adresse(String mailClient, int numeroRue, String nomRue, String ville, int cp,
			String pays) {
		setAll(mailClient, numeroRue, nomRue, ville, cp, pays);
    }
    
    /******setter and getter****/

	public String getMailClient() {
		return mailClient;
	}
	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
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
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}

	private void  setAll(String mailClient, int numeroRue, String nomRue, String ville, int cp,
	String pays){
		setMailClient(mailClient);
		setNumeroRue(numeroRue);
		setNomRue(nomRue);
		setVille(ville);
		setCp(cp);
		setPays(pays);
	}

	/******setter and getter****/
}
