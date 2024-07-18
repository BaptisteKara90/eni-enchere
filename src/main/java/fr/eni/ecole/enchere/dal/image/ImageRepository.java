package fr.eni.ecole.enchere.dal.image;

import fr.eni.ecole.enchere.bo.Image;
import fr.eni.ecole.enchere.dal.Dao;

public interface ImageRepository extends Dao<Image> {
    Image getImageByArticle(int no_article);
    void deleteImageByArticle(int no_article);
}
