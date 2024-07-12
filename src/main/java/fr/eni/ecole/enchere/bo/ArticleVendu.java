package fr.eni.ecole.enchere.bo;

import java.time.LocalDate;


public class ArticleVendu {

    private long no_article;
    private String nom_article;
    private String description;
    private LocalDate date_debut_encheres;
    private LocalDate date_fin_encheres;
    private long prix_initial;
    private long prix_vente;
    private Utilisateur utilisateur;
    private Categorie categorie;

    //Constructor
    public ArticleVendu(long no_article, String nom_article, String description, LocalDate date_debut_encheres, LocalDate date_fin_encheres, long prix_initial, long prix_vente, Utilisateur utilisateur, Categorie categorie) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
    }

    public ArticleVendu(String nom_article, String description, LocalDate date_debut_encheres, LocalDate date_fin_encheres, long prix_initial, long prix_vente, Utilisateur utilisateur, Categorie categorie) {
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
    }

    public ArticleVendu() {
    }

    //Getter Setter

    public long getNo_article() {
        return no_article;
    }

    public void setNo_article(long no_article) {
        this.no_article = no_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_debut_encheres() {
        return date_debut_encheres;
    }

    public void setDate_debut_encheres(LocalDate date_debut_encheres) {
        this.date_debut_encheres = date_debut_encheres;
    }

    public LocalDate getDate_fin_encheres() {
        return date_fin_encheres;
    }

    public void setDate_fin_encheres(LocalDate date_fin_encheres) {
        this.date_fin_encheres = date_fin_encheres;
    }

    public long getPrix_initial() {
        return prix_initial;
    }

    public void setPrix_initial(long prix_initial) {
        this.prix_initial = prix_initial;
    }

    public long getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(long prix_vente) {
        this.prix_vente = prix_vente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    //ToString

    @Override
    public String toString() {
        return "ArticleVendu{" +
                "no_article=" + no_article +
                ", nom_article='" + nom_article + '\'' +
                ", description='" + description + '\'' +
                ", date_debut_encheres=" + date_debut_encheres +
                ", date_fin_encheres=" + date_fin_encheres +
                ", prix_initial=" + prix_initial +
                ", prix_vente=" + prix_vente +
                ", utilisateur=" + utilisateur +
                ", categorie=" + categorie +
                '}';
    }
}
