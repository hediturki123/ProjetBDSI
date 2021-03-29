package photonum.objects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ArticleDAO;
import photonum.dao.ClientDAO;
import photonum.dao.CommandeDAO;
import photonum.dao.CodePromoDAO;

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

	private final static ArticleDAO AT_DAO = new ArticleDAO(PhotoNum.conn);
	private final static CommandeDAO CM_DAO = new CommandeDAO(PhotoNum.conn);
	private final static ClientDAO CL_DAO = new ClientDAO(PhotoNum.conn);
	private final static CodePromoDAO CP_DAO = new CodePromoDAO(PhotoNum.conn);

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

	public void setAdresseLivraison(Adresse a) {
		setAdresseLivraison(
			a.getNumeroRue(),
			a.getNomRue(),
			a.getVille(),
			a.getCp(),
			a.getPays()
		);
	}

	public List<Article> getArticles() {
		return AT_DAO.readAllByCommande(this);
	}

	public Article getArticle(int index) {
		return AT_DAO.readAllByCommande(this).get(index);
	}

	public void ajouterArticles(List<Article> articles) {
		articles.forEach(a -> {
			AT_DAO.create(a);
		});
	}

	public void ajouterArticle(Article a) {
		AT_DAO.create(a);
	}

	public void ajouterArticle(int idImpression, int quantite) {
		AT_DAO.create(new Article(this.idCommande, idImpression, quantite));
	}

	public double getPrixTotal() {
		return CM_DAO.getPrix(this.idCommande);
	}

	@Override
	public String toString() {
		CodePromo cp = getCodePromoStruct();
		String s = String.join(" | ",
			idCommande+"",
			mail,
			new SimpleDateFormat("dd/MM/yyyy").format(dateCommande),
			estLivreChezClient ? "Domicile" : "Point relais",
			status.getFancyString(),
			codePromo == null ? "---" : codePromo,
			numeroRue+", "+nomRue+", "+codePostal+" "+ville.toUpperCase()+" ("+pays+")"
		);
		return s;
	}

	public String facture() {
		final double PRIX_TOTAL = getPrixTotal();
		final List<Article> articles = getArticles();

		String f = "< FACTURE n°"+idCommande+" >\n"+"-".repeat(10)+"\n";
		f += "Vous avez commandé "+(articles.size() > 1 ? "les "+articles.size()+" suivants" : "l'article suivant")+" :\n";
		f += "Référence | Prix / u. | Quantité | Prix\n";
		for (Article a : articles) {
			f += "\t"+a.factureString()+"\n";
		}
		f += "-".repeat(10)+"\n";
		f += "PRIX HT : "+(PRIX_TOTAL*0.8)+"€ / PRIX TTC : "+PRIX_TOTAL+"€\n";

		return f;
	}

	/**
	 * Crée une nouvelle commande dans la base de données.
	 * @return <b>true</b> si la création de la commande a réussi ; <b>false</b> sinon.
	 */
	public boolean nouvelleCommande() {
		return CM_DAO.create(this);
	}

	/**
	 * Met à jour la commande dans la base de données.
	 * @return <b>true</b> si la mise à jour de la commande s'est bien passée ; <b>false</b> sinon.
	 */
	public boolean mettreAJour() {
		return CM_DAO.update(this);
	}

	/**
	 * Récupère une commande depuis la base de données.
	 * @param id Identifiant de la commande à récupérer.
	 * @return Une commande.
	 */
	public static Commande get(int id) {
		return CM_DAO.read(id);
	}

	/**
	 * Récupère la liste des commandes ayant le statut indiqué.
	 * @param sc Un statut de commande (EN_COURS, PRETE_ENVOI, ENVOYEE).
	 * @return Liste de commandes.
	 */
	public static List<Commande> getByStatus(StatutCommande sc) {
		return CM_DAO.readAllByStatus(sc);
	}

	public Client getClient() {
		return CL_DAO.read(mail);
	}

	public CodePromo getCodePromoStruct() {
		return CP_DAO.read(codePromo);
	}
}
