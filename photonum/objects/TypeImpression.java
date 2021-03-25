package photonum.objects;

public enum TypeImpression {
    TIRAGE ("tirage", "Tirage"),
    CADRE ("cadre", "Cadre"),
    ALBUM ("album", "Album"), 
    CALENDRIER ("calendrier", "Calendrier");

    private final String str;
    private final String fstr;

    TypeImpression(String s, String fs) {
        this.str = s;
        this.fstr = fs;
    }

    public String getString() {
        return this.str;
    }

    public String getFancyString() {
        return this.fstr;
    }

    public static TypeImpression fromString(String s) {
        TypeImpression ti = null;
        switch (s) {
            case "tirage": ti = TypeImpression.TIRAGE; break;
            case "cadre": ti = TypeImpression.CADRE; break;
            case "album": ti = TypeImpression.ALBUM; break;
            case "calendrier": ti = TypeImpression.CALENDRIER; break;
            default: break;
        }
        return ti;
    }

}


