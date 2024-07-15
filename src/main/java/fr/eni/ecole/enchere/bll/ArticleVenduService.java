package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.dal.articleVendu.ArticleVenduRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduService {

    private ArticleVenduRepository articleVenduRepository;

    public ArticleVenduService(ArticleVenduRepository articleVenduRepository) {
        this.articleVenduRepository = articleVenduRepository;
    }

    public List<ArticleVendu> getArticleVendu() {
        return articleVenduRepository.findAll();
    }

    public ArticleVendu getArticleVendu(int id) {
        return articleVenduRepository.findById(id);
    }

    public void addArticleVendu(ArticleVendu articleVendu) {
        articleVenduRepository.save(articleVendu);
    }

    public List<ArticleVendu> getArticlesWithFilter(String motCle,
                                                    int idCategorie,
                                                    String encheresOuvertes,
                                                    String mesEncheresEnCours,
                                                    String mesEncheresRemportees,
                                                    String mesVentesEnCours,
                                                    String ventesNonDebutees,
                                                    String venteTerminee) {

        return articleVenduRepository.searchArticles(motCle, idCategorie);
    }
}
