package fr.eni.ecole.enchere.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

   @GetMapping("/login")
   public String login( @RequestParam(value = "sessionExpired", required = false) String sessionExpired,
                        Model model) {

       if (sessionExpired != null) {
           model.addAttribute("sessionExpired", true);
       }
       return "login";
    }
}

