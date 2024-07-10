package fr.eni.ecole.enchere.bll.utilisateur;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Utilisateur.UtilisateurRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

//    PasswordEncoder encoderBean;
    private UtilisateurRepository utilisateurRepository;

//    public UtilisateurService(PasswordEncoder encoderBean, UtilisateurRepository utilisateurRepository) {
//        this.encoderBean = encoderBean;
//        this.utilisateurRepository = utilisateurRepository;
//    }


    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public void registerUtilisateur(Utilisateur utilisateur) {

//        String password = utilisateur.getMot_de_passe();
//        String encryptedPassword = encoderBean.encode(password);
        //password = "{bcrypt}" + encryptedPassword;
//        utilisateur.setMot_de_passe(encryptedPassword);
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
