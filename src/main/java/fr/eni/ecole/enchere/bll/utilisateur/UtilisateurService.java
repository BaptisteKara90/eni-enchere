package fr.eni.ecole.enchere.bll.utilisateur;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Utilisateur.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        super();
        this.utilisateurRepository = utilisateurRepository;
    }

    public void registerUtilisateur(Utilisateur utilisateur) {

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
