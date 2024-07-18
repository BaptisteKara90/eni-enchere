package fr.eni.ecole.enchere.dal.articleVendu;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Search;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

@Repository
public class ArticleVenduRepositoryImpl implements ArticleVenduRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ArticleVenduRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<ArticleVendu> findAll() {
        String sql = "select a.no_article AS id_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, c.no_categorie, c.libelle FROM articles_vendus a INNER JOIN utilisateurs u ON a.no_utilisateur = u.no_utilisateur INNER JOIN categories c ON a.no_categorie = c.no_categorie";
        List<ArticleVendu> list = jdbcTemplate.query(sql, new ArticleVenduRowMapper());
        return list;
    }

    @Override
    public ArticleVendu findById(int id) {
        String sql = "select a.no_article AS id_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit, u.administrateur, c.no_categorie, c.libelle FROM articles_vendus a INNER JOIN utilisateurs u ON a.no_utilisateur = u.no_utilisateur INNER JOIN categories c ON a.no_categorie = c.no_categorie WHERE a.no_article = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        ArticleVendu articleVendu = namedParameterJdbcTemplate.queryForObject(sql, map, new ArticleVenduRowMapper());
        return articleVendu;
    }

    @Override
    public void save(ArticleVendu articleVendu) {

        GeneratedKeyHolder key = new GeneratedKeyHolder();

        String sql = "insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nom_article", articleVendu.getNom_article());
        map.addValue("description", articleVendu.getDescription());
        map.addValue("date_debut_encheres", articleVendu.getDate_debut_encheres());
        map.addValue("date_fin_encheres", articleVendu.getDate_fin_encheres());
        map.addValue("prix_initial", articleVendu.getPrix_initial());
        map.addValue("prix_vente", articleVendu.getPrix_vente());
        map.addValue("no_utilisateur", articleVendu.getUtilisateur().getNo_utilisateur());
        map.addValue("no_categorie", articleVendu.getCategorie().getNo_categorie());

        namedParameterJdbcTemplate.update(sql, map, key);

        if(key.getKey() != null) {
            articleVendu.setNo_article(key.getKey().intValue());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from articles_vendus where no_article = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void update(ArticleVendu articleVendu) {
        String sql = "Update articles_vendus set nom_article = :nom_article, description = :description, no_categorie = :no_categorie where no_article = :no_article";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", articleVendu.getNo_article());
        map.addValue("nom_article", articleVendu.getNom_article());
        map.addValue("description", articleVendu.getDescription());
        map.addValue("no_categorie", articleVendu.getCategorie().getNo_categorie());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteByIdUtilisateur(int idUtilisateur) {
            String sql = "delete from articles_vendus where no_utilisateur = :id";

            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("id", idUtilisateur);
            namedParameterJdbcTemplate.update(sql, map);
    }
}
