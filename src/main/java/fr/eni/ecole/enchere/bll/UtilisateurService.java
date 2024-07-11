package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Utilisateur.UtilisateurRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

        String password = utilisateur.getMot_de_passe();
        String encryptedPassword = encoderBean.encode(password);

        utilisateur.setMot_de_passe(encryptedPassword);

        utilisateurRepository.update(utilisateur);
    }
}
