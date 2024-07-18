package fr.eni.ecole.enchere.dal.image;

import fr.eni.ecole.enchere.bo.Image;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl implements ImageRepository{

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ImageRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Image> findAll() {
        String sql = "select * from image";
        List<Image> list = jdbcTemplate.query(sql, new ImageRowMapper());
        return list;
    }

    @Override
    public Image findById(int id) {
        String sql = "select * from image where id_image = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        Image image = jdbcTemplate.queryForObject(sql, new ImageRowMapper(), id);
        return image;
    }

    @Override
    public void save(Image image) {
        String sql = "insert into image (id_image, no_article, image) values (:id_image, :no_article, :image)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id_image", image.getId_image());
        map.addValue("no_article", image.getNo_article());
        map.addValue("image", image.getImage());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from image where id_image = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void update(Image image) {

    }

    @Override
    public Image getImageByArticle(int no_article) {
        String sql = "select * from image where no_article = :no_article";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", no_article);

        try {
            Image image = namedParameterJdbcTemplate.queryForObject(sql, map, new ImageRowMapper());
            return image;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteImageByArticle(int no_article) {
        String sql = "delete from image where no_article = :no_article";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", no_article);
        namedParameterJdbcTemplate.update(sql, map);
    }
}
