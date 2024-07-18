package fr.eni.ecole.enchere.dal.image;

import fr.eni.ecole.enchere.bo.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();

        image.setId_image(rs.getInt("id_image"));
        image.setNo_article(rs.getInt("no_article"));
        image.setImage(rs.getString("image"));

        return image;
    }
}
