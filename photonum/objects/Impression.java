package photonum.objects;

public class Impression{

	private int idImpression;
	private String reference;
	private String type;
	private String titre;
	
	public Impression() {}
	
	public Impression(int idImpression, String reference, String type, String titre) {
		this.idImpression = idImpression;
		this.reference = reference;
		this.type = type;
		this.titre = titre;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
