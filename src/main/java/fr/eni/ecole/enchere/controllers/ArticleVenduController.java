package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.*;
import fr.eni.ecole.enchere.bo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ArticleVenduController {

    private RetraitService retraitService;
    private final CategorieService categorieService;
    private ArticleVenduService articleVenduService;
    private UtilisateurService utilisateurService;
    private EnchereService enchereService;
    private ImageService imageService;
    private static final String UPLOAD_DIR = "D:/uploads/";

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService, UtilisateurService utilisateurService, RetraitService retraitService, EnchereService enchereService, ImageService imageService) {
        super();
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
        this.retraitService = retraitService;
        this.enchereService = enchereService;
        this.imageService = imageService;
    }

    @GetMapping("/encheres")
    public String articles(Model model) {

        Search emptyFilter = new Search();
        model.addAttribute("search", emptyFilter);

        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);

        List<ArticleVendu> listArticles = articleVenduService.getArticleVendu();

        List<Image> listImage = imageService.getImages();
        if(listImage != null && listImage.size() > 0) {
            model.addAttribute("images", listImage);
        }

        for (ArticleVendu articleVendu : listArticles) {
            Enchere enchere = enchereService.getEnchere(articleVendu.getNo_article());
            if (enchere != null) {
                articleVendu.setPrix_initial(enchere.getMontant_enchere());
            }
        }
        model.addAttribute("articles", listArticles);

        return "encheres";
    }

    @GetMapping("/article")
    public String showDetails(@RequestParam("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur CurrentUtilisateur = (Utilisateur) auth.getPrincipal();
        CurrentUtilisateur = utilisateurService.getUtilisateur(CurrentUtilisateur.getNo_utilisateur());
        ArticleVendu article = articleVenduService.getArticleVendu(id);
        Enchere enchere = enchereService.getEnchere(id);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(article.getUtilisateur().getNo_utilisateur());
        Retrait retrait = retraitService.getRetrait(article.getNo_article());
        Image image = imageService.findImageByArticle(article.getNo_article());

        if (image != null){
            model.addAttribute("image", image);
        }

        if (enchere != null) {
            Utilisateur enchereUtilisateur = utilisateurService.getUtilisateur(enchere.getNo_utilisateur());
            model.addAttribute("enchereUtilisateur", enchereUtilisateur);
            model.addAttribute("enchere", enchere);
        } else {
            model.addAttribute("enchereUtilisateur", null);
            Enchere enchere_vide = new Enchere();
            enchere_vide.setMontant_enchere(article.getPrix_initial());
            model.addAttribute("enchere", enchere_vide);
        }

        model.addAttribute("retrait", retrait);
        model.addAttribute("article", article);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("CurrentUtilisateur", CurrentUtilisateur);

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
    public String addArticle(@ModelAttribute("articleVendu") ArticleVendu articleVendu, @ModelAttribute("retrait") Retrait retrait, @RequestParam("image") MultipartFile image) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        articleVendu.setUtilisateur(utilisateur);
        articleVenduService.addArticleVendu(articleVendu);

        retrait.setNo_article(articleVendu.getNo_article());
        retraitService.addRetrait(retrait);

        if (image != null && !image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            File uploadDir = new File(UPLOAD_DIR);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = UPLOAD_DIR + fileName;
            File file = new File(filePath);

            try {
                image.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Image newImage = new Image();
            newImage.setNo_article(articleVendu.getNo_article());
            newImage.setImage("/uploads/" + fileName);
            imageService.addImage(newImage);
        }

        return "redirect:/article?id=" + articleVendu.getNo_article();
    }

    @GetMapping("/update-article")
    public String updateArticle(@RequestParam("id") int id, Model model){
        ArticleVendu articleVendu = articleVenduService.getArticleVendu(id);

        Retrait retrait = retraitService.getRetrait(articleVendu.getNo_article());

        List<Categorie> categories = categorieService.getAllCategories();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        utilisateur = utilisateurService.getUtilisateur(utilisateur.getNo_utilisateur());


        model.addAttribute("article", articleVendu);
        model.addAttribute("retrait", retrait);
        model.addAttribute("categories", categories);
        model.addAttribute("utilisateur", utilisateur);
        return "update-article";
    }

    @PostMapping("/update-article")
    public String updateArticle(@ModelAttribute("article") ArticleVendu articleVendu, @ModelAttribute("retrait") Retrait retrait){
        articleVenduService.updateArticle(articleVendu);
        retraitService.updateRetrait(retrait);
        return "redirect:/article?id=" + articleVendu.getNo_article();
    }


    @GetMapping("/delete-article")
    public String deleteArticle(@RequestParam("id") int id){
        retraitService.deleteRetrait(id);
        Image image = imageService.findImageByArticle(id);
        if (image != null) {
            imageService.deleteImageByArticle(id);
        }
        articleVenduService.deleteArticleVendu(id);

        return "redirect:/encheres";
    }

}


