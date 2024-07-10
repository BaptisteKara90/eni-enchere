package fr.eni.ecole.enchere.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {

    @GetMapping("/signin")
    public String utilisateur() {
        return "signin";
    }
}
