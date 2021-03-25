package photonum.objects;
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
    
    public Client(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
			String ville, int cp, String pays) {
		this.setAll(mail, nom, prenom, mdp, numeroRue, nomRue, ville, cp, pays);
    }
    
    /*******getter and setter********/
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
		public void setAll(String mail, String nom, String prenom, String mdp, int numeroRue, String nomRue,
		String ville, int cp, String pays){
			this.setMail(mail);
			this.setMdp(mdp);
			this.setNom(nom);
			this.setPrenom(prenom);
			this.setNomRue(nomRue);
			this.setNumeroRue(numeroRue);
			this.setVille(ville);
			this.setCp(cp);
			this.setPays(pays);
		}

		@Override
		public String toString() {
			String descriptif="vos informations:\n"+
			"mail = "+mail+
			"\nnom ="+nom+
			"\nprenom =" + prenom +
			"\nadresse = "+numeroRue +" "+nomRue+" "+cp+" "+ville+" "+pays;
			return descriptif+"\n";
		}
	
}
