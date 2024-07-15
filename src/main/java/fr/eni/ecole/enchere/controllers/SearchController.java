package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.ArticleVenduService;
import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;

    public SearchController(ArticleVenduService articleVenduService, CategorieService categorieService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
    }

    @PostMapping("/search-article")
    public String searchArticle(
            @RequestParam("motCle") String motCle,
            @RequestParam("categorie") String categorie,
            @RequestParam("encheresOuvertes") String encheresOuvertes,
            @RequestParam("mesEncheresEnCours") String mesEncheresEnCours,
            @RequestParam("mesEncheresRemportees") String mesEncheresRemportees,
            @RequestParam("mesVentesEnCours") String mesVentesEnCours,
            @RequestParam("ventesNonDebutees") String ventesNonDebutees,
            @RequestParam("venteTerminee") String venteTerminee,
            Model model
    ){

        List<ArticleVendu> searchResult = articleVenduService.getArticlesWithFilter(
                motCle,
                Integer.parseInt(categorie),
                encheresOuvertes,
                mesEncheresEnCours,
                mesEncheresRemportees,
                mesVentesEnCours,
                ventesNonDebutees,
                venteTerminee);
        model.addAttribute("articles", searchResult);

        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);

        return "search-article";
    }
}
