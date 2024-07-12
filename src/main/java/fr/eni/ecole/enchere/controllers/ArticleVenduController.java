package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.ArticleVenduService;
import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bll.UtilisateurService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleVenduController {

    private final CategorieService categorieService;
    private ArticleVenduService articleVenduService;
    private UtilisateurService utilisateurService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService, UtilisateurService utilisateurService) {
        super();
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/encheres")
    public String articles(Model model) {
        List<ArticleVendu>listArticles = articleVenduService.getArticleVendu();
        model.addAttribute("articles", listArticles);
        return "encheres";
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        utilisateur = utilisateurService.getUtilisateur(utilisateur.getNo_utilisateur());

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("categories", listCat);
        model.addAttribute("articleVendu", articleVendu);
        return "add-article";
    }

    @PostMapping("/add-article")
    public String addArticle(@ModelAttribute("articleVendu") ArticleVendu articleVendu){
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleVendu.setUtilisateur(utilisateur);
        articleVenduService.addArticleVendu(articleVendu);

        return "article?id=" + articleVendu.getNo_article();
    }

    //TODO ajouter un nouvel article
    //TODO supprimer un article
    //TODO modifier un article
}


