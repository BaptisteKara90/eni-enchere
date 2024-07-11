package fr.eni.ecole.enchere.dal.categorie;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieRepositoryImpl implements CategorieRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategorieRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Categorie> findAll() {
        String sql = "select * from categories";
       List<Categorie> list =  jdbcTemplate.query(sql, new CategorieRowMapper());
        return list;
    }

    @Override
    public Categorie findById(int id) {
        String sql = "select * from categories where no_categorie = :id";
        MapSqlParameterSource map = new MapSqlParameterSource("id", id);
        map.addValue("id", id);

        Categorie categorie = namedParameterJdbcTemplate.queryForObject(sql, map, new CategorieRowMapper());

        return categorie;
    }

    @Override
    public void save(Categorie categorie) {

        GeneratedKeyHolder key = new GeneratedKeyHolder();

        String sql = "insert into categories (libelle) values (:libelle)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("libelle", categorie.getLibelle());

        namedParameterJdbcTemplate.update(sql, map, key);

        if(key.getKey() != null){
            categorie.setNo_categorie(key.getKey().intValue());
        }
    }

    @Override
    public void deleteById(int id) {
        //TODO
    }

    @Override
    public void update(Categorie categorie) {
    //TODO
    }
}
