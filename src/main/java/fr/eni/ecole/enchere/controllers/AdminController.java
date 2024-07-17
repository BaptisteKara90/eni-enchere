package fr.eni.ecole.enchere.controllers;


import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bll.UtilisateurService;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private UtilisateurService utilisateurService;
    private CategorieService categorieService;

    public AdminController(UtilisateurService utilisateurService, CategorieService categorieService) {
        this.utilisateurService = utilisateurService;
        this.categorieService = categorieService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        List<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();
        List<Categorie> categories = categorieService.getAllCategories();

        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("categories", categories);

        return "administrator";
    }
}
