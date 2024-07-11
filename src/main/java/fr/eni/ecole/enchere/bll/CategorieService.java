package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.dal.categorie.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorie(int id) {
        return categorieRepository.findById(id);
    }
}
