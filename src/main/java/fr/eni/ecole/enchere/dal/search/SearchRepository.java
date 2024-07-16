package fr.eni.ecole.enchere.dal.search;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Search;
import fr.eni.ecole.enchere.dal.articleVendu.ArticleVenduRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SearchRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<ArticleVendu> searchArticles(Search search) {

        String sql = "SELECT a.no_article AS id_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, " +
                "u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, " +
                "c.no_categorie, c.libelle " +
                "FROM articles_vendus a " +
                "INNER JOIN utilisateurs u ON a.no_utilisateur = u.no_utilisateur " +
                "INNER JOIN categories c ON a.no_categorie = c.no_categorie";

        MapSqlParameterSource map = new MapSqlParameterSource();

        // Construire la clause WHERE en fonction des paramètres
        if ((search.getMotCle() != null && !search.getMotCle().isEmpty()) && search.getCategorie() > 0) {
            sql += " WHERE a.nom_article LIKE :motCle AND a.no_categorie = :idCategorie";
            map.addValue("motCle", "%" + search.getMotCle() + "%");
            map.addValue("idCategorie", search.getCategorie());
        } else if ((search.getMotCle() != null && !search.getMotCle().isEmpty()) && search.getCategorie() == 0) {
            sql += " WHERE a.nom_article LIKE :motCle";
            map.addValue("motCle", "%" + search.getMotCle() + "%");
        } else if ((search.getMotCle() == null || search.getMotCle().isEmpty()) && search.getCategorie() > 0) {
            sql += " WHERE a.no_categorie = :idCategorie";
            map.addValue("idCategorie", search.getCategorie());
        }

        // Exécuter la requête
        List<ArticleVendu> list = namedParameterJdbcTemplate.query(sql, map, new ArticleVenduRowMapper());
        return list;
    }
}
