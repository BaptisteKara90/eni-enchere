package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Role;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Utilisateur.UtilisateurRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private PasswordEncoder encoderBean;
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(PasswordEncoder encoderBean, UtilisateurRepository utilisateurRepository) {
        this.encoderBean = encoderBean;
        this.utilisateurRepository = utilisateurRepository;
    }

    public void registerUtilisateur(Utilisateur utilisateur) {

        String password = utilisateur.getMot_de_passe();
        String encryptedPassword = encoderBean.encode(password);

        utilisateur.setMot_de_passe(encryptedPassword);
        utilisateur.setAdministrateur(false);
        utilisateur.setCredit(0);

        utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getUtilisateurs() {

        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateur(int id) {

       return utilisateurRepository.findById(id);
    }

    public Utilisateur getUtilisateurByEmail(String email) {

        return utilisateurRepository.findByEmail(email);
    }

    public void updateUtilisateur(Utilisateur utilisateur) {

        //Cryptage du mot de passe
        String password = utilisateur.getMot_de_passe();
        String encryptedPassword = encoderBean.encode(password);
        utilisateur.setMot_de_passe(encryptedPassword);

        //Récupération de l'utilisateur connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur principal = (Utilisateur) auth.getPrincipal();

        //Mise à jour de l'utilisateur
        utilisateurRepository.update(utilisateur);

        // On récupère l'utilisateur mis à jour
        Utilisateur updatedUser = getUtilisateur(utilisateur.getNo_utilisateur());

        // Si l'utilisateur mis à jour est l'utilisateur connecté, on met à jour le principal
        if(updatedUser.getNo_utilisateur() == principal.getNo_utilisateur()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(updatedUser, updatedUser.getPassword(), updatedUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public void deleteUtilisateur(int id) {

        utilisateurRepository.deleteById(id);
    }

    public void changeCredit(int id,int credit) {
        utilisateurRepository.changeCredit(id, credit);
    }

    public List<Role> getUserRoles(String username){
        return utilisateurRepository.findRolesByUsername(username);
    }
}
