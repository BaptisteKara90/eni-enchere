package fr.eni.ecole.enchere.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
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

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = "Identifiant ou mot de passe incorrect";
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}

