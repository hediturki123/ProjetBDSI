package photonum.objects;

import java.util.List;

import photonum.PhotoNum;
import photonum.dao.ProduitDAO;

public class Produit {
    private String reference;
    private double prix;
    private int stock;

    private final static ProduitDAO PD_DAO = new ProduitDAO(PhotoNum.conn);

    public Produit() {}

    public Produit(String reference, double prix, int stock) {
        this.reference = reference;
        this.prix = prix;
        this.stock = stock;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return 
            " reference = " + getReference() +
            ", prix = " + getPrix();
    }

    public static List<Produit> getAll() {
        return PD_DAO.readAll();
    }

}
