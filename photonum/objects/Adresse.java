package photonum.objects;
import photonum.PhotoNum;
import photonum.dao.ClientDAO;

public class Adresse {

    private String mailClient;
	private int numeroRue;
	private String nomRue;
	private String ville;
	private int cp;
    private String pays;

	private final static ClientDAO CL_DAO = new ClientDAO(PhotoNum.conn);

	public Adresse() {}

    public Adresse(String mailClient, int numeroRue, String nomRue, String ville, int cp,
			String pays) {
		setAll(mailClient, numeroRue, nomRue, ville, cp, pays);
    }

	public Adresse(Client client, int numeroRue, String nomRue, String ville, int cp,
			String pays) {
		this(client.getMail(), numeroRue, nomRue, ville, cp, pays);
    }

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

	@Override
	public String toString() {
		return numeroRue +", "+nomRue+", "+cp+" "+ville.toUpperCase()+" ("+pays+")";
	}

	public Client getClient() {
		return CL_DAO.read(mailClient);
	}

}
