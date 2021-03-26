package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.CommandeDAO;
import photonum.dao.ImpressionDAO;

public class Article {

    private int idCommande;
    private int idImpression;
    private int quantite;

    private final static CommandeDAO C_DAO = new CommandeDAO(PhotoNum.conn);
    private final static ImpressionDAO I_DAO = new ImpressionDAO(PhotoNum.conn);

    public Article(int idCommande, int idImpression, int quantite) {
        setIdCommande(idCommande);
        setIdImpression(idImpression);
        setQuantite(quantite);
    }

    public Article(Commande c, Impression i, int quantite) {
        setIdCommande(c.getIdCommande());
        setIdImpression(i.getIdImpression());
        setQuantite(quantite);
    }

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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public Impression getImpression() {
        return I_DAO.read(idImpression);
    }

    public Commande getCommande() {
        return C_DAO.read(idCommande);
    }

    @Override
    public String toString() {
        return "Article [idCommande=" + idCommande + ", idImpression=" + idImpression + ", quantite=" + quantite + "]";
    }

    public String factureString() {
        Produit p = getImpression().getProduit();
        String fs = String.join(" | ",
            p.getReference(),
            p.getPrix()+"€",
            quantite+"",
            p.getPrix()*quantite+"€"
        );
        return fs;
    }
}
