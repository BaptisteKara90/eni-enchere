package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.*;
import fr.eni.ecole.enchere.bo.*;
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
    private EnchereService enchereService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService, UtilisateurService utilisateurService, RetraitService retraitService, EnchereService enchereService) {
        super();
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
        this.retraitService = retraitService;
        this.enchereService = enchereService;
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
        Enchere enchere = enchereService.getEnchere(id);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);

        Utilisateur enchereUtilisateur = utilisateurService.getUtilisateur(enchere.getNo_utilisateur());


        model.addAttribute("article", article);
        model.addAttribute("enchere", enchere);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("enchereUtilisateur", enchereUtilisateur);
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
        Enchere enchere = new Enchere();

        articleVenduService.addArticleVendu(articleVendu);
        retrait.setNo_article(articleVendu.getNo_article());
        retraitService.addRetrait(retrait);

        enchere.setNo_article(articleVendu.getNo_article());
        enchere.setNo_utilisateur(utilisateur.getNo_utilisateur());
        enchere.setDate_enchere(articleVendu.getDate_debut_encheres());
        enchere.setMontant_enchere(articleVendu.getPrix_initial());

        enchereService.addEnchere(enchere);

        return "redirect:/article?id=" + articleVendu.getNo_article();
    }

    //TODO ajouter un nouvel article
    //TODO supprimer un article
    //TODO modifier un article
}


