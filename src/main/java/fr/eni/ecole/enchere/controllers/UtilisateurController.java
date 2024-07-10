package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.utilisateur.UtilisateurService;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtilisateurController {

   private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        super();
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String utilisateur(Model model) {
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "register";
    }

    @PostMapping("/register")
    public String signin(@ModelAttribute("utilisateur") Utilisateur utilisateur) {

        utilisateurService.registerUtilisateur(utilisateur);
        return "redirect:/login";
    }
}
