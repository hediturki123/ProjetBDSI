package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.CodePromoDAO;

public class CodePromo  {

    private String mailClient;
    private String code;
    private boolean estUtilise;

    private final static CodePromoDAO CP_DAO = new CodePromoDAO(PhotoNum.conn);

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

    public static void ajouterPromo(String mail) {
        CodePromo cp = new CodePromo(null, mail);
        CP_DAO.create(cp);
    }

    public static void ajouterPromo(Client c) {
        ajouterPromo(c.getMail());
    }
}
