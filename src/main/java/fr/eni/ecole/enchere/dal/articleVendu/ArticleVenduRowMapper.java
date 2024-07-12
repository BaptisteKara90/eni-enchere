package fr.eni.ecole.enchere.dal.articleVendu;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {
    @Override
    public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {

        ArticleVendu articleVendu = new ArticleVendu();

        articleVendu.setNo_article(rs.getInt("id_article"));
        articleVendu.setNom_article(rs.getString("nom_article"));
        articleVendu.setDescription(rs.getString("description"));
        Date debutEnchere = rs.getDate("date_debut_encheres");
        articleVendu.setDate_debut_encheres(debutEnchere.toLocalDate());
        Date finEnchere = rs.getDate("date_fin_encheres");
        articleVendu.setDate_fin_encheres(finEnchere.toLocalDate());
        articleVendu.setPrix_initial(rs.getLong("prix_initial"));
        articleVendu.setPrix_vente(rs.getLong("prix_vente"));

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
        utilisateur.setPseudo(rs.getString("pseudo"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setTelephone(rs.getString("telephone"));
        utilisateur.setRue(rs.getString("rue"));
        utilisateur.setCode_postal(rs.getString("code_postal"));
        utilisateur.setVille(rs.getString("ville"));
        utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
        utilisateur.setCredit(rs.getInt("credit"));
        utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

        articleVendu.setUtilisateur(utilisateur);

        Categorie categorie = new Categorie();
        categorie.setNo_categorie(rs.getInt("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));

        articleVendu.setCategorie(categorie);


        return articleVendu;
    }
}
