package fr.eni.ecole.enchere.bo;

import java.util.Date;

public class Enchere {
    private long no_utilisateur;
    private long no_article;
    private Date date_enchere;
    private int montant_enchere;

    //Constructor
    public Enchere(long no_utilisateur, long no_article, Date date_enchere, int montant_enchere) {
        this.no_utilisateur = no_utilisateur;
        this.no_article = no_article;
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }

    public Enchere(Date date_enchere, int montant_enchere) {
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }

    public Enchere() {
    }

    //Setter Getter
    public long getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(long no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public long getNo_article() {
        return no_article;
    }

    public void setNo_article(long no_article) {
        this.no_article = no_article;
    }

    public Date getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(Date date_enchere) {
        this.date_enchere = date_enchere;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "no_utilisateur=" + no_utilisateur +
                ", no_article=" + no_article +
                ", date_enchere=" + date_enchere +
                ", montant_enchere=" + montant_enchere +
                '}';
    }
}
