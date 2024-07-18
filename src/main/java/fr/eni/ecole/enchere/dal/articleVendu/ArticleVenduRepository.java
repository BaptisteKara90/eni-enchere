package fr.eni.ecole.enchere.dal.articleVendu;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Search;
import fr.eni.ecole.enchere.dal.Dao;

import java.util.List;

public interface ArticleVenduRepository extends Dao<ArticleVendu> {

    void deleteByIdUtilisateur(int idUtilisateur);
}
