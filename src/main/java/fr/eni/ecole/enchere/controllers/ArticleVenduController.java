package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.utilisateur.ArticleVenduService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleVenduController {

    private ArticleVenduService articleVenduService;

    public ArticleVenduController(ArticleVenduService articleVenduService) {
        super();
        this.articleVenduService = articleVenduService;
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
}


