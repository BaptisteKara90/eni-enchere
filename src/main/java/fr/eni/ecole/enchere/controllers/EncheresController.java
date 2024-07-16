package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.ArticleVenduService;
import fr.eni.ecole.enchere.bll.EnchereService;
import fr.eni.ecole.enchere.bll.UtilisateurService;
import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class EncheresController {

    private EnchereService enchereService;
    private UtilisateurService utilisateurService;
    private ArticleVenduService articleVenduService;

    public EncheresController(UtilisateurService utilisateurService, EnchereService enchereService, ArticleVenduService articleVenduService) {
        super();
        this.utilisateurService = utilisateurService;
        this.enchereService = enchereService;
        this.articleVenduService = articleVenduService;
    }

    @PostMapping("/add-enchere")
    public String addEnchere(@ModelAttribute("enchere") Enchere enchere, RedirectAttributes redirectAttributes) {
     Utilisateur utilisateur = utilisateurService.getUtilisateur(enchere.getNo_utilisateur());
     ArticleVendu article = articleVenduService.getArticleVendu(enchere.getNo_article());
     if (utilisateur.getCredit() >= enchere.getMontant_enchere()){
         enchere.setDate_enchere(LocalDate.now());
         enchereService.addEnchere(enchere);
        } else{
         String erreur = "Vous avez pas les moyens pour acquérir cette objet !";
         redirectAttributes.addFlashAttribute("erreur", erreur);
     }


     return "redirect:/article?id=" + article.getNo_article();
    }
}
