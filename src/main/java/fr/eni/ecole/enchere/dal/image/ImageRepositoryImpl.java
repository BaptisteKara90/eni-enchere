package fr.eni.ecole.enchere.dal.image;

import fr.eni.ecole.enchere.bo.Image;
import org.springframework.jdbc.core.JdbcTemplate;
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

        return List.of();
    }

    @Override
    public Image findById(int id) {
        return null;
    }

    @Override
    public void save(Image image) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Image image) {

    }
}
