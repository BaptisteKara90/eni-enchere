package fr.eni.ecole.enchere.bo;

public class Retrait {
    private long no_article;
    private String rue;
    private String code_postal;
    private String ville;

    //Constructor
    public Retrait(long no_article, String rue, String code_postal, String ville) {
        this.no_article = no_article;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Retrait(String rue, String code_postal, String ville) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Retrait() {
    }

    //Getter Setter
    public long getNo_article() {
        return no_article;
    }

    public void setNo_article(long no_article) {
        this.no_article = no_article;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    //ToString
    @Override
    public String toString() {
        return "Retrait{" +
                "no_article=" + no_article +
                ", rue='" + rue + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
