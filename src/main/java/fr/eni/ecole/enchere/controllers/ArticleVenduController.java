package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.ArticleVenduService;
import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleVenduController {

    private final CategorieService categorieService;
    private ArticleVenduService articleVenduService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService) {
        super();
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        List<ArticleVendu>listArticles = articleVenduService.getArticleVendu();
        model.addAttribute("articles", listArticles);
        return "articles";
    }

    @GetMapping("/article")
    public String showDetails(@RequestParam("id") int id, Model model){
        ArticleVendu article = articleVenduService.getArticleVendu(id);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/add-article")
    public String addArticle(Model model){
        ArticleVendu articleVendu = new ArticleVendu();
        List<Categorie> listCat = categorieService.getAllCategories();

        model.addAttribute("categories", listCat);
        model.addAttribute("articleVendu", articleVendu);
        return "add-article";
    }


    //TODO ajouter un nouvel article
    //TODO supprimer un article
    //TODO modifier un article
}


