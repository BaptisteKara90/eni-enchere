package fr.eni.ecole.enchere.dal.articleVendu;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

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
        return List.of();
    }

    @Override
    public ArticleVendu findById(int id) {
        return null;
    }

    @Override
    public void save(ArticleVendu articleVendu) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(ArticleVendu articleVendu) {

    }
}
