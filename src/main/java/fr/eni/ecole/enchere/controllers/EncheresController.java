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
import java.util.List;

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
        // Récupérer l'utilisateur actuellement connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUtilisateur = (Utilisateur) auth.getPrincipal();

        // Récupérer l'utilisateur et l'article liés à l'enchère
        Utilisateur utilisateur = utilisateurService.getUtilisateur(enchere.getNo_utilisateur());
        ArticleVendu article = articleVenduService.getArticleVendu(enchere.getNo_article());

        // Vérifier si l'utilisateur est le propriétaire de l'article
        if (currentUtilisateur.getNo_utilisateur() == article.getUtilisateur().getNo_utilisateur()) {
            String erreur = "Vous ne pouvez pas enchérir sur votre propre objet ! (petit(e) coquin(e) va!)";
            redirectAttributes.addFlashAttribute("erreur", erreur);
            return "redirect:/article?id=" + article.getNo_article();
        }

        // Vérifier si l'utilisateur a suffisamment de crédit pour l'enchère
        if (utilisateur.getCredit() >= enchere.getMontant_enchere()) {
            // Vérifier si l'enchère est supérieure à l'enchère actuelle
            List<Enchere> enchereArticle = enchereService.getEnchereByArticle(enchere.getNo_article());
            if (!enchereArticle.isEmpty()) {
                Enchere derniereEnchere = enchereArticle.get(enchereArticle.size() - 1);
                if (enchere.getMontant_enchere() <= derniereEnchere.getMontant_enchere()) {
                    String erreur = "Vous devez enchérir plus que l'enchère actuelle !";
                    redirectAttributes.addFlashAttribute("erreur", erreur);
                    return "redirect:/article?id=" + article.getNo_article();
                }
            }

            // Ajouter la date de l'enchère et enregistrer l'enchère
            enchere.setDate_enchere(LocalDate.now());
            enchereService.addEnchere(enchere);

            // Mettre à jour le crédit de l'utilisateur enchérisseur
            utilisateur.setCredit(utilisateur.getCredit() - enchere.getMontant_enchere());
            utilisateurService.changeCredit(utilisateur.getNo_utilisateur(), utilisateur.getCredit());

            // Rembourser la dernière enchère perdante
            if (enchereArticle.size() > 1) {
                Enchere derniereEncherePerdante = enchereArticle.get(enchereArticle.size() - 2);
                Utilisateur utilisateurRembourse = utilisateurService.getUtilisateur(derniereEncherePerdante.getNo_utilisateur());
                utilisateurRembourse.setCredit(utilisateurRembourse.getCredit() + derniereEncherePerdante.getMontant_enchere());
                utilisateurService.changeCredit(utilisateurRembourse.getNo_utilisateur(), utilisateurRembourse.getCredit());
            }
        } else {
            // Si l'utilisateur n'a pas suffisamment de crédit, rediriger avec un message d'erreur
            String erreur = "Vous n'avez pas les moyens d'acquérir cet objet !";
            redirectAttributes.addFlashAttribute("erreur", erreur);
        }

        // Rediriger vers la page de l'article après le traitement de l'enchère
        return "redirect:/article?id=" + article.getNo_article();
    }
}
