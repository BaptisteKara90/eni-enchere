package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Retrait;
import fr.eni.ecole.enchere.dal.Retrait.RetraitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitService {

    private RetraitRepository retraitRepository;

    public RetraitService(RetraitRepository retraitRepository) {
        this.retraitRepository = retraitRepository;
    }

    public List<Retrait> getAllRetrait() {
        return retraitRepository.findAll();
    }

    public Retrait getRetrait(int id) {
        return retraitRepository.findById(id);
    }

    public void addRetrait(Retrait retrait) {
        retraitRepository.save(retrait);
    }
}
