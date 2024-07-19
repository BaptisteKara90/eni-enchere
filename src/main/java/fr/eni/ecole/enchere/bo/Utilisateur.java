package fr.eni.ecole.enchere.bo;

import fr.eni.ecole.enchere.bll.UtilisateurService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Utilisateur implements UserDetails {

    private int no_utilisateur;
    @Pattern(regexp ="^[a-zA-Z0-9]+$", message = "Merci de ne saisir que des lettres et des chiffres")
    private String pseudo;
    private String nom;
    private String prenom;
    @Email(message = "Merci de saisir une adresse e-mail valide")
    private String email;
    private String telephone;
    private String rue;
    private String code_postal;
    private String ville;
    @Pattern(regexp ="^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,}$", message = "Le mot de passe doit contenir au mininum 8 caractères, une majuscule, un chiffre et un caractère spécial")
    private String mot_de_passe;
    private int credit;
    private boolean administrateur;
    private UtilisateurService utilisateurService;
    private int active;

    public Utilisateur(int no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String code_postal, String ville, String mot_de_passe, int credit, boolean administrateur, UtilisateurService utilisateurService, int active) {
        this.no_utilisateur = no_utilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.mot_de_passe = mot_de_passe;
        this.credit = credit;
        this.administrateur = administrateur;
        this.utilisateurService = utilisateurService;
        this.active = active;
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String code_postal, String ville, String mot_de_passe, int credit, boolean administrateur, UtilisateurService utilisateurService, int active) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.mot_de_passe = mot_de_passe;
        this.credit = credit;
        this.administrateur = administrateur;
        this.utilisateurService = utilisateurService;
        this.active = active;
    }

    public Utilisateur() {
    }

    public int getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    //ToString
    @Override
    public String toString() {
        return "Utilisateur{" +
                "no_utilisateur=" + no_utilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", rue=" + rue +
                ", code_postal=" + code_postal +
                ", ville='" + ville + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of();
    }

    @Override
    public String getPassword() {
        return mot_de_passe;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
