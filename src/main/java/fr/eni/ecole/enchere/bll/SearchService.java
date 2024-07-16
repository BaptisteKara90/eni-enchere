package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Search;
import fr.eni.ecole.enchere.dal.search.SearchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

    private SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
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
        if(search.getType().equalsIgnoreCase("achat")){
            for (ArticleVendu article : result) {
                if(article.getUtilisateur().getNo_utilisateur() == currentUserId){
                    result.remove(article);
                }
                if(search.getEncheresOuvertes() == "1") {
                    if (article.getDate_debut_encheres().isAfter(currentDate) == true || article.getDate_fin_encheres().isBefore(currentDate) == true) {
                        result.remove(article);
                    }
                }
                if(search.getMesEncheresEnCours() == "1") {

                }
            }
        }

        return result;
    }
}
