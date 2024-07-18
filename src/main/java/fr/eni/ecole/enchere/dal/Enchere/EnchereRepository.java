package fr.eni.ecole.enchere.dal.Enchere;

import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.dal.Dao;

import java.util.List;

public interface EnchereRepository extends Dao<Enchere> {
    List<Enchere> findByIdUtilisateur(int idUtilisateur);
    List<Enchere> findByIdArticle(int idArticle);
    void deleteByIdUtilisateur(int idUtilisateur);
}
