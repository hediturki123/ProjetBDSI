package photonum.objects;

public enum StatutCommande {
    EN_COURS ("enCours", "En cours..."),
    PRETE_ENVOI ("preteEnvoi", "Prête à l'envoi."),
    ENVOYEE ("envoyee", "Envoyée !");

    private final String str;
    private final String fstr;

    StatutCommande(String s, String fs) {
        this.str = s;
        this.fstr = fs;
    }

    public String getString() {
        return this.str;
    }

    public String getFancyString() {
        return this.fstr;
    }

    public static StatutCommande fromString(String s) {
        StatutCommande sc = null;
        switch (s) {
            case "enCours": sc = StatutCommande.EN_COURS; break;
            case "preteEnvoi": sc = StatutCommande.PRETE_ENVOI; break;
            case "envoyee": sc = StatutCommande.ENVOYEE; break;
            default: break;
        }
        return sc;
    }
}
