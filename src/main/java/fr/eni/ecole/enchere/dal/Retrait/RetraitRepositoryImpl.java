package fr.eni.ecole.enchere.dal.Retrait;

import fr.eni.ecole.enchere.bo.Retrait;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RetraitRepositoryImpl implements RetraitRepository{
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RetraitRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Retrait> findAll() {

        String sql = "select * from retraits";

        List<Retrait> list = jdbcTemplate.query(sql, new RetraitRowMapper());

        return list;
    }

    @Override
    public Retrait findById(int id) {
        String sql = "select * from retraits where no_article = :id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        Retrait retrait = namedParameterJdbcTemplate.queryForObject(sql,map,new RetraitRowMapper());
        return retrait;
    }

    @Override
    public void save(Retrait retrait) {
        String sql = "insert into retraits (no_article, rue, code_postal, ville) values (:no_article, :rue, :codePostal, :ville)";

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("no_article", retrait.getNo_article());
        map.addValue("rue", retrait.getRue());
        map.addValue("codePostal", retrait.getCode_postal());
        map.addValue("ville", retrait.getVille());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from retraits where no_article = :no_article";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", id);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void update(Retrait retrait) {
        String sql = "update retraits set rue = :rue, code_postal = :code_postal, ville = :ville where no_article = :no_article";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", retrait.getNo_article());
        map.addValue("rue", retrait.getRue());
        map.addValue("code_postal", retrait.getCode_postal());
        map.addValue("ville", retrait.getVille());
        namedParameterJdbcTemplate.update(sql, map);
    }
}
