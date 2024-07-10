package fr.eni.ecole.enchere.bll.utilisateur;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Utilisateur.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        super();
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUtilisateur(Utilisateur utilisateur) {

        String password = utilisateur.getMot_de_passe();
        String encryptedPassword = passwordEncoder.encode(password);
        password = "{bcrypt}" + encryptedPassword;
        utilisateur.setMot_de_passe(password);
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

    public void updateUtilisateur(Utilisateur utilisateur) {

        utilisateurRepository.update(utilisateur);
    }

}
