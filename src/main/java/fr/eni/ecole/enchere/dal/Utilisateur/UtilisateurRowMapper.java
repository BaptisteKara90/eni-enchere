package fr.eni.ecole.enchere.dal.Utilisateur;

import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {

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
        utilisateur.setActive(rs.getInt("active"));

        return utilisateur;
    }
}
