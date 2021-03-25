package photonum.objects;

public class Produit {
    private String reference;
    private float prix;
    private int stock;

    public Produit() {
    }

    public Produit(String reference, float prix, int stock) {
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

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Produit reference(String reference) {
        setReference(reference);
        return this;
    }

    public Produit prix(float prix) {
        setPrix(prix);
        return this;
    }

    public Produit stock(int stock) {
        setStock(stock);
        return this;
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
