package photonum.objects;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ClientDAO;
import photonum.dao.ImpressionDAO;
import photonum.dao.PageDAO;
import photonum.dao.PhotoTirageDAO;
import photonum.dao.ProduitDAO;

public class Impression{

	private int idImpression;
	private String mailClient;
	private String reference;
	private TypeImpression type;
	private String titre;

	private final static PhotoTirageDAO PT_DAO = new PhotoTirageDAO(PhotoNum.conn);
	private final static ProduitDAO PR_DAO = new ProduitDAO(PhotoNum.conn);
	private final static ClientDAO CL_DAO = new ClientDAO(PhotoNum.conn);
	private final static PageDAO PA_DAO = new PageDAO(PhotoNum.conn);
	private final static ImpressionDAO IP_DAO = new ImpressionDAO(PhotoNum.conn);

	public Impression() {
		setIdImpression(25);
	}

	public Impression(int idImpression, String mailClient ,String reference, TypeImpression type, String titre) {
		setIdImpression(idImpression);
		setMailClient(mailClient);
		setReference(reference);
		setType(type);
		setTitre(titre);
	}

	@Override
	public String toString() {
		String s="Le titre de l'impression est: ";
		s += getTitre();
		s += ".\nC'est un: "+getType();
		s += ".\nSa référence est: "+getReference();
		if(getType().equals(TypeImpression.TIRAGE)) {
			s += ".\nLes photos de ce tirage sont:\n";
			for(PhotoTirage p: getPhotosTirage()) {
				s += "\t" + p.toString() + "\n";
			}
		}
		else {
			s += ".\nLes pages de ce "+getType()+" sont:\n";
			for(Page p: getPages()) {
				s += "\t" + p.toString() + "\n";
			}
		}
		return s;
	}

	/*** getters and setters ***/
	public int getIdImpression() {
		return idImpression;
	}

	public void setIdImpression(int idImpression) {
		this.idImpression = idImpression;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public TypeImpression getType() {
		return type;
	}

	public void setType(TypeImpression type) {
		this.type = type;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Page> getPages() {
		return PA_DAO.readAllByImpression(this);
	}

	public List<PhotoTirage> getPhotosTirage() {
		return PT_DAO.readAllPhotoTirageByImpression(this);
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}

	public Produit getProduit() {
		return PR_DAO.read(reference);
	}

	public Client getProprietaire() {
		return CL_DAO.read(mailClient);
	}

	public void ajoutPages(List<Page> pages) {
		for (Page p : pages) {
			PA_DAO.create(p);
		}
	}

	public boolean nouvelleImpression() {
		return IP_DAO.create(this);
	}

	public boolean mettreAJour() {
		return IP_DAO.update(this);
	}

	public boolean supprimer() {
		return IP_DAO.delete(this);
	}

	public void ajoutPhotosTirage(List<PhotoTirage> photos) {
		for (PhotoTirage p : photos) {
			PT_DAO.create(p);
		}
	}


}
