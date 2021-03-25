package photonum.objects;

public class Article{
    private int idCommande;
    private int idImpression;
    private int quantite;


    public Article(int idCommande, int idImpression, int quantite) {
        setIdCommande(idCommande);
        setIdImpression(idImpression);
        setQuantite(quantite);
    }

    /**************Gettter and setter *******/
    public int getIdCommande() {
        return idCommande;
    }


    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }


    public int getIdImpression() {
        return idImpression;
    }


    public void setIdImpression(int idImpression) {
        this.idImpression = idImpression;
    }


    public int getQuantite() {
        return quantite;
    }


    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Article [idCommande=" + idCommande + ", idImpression=" + idImpression + ", quantite=" + quantite + "]";
    }
    

    /*************************/

    

    
}
