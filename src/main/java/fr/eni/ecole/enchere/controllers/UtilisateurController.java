package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.utilisateur.UtilisateurService;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtilisateurController {

    UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        super();
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/signin")
    public String utilisateur() {
        return "signin";
    }

    @PostMapping("/addUser")
    public String signin(@ModelAttribute("utilisateur") Utilisateur utilisateur) {

        utilisateurService.registerUtilisateur(utilisateur);
        return "signin";
    }
}
