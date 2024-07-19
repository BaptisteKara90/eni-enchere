package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.UtilisateurService;
import fr.eni.ecole.enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String signin(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
           return "register";
        }

        utilisateurService.registerUtilisateur(utilisateur);
        return "redirect:/login";

    }

    @GetMapping("/my-profile")
    public String myAccount(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        Utilisateur currentUtilisateur = utilisateurService.getUtilisateur(utilisateur.getNo_utilisateur());

        model.addAttribute("userData", currentUtilisateur);

        return "my-profile";
    }

    @GetMapping("/profile")
    public String userProfile(@RequestParam int id, Model model) {

        Utilisateur currentUtilisateur = utilisateurService.getUtilisateur(id);

        model.addAttribute("userData", currentUtilisateur);

        return "user-profile";
    }

    @GetMapping("/profile/update")
    public String getUpdateProfileForm(@RequestParam int id, Model model) {

        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);

        model.addAttribute("userData", utilisateur);

        return "update-profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute("userData") Utilisateur utilisateur, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "update-profile";
        }

        utilisateurService.updateUtilisateur(utilisateur);

        return "redirect:/my-profile";
    }

    @GetMapping("/profile/delete")
    public String deleteProfile(@RequestParam int id) {

        utilisateurService.deleteUtilisateur(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur principal = (Utilisateur) auth.getPrincipal();

        if (principal.getNo_utilisateur() == id) {
            return "redirect:/logout";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/desactivate-user")
    public String desactivateUser(@RequestParam int id) {

        utilisateurService.desactivateUser(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur principal = (Utilisateur) auth.getPrincipal();

        if (principal.getNo_utilisateur() == id) {
            return "redirect:/logout";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/activate-user")
    public String activateUser(@RequestParam int id) {

        utilisateurService.activateUser(id);

        return "redirect:/admin";
    }

    @GetMapping("/forget-password")
    public String getForgetPasswordForm(Model model) {

        return "change-password";
    }

    @PostMapping("/forget-password/change-password")
    public String changePassword(@RequestParam("username") String username, @RequestParam String password1, @RequestParam String password2, RedirectAttributes redirectAttributes) {

        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(username);
        if (password1.equals(password2)) {
            utilisateurService.updatePassword(utilisateur.getNo_utilisateur(), password1);
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Les mots de passe ne correspondent pas.");
            return "redirect:/forget-password";
        }
    }
}
