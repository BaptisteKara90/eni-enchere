package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.ArticleVenduService;
import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bll.RetraitService;
import fr.eni.ecole.enchere.bll.UtilisateurService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Retrait;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Retrait.RetraitRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArticleVenduController {

    private RetraitService retraitService;
    private final CategorieService categorieService;
    private ArticleVenduService articleVenduService;
    private UtilisateurService utilisateurService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService, UtilisateurService utilisateurService, RetraitService retraitService) {
        super();
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
        this.retraitService = retraitService;
    }

    @GetMapping("/encheres")
    public String articles(Model model) {


        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);

        List<ArticleVendu> listArticles = articleVenduService.getArticleVendu();
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
        Retrait retrait = new Retrait();

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("categories", listCat);
        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("retrait", retrait);
        return "add-article";
    }

    @PostMapping("/add-article")
    public String addArticle(@ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("retrait") Retrait retrait){
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleVendu.setUtilisateur(utilisateur);

        articleVenduService.addArticleVendu(articleVendu);
        retrait.setNo_article(articleVendu.getNo_article());
        retraitService.addRetrait(retrait);

        return "redirect:article?id=" + articleVendu.getNo_article();
    }

    @PostMapping("/search-article")
    public String searchArticle(@RequestParam("motCle") String motCle,@RequestParam("categorie") String categorie, Model model){

        List<ArticleVendu> searchResult = articleVenduService.getArticlesWithFilter(motCle, Integer.parseInt(categorie));
        model.addAttribute("articles", searchResult);

        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);

        return "search-article";
    }

    //TODO ajouter un nouvel article
    //TODO supprimer un article
    //TODO modifier un article
}


