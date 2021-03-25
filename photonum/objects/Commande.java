package photonum.objects;

import java.sql.Date;
import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ArticleDAO;

public class Commande {

	private int idCommande;
	private String mail;
	private Date dateCommande;
	private boolean estLivreChezClient;
	private StatutCommande status;
	private String codePromo;

	public Commande() {}

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
		ArticleDAO artDao=new ArticleDAO(PhotoNum.conn);
        List<Article> tabArticles = artDao.readAllByCommande(this);
       String message=	"\n\nLes details de votre commande :";
        for(int i=1;i<=tabArticles.size();i++){
			message+="	Article n°"+i+" :"+tabArticles.get(i-1).toString()+"\n";
		}
		return message;
	}

	
}
