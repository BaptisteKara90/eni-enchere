package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Search;
import fr.eni.ecole.enchere.dal.search.SearchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

    private SearchRepository searchRepository;
    private EnchereService enchereService;

    public SearchService(SearchRepository searchRepository, EnchereService enchereService) {
        this.searchRepository = searchRepository;
        this.enchereService = enchereService;
    }

    public List<ArticleVendu> getArticlesWithFilter(Search search, int currentUserId) {

        LocalDate currentDate = LocalDate.now();

       // On récupère le resultat de la requête avec prise en compte des 2 premiers champs du formulaire
        List<ArticleVendu> result = searchRepository.searchArticles(search);

        // Si l'utilisateur n'utilise pas de filtres, on envoie le resultat
        if (search.getType().equalsIgnoreCase("")){
            return result;
        }

        //Si l'utilisateur utilise le filtre Achat
        if(search.getType().equals("achat")){
            for (ArticleVendu article : result) {
                if(article.getUtilisateur().getNo_utilisateur() == currentUserId){
                    result.remove(article);
                }
            }
            if(search.isEncheresOuvertes()) {
                for (ArticleVendu article : result) {
                    if (!article.getDate_debut_encheres().isAfter(currentDate) || !article.getDate_fin_encheres().isBefore(currentDate)) {
                        result.remove(article);
                    }
                }
            }
            if(search.isMesEncheresEnCours()) {
                for (ArticleVendu article : result) {

                    List<Enchere> encheresCurrentArticle = enchereService.getEnchereByArticle(article.getNo_article());
                    int currentUserEnchereDetected = 0;

                    for (Enchere enchere : encheresCurrentArticle) {
                        if(enchere.getNo_utilisateur() == currentUserId && enchere.getNo_article() == article.getNo_article()){
                            currentUserEnchereDetected++;
                        }
                    }
                    if (currentUserEnchereDetected <= 0) {
                        result.remove(article);
                    }
                }
            }
        }

        return result;
    }
}
