package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bll.SearchService;
import fr.eni.ecole.enchere.bll.UtilisateurService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Search;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService;
    private CategorieService categorieService;
    private UtilisateurService utilisateurService;

    public SearchController(SearchService searchService, CategorieService categorieService, UtilisateurService utilisateurService) {
        this.searchService = searchService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/search-article")
    public String searchArticle(@ModelAttribute("search") Search search, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUtilisateur = (Utilisateur) auth.getPrincipal();

        Search emptyFilter = new Search();
        model.addAttribute("search", emptyFilter);

        List<ArticleVendu> searchResult = searchService.getArticlesWithFilter(search, currentUtilisateur.getNo_utilisateur());

        model.addAttribute("articles", searchResult);

        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);

        return "search-article";
    }
}
