package fr.eni.ecole.enchere.bo;

public class Search {

    private String motCle;
    private int categorie;
    private String type = "";
    private String encheresOuvertes = "";
    private String mesEncheresEnCours = "";
    private String mesEncheresRemportees = "";
    private String mesVentesEnCours = "";
    private String ventesNonDebutees = "";
    private String venteTerminee = "";

    public Search(String motCle, int categorie, String type, String encheresOuvertes, String mesEncheresEnCours, String mesEncheresRemportees, String mesVentesEnCours, String ventesNonDebutees, String venteTerminee) {
        this.motCle = motCle;
        this.categorie = categorie;
        this.type = type;
        this.encheresOuvertes = encheresOuvertes;
        this.mesEncheresEnCours = mesEncheresEnCours;
        this.mesEncheresRemportees = mesEncheresRemportees;
        this.mesVentesEnCours = mesVentesEnCours;
        this.ventesNonDebutees = ventesNonDebutees;
        this.venteTerminee = venteTerminee;
    }

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

    public String getEncheresOuvertes() {
        return encheresOuvertes;
    }

    public void setEncheresOuvertes(String encheresOuvertes) {
        this.encheresOuvertes = encheresOuvertes;
    }

    public String getMesEncheresEnCours() {
        return mesEncheresEnCours;
    }

    public void setMesEncheresEnCours(String mesEncheresEnCours) {
        this.mesEncheresEnCours = mesEncheresEnCours;
    }

    public String getMesEncheresRemportees() {
        return mesEncheresRemportees;
    }

    public void setMesEncheresRemportees(String mesEncheresRemportees) {
        this.mesEncheresRemportees = mesEncheresRemportees;
    }

    public String getMesVentesEnCours() {
        return mesVentesEnCours;
    }

    public void setMesVentesEnCours(String mesVentesEnCours) {
        this.mesVentesEnCours = mesVentesEnCours;
    }

    public String getVentesNonDebutees() {
        return ventesNonDebutees;
    }

    public void setVentesNonDebutees(String ventesNonDebutees) {
        this.ventesNonDebutees = ventesNonDebutees;
    }

    public String getVenteTerminee() {
        return venteTerminee;
    }

    public void setVenteTerminee(String venteTerminee) {
        this.venteTerminee = venteTerminee;
    }
}
