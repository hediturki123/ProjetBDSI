package photonum.objects;

public class Produit {
    private String reference;
    private double prix;
    private int stock;

    public Produit() {
    }

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
        return "{" +
            " reference='" + getReference() + "'" +
            ", prix='" + getPrix() + "'" +
            ", stock='" + getStock() + "'" +
            "}";
    }


}
