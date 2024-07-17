package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Search;
import fr.eni.ecole.enchere.dal.search.SearchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<ArticleVendu> toRemove = new ArrayList<>();

        // Si l'utilisateur n'utilise pas de filtres, on envoie le resultat
        if (search.getType().equalsIgnoreCase("")){
            return result;
        }

        //Si l'utilisateur utilise le filtre Achat
        if(search.getType().equals("achat")){

            Stream<ArticleVendu> streamFilterResultAchat = result.stream();

            result = streamFilterResultAchat
                    .filter(a -> a.getUtilisateur().getNo_utilisateur() != currentUserId)
                    .collect(Collectors.toList());

            if(search.isEncheresOuvertes()) {

                Stream<ArticleVendu> streamFilterResultAchat1 = result.stream();
                result = streamFilterResultAchat1
                        .filter(a -> (a.getDate_debut_encheres().isBefore(currentDate) && a.getDate_fin_encheres().isAfter(currentDate)))
                        .collect(Collectors.toList());

            }
            if(search.isMesEncheresEnCours()) {

                Stream<ArticleVendu> streamFilterResultAchat2 = result.stream();
                result = streamFilterResultAchat2
                        .filter(a -> {
                            List<Enchere> encheresCurrentArticle = enchereService.getEnchereByArticle(a.getNo_article());
                            Stream<Enchere> enchereStream = encheresCurrentArticle.stream();
                            long currentUserEnchereDetected = enchereStream.filter(e -> e.getNo_utilisateur() == currentUserId).count();
                            return currentUserEnchereDetected > 0;
                        })
                        .collect(Collectors.toList());
            }
            if(search.isMesEncheresRemportees()) {

                Stream<ArticleVendu> streamFilterResultAchat3 = result.stream();
                result = streamFilterResultAchat3
                        .filter(a -> {
                            if(enchereService.getEnchere(a.getNo_article()) != null && a.getDate_fin_encheres().isBefore(currentDate) && enchereService.getEnchere(a.getNo_article()).getNo_utilisateur() == currentUserId){
                                return true;
                            } else {
                                return false;
                            }
                        })
                        .collect(Collectors.toList());
            }
        }

        //Si l'utilisateur utilise le filtre Achat
        if(search.getType().equals("vente")){

            Stream<ArticleVendu> streamFilterResultVente = result.stream();
            result = streamFilterResultVente.filter(a -> a.getUtilisateur().getNo_utilisateur() == currentUserId).collect(Collectors.toList());

            if(search.isMesVentesEnCours()) {
                Stream<ArticleVendu> streamFilterResultVente1 = result.stream();
                result = streamFilterResultVente1
                        .filter(a -> (a.getDate_debut_encheres().isBefore(currentDate) && a.getDate_fin_encheres().isAfter(currentDate)))
                        .collect(Collectors.toList());
            }

            if(search.isVentesNonDebutees()) {
                Stream<ArticleVendu> streamFilterResultVente2 = result.stream();
                result = streamFilterResultVente2.
                        filter(a -> a.getDate_debut_encheres().isAfter(currentDate)).
                        collect(Collectors.toList());
            }

            if(search.isVenteTerminee()) {
                Stream<ArticleVendu> streamFilterResultVente3 = result.stream();
                result = streamFilterResultVente3
                        .filter(a -> a.getDate_fin_encheres().isBefore(currentDate))
                        .collect(Collectors.toList());
            }
        }

        return result;
    }
}
