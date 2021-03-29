package photonum.objects;

public class CodePromo  {

    private String mailClient;
    private String code;
    private boolean estUtilise;

    public CodePromo() {}

    public CodePromo(String code, String mail) {
        this.mailClient = mail;
        this.code = code;
        this.estUtilise = false;
    }

    public CodePromo(String code, String mail, boolean estUtilise) {
        this(code, mail);
        this.estUtilise = estUtilise;
    }

    public String getMailClient() {
        return this.mailClient;
    }

    public void setMail(String mail) {
        this.mailClient = mail;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean estUtilise() {
        return this.estUtilise;
    }

    public void utilise(boolean estUtilise) {
        this.estUtilise = estUtilise;
    }

    @Override
    public String toString() {
        return this.getCode();
    }
}
