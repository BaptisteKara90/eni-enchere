package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.dal.Retrait.RetraitRepository;
import org.springframework.stereotype.Service;

@Service
public class RetraitService {

    private RetraitRepository retraitRepository;

    public RetraitService(RetraitRepository retraitRepository) {
        this.retraitRepository = retraitRepository;
    }
}
