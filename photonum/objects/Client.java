package photonum.objects;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.AdresseDAO;
import photonum.dao.ClientDAO;
import photonum.dao.CodePromoDAO;
import photonum.dao.CommandeDAO;
import photonum.dao.FichierImageDAO;
import photonum.dao.ImpressionDAO;
import photonum.dao.PhotoAlbumDAO;
import photonum.dao.PhotoDAO;
import photonum.dao.PhotoTirageDAO;

public class Client {

    private String mail;
	private String nom;
	private String prenom;
	private String mdp;
	private int numeroRue;
	private String nomRue;
	private String ville;
	private int cp;
	private String pays;
	private boolean actif;

	private final static AdresseDAO AD_DAO = new AdresseDAO(PhotoNum.conn);
	private final static ClientDAO CL_DAO = new ClientDAO(PhotoNum.conn);
	private final static ImpressionDAO IM_DAO = new ImpressionDAO(PhotoNum.conn);
	private final static CommandeDAO CM_DAO = new CommandeDAO(PhotoNum.conn);
	private final static CodePromoDAO CP_DAO = new CodePromoDAO(PhotoNum.conn);
	private final static FichierImageDAO FI_DAO = new FichierImageDAO(PhotoNum.conn);
	private final static PhotoTirageDAO PT_DAO = new PhotoTirageDAO(PhotoNum.conn);
	private final static PhotoDAO PH_DAO = new PhotoDAO(PhotoNum.conn);
	private final static PhotoAlbumDAO PA_DAO = new PhotoAlbumDAO(PhotoNum.conn);

    public Client(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
			String ville, int cp, String pays, boolean actif) {
		this.setAll(mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays, actif);
    }

	public Client(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
			String ville, int cp, String pays) {
		this(mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays, true);
    }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
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

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public void setAll(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
	String ville, int cp, String pays, boolean actif){
		this.setMail(mail);
		this.setMdp(mdp);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNomRue(nomRue);
		this.setNumeroRue(numeroRue);
		this.setVille(ville);
		this.setCp(cp);
		this.setPays(pays);
		this.setActif(actif);
	}

	@Override
	public String toString() {
		String descriptif =
			"???"+"???".repeat(32)+"???\n"+
			prenom+" "+nom+" <"+mail+">\n" + (isActif() ? "" : "(INACTIF)") +
			numeroRue +", "+nomRue+", "+cp+" "+ville.toUpperCase()+" ("+pays+")\n"+
			"???"+"???".repeat(32)+"???\n";
		return descriptif;
	}

	/**
	 * R??cup??re les adresses de livraison associ??es au client.
	 * @return Liste des adresses de livraison.
	 */
	public List<Adresse> getAdressesLivraison() {
		return AD_DAO.readAllByClient(this);
	}

	/**
	 * Ajoute une nouvelle adresse de livraison au client.
	 * @param a Une adresse de livraison.
	 */
	public void ajouterAdresseLivraison(Adresse a) {
		AD_DAO.create(a);
	}

	/**
	 * Cr??e un nouveau compte client dans la base de donn??es.
	 * @return <b>true</b> si la cr??ation du compte s'est d??roul??e correctement ; <b>false</b> sinon.
	 */
	public boolean nouveauCompte() {
		return CL_DAO.create(this);
	}

	public static Client connexion(String mail, String mdp) {
		String[] infos = {mail, mdp};
		return CL_DAO.getConnexion(infos);
	}

	/**
	 * R??cup??re la liste des impressions cr??es par un client.
	 * @return Les impressions d'un client.
	 */
	public List<Impression> getImpressions() {
		return IM_DAO.readAllByClient(this);
	}

	/**
	 * R??cup??re la liste des commandes (pass??es et pr??sentes) du client.
	 * @return Toutes les commandes d'un client.
	 */
	public List<Commande> getCommandes() {
		return CM_DAO.readAllByClient(this);
	}

	/**
	 * R??cup??re la liste de <u>tous</u> les codes promo d'un client.
	 * @return Tous les codes promos d'un client.
	 */
	public List<CodePromo> getCodesPromo() {
		return CP_DAO.readAllByClient(mail);
	}

	/**
	 * R??cup??re la liste des codes promo (utilis??s ou non) d'un client.
	 * @param utilise <b>true</b> si on cherche les codes utilis??s, <b>false</b> si on cherche les codes utilisables.
	 * @return Les codes promos (utilis??s ou non) d'un client.
	 */
	public List<CodePromo> getCodesPromo(boolean utilise) {
		return CP_DAO.readAllByClient(mail, utilise);
	}

	/**
	 * R??cup??re la liste des images import??es par un client.
	 * @return Fichiers images import??es par le client.
	 */
	public List<FichierImage> getImages() {
		return FI_DAO.readAllByClient(this);
	}

	/**
	 * R??cup??re la liste des images partag??es ou non par le client.
	 * @param partages <b>true</b> si les images sont partag??es ; <b>false</b> sinon.
	 * @return Fichiers images partag??s (ou non) par le client.
	 */
	public List<FichierImage> getImages(boolean partages) {
		return FI_DAO.readAllByClient(this, partages);
	}
	/**
	 * R??cup??re la liste des Photo tirage
	 * @return
	 */
	public List<PhotoTirage> getPhotosTirage() {
		return PT_DAO.readAllPhotosTirageByClient(this);
	}


	public List<Photo> getPhotos() {
		return PH_DAO.readAllPhotosByClient(mail);
	}

	public List<PhotoAlbum> getPhotosAlbum() {
		return PA_DAO.readAllPhotosAlbumByClient(mail);
	}

	/**
	 * permet de regarder si l'adresse du client est deja dans la base de donn??e pour eviter la redondances de sont adresse
	 * @param a une {@link Adresse} qui sera l'adresse des client
	 */
	public void checkAdresseExist(Adresse a) {
		List<Adresse> addr = this.getAdressesLivraison();
		if (!addr.contains(a)) {
			ajouterAdresseLivraison(a);
		}
	}
 }
