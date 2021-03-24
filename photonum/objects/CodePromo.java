package photonum.objects;

import photonum.PhotoNum;
import photonum.dao.CodePromoDAO;

public class CodePromo  {
    private String mail;
    private String code;
    private boolean estUtilise;

    public CodePromo() {}

    public CodePromo(String code, String mail) {
        this.mail = mail;
        this.code = (code.length() != 0 || code != null) ? code : System.currentTimeMillis()+"";
        this.estUtilise = false;
    }

    public CodePromo(String code, String mail, boolean estUtilise) {
        this(code, mail);
        this.estUtilise = estUtilise;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEstUtilise() {
        return this.estUtilise;
    }

    public boolean getEstUtilise() {
        return this.estUtilise;
    }

    public void setEstUtilise(boolean estUtilise) {
        this.estUtilise = estUtilise;
    }

    public static void ajouterPromo(String mail) {
        CodePromoDAO codePromoDAO = new CodePromoDAO(PhotoNum.conn);
        CodePromo cp = new CodePromo(null, mail);
        codePromoDAO.create(cp);
    }
}
