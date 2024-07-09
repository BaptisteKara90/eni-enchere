package fr.eni.ecole.enchere.bo;

public class Categorie {

    private long no_categorie;
    private String libelle;

    //Constructor
    public Categorie(long no_categorie, String libelle) {
        this.no_categorie = no_categorie;
        this.libelle = libelle;
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie() {
    }

    //Getter Setter
    public long getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(long no_categorie) {
        this.no_categorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    //ToString
    @Override
    public String toString() {
        return "categorie{" +
                "no_categorie=" + no_categorie +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
