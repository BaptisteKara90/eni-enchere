package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bll.EnchereService;
import fr.eni.ecole.enchere.bll.SearchService;
import fr.eni.ecole.enchere.bo.*;
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
    private EnchereService enchereService;

    public SearchController(SearchService searchService, CategorieService categorieService, EnchereService enchereService) {
        this.searchService = searchService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
    }

    @PostMapping("/search-article")
    public String searchArticle(@ModelAttribute("search") Search search, Model model){

        Search emptyFilter = new Search();
        model.addAttribute("search", emptyFilter);

        List<ArticleVendu> searchResult = searchService.getArticlesWithFilter(search);
        for (ArticleVendu articleVendu : searchResult) {
            Enchere enchere = enchereService.getEnchere(articleVendu.getNo_article());
            if (enchere != null) {
                articleVendu.setPrix_initial(enchere.getMontant_enchere());
            }
        }
        model.addAttribute("articles", searchResult);

        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);

        return "search-article";
    }
}
