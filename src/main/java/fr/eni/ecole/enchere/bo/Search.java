package fr.eni.ecole.enchere.bo;

public class Search {

    private String motCle;
    private int categorie;
    private String type = "";
    private boolean encheresOuvertes;
    private boolean mesEncheresEnCours;
    private boolean mesEncheresRemportees;
    private boolean mesVentesEnCours;
    private boolean ventesNonDebutees;
    private boolean venteTerminee;

    public Search() {
    }

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEncheresOuvertes() {
        return encheresOuvertes;
    }

    public void setEncheresOuvertes(boolean encheresOuvertes) {
        this.encheresOuvertes = encheresOuvertes;
    }

    public boolean isMesEncheresEnCours() {
        return mesEncheresEnCours;
    }

    public void setMesEncheresEnCours(boolean mesEncheresEnCours) {
        this.mesEncheresEnCours = mesEncheresEnCours;
    }

    public boolean isMesEncheresRemportees() {
        return mesEncheresRemportees;
    }

    public void setMesEncheresRemportees(boolean mesEncheresRemportees) {
        this.mesEncheresRemportees = mesEncheresRemportees;
    }

    public boolean isMesVentesEnCours() {
        return mesVentesEnCours;
    }

    public void setMesVentesEnCours(boolean mesVentesEnCours) {
        this.mesVentesEnCours = mesVentesEnCours;
    }

    public boolean isVentesNonDebutees() {
        return ventesNonDebutees;
    }

    public void setVentesNonDebutees(boolean ventesNonDebutees) {
        this.ventesNonDebutees = ventesNonDebutees;
    }

    public boolean isVenteTerminee() {
        return venteTerminee;
    }

    public void setVenteTerminee(boolean venteTerminee) {
        this.venteTerminee = venteTerminee;
    }
}
