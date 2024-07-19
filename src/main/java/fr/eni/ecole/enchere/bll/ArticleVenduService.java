package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.ArticleVendu;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.dal.articleVendu.ArticleVenduRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduService {

    private final ImageService imageService;
    private final EnchereService enchereService;
    private ArticleVenduRepository articleVenduRepository;

    public ArticleVenduService(ArticleVenduRepository articleVenduRepository, ImageService imageService, EnchereService enchereService) {
        this.articleVenduRepository = articleVenduRepository;
        this.imageService = imageService;
        this.enchereService = enchereService;
    }

    public List<ArticleVendu> getArticleVendu() {

        return articleVenduRepository.findAll();
    }

    public ArticleVendu getArticleVendu(int id) {

        return articleVenduRepository.findById(id);
    }

    public void addArticleVendu(ArticleVendu articleVendu) {

        articleVenduRepository.save(articleVendu);
    }

    public void updateArticle(ArticleVendu articleVendu) {

        articleVenduRepository.update(articleVendu);
    }

    public void deleteArticleVendu(int id) {

        articleVenduRepository.deleteById(id);
    }

    public void deleteArticlesVenduByUserId(int userId) {
        List<ArticleVendu> userArticles = articleVenduRepository.getArticlesVenduByUser(userId);

        for (ArticleVendu articleVendu : userArticles) {
            imageService.deleteImageByArticle(articleVendu.getNo_article());
            List<Enchere> enchereList = enchereService.getEnchereByArticle(articleVendu.getNo_article());
            for(Enchere enchere : enchereList) {
                enchereService.deleteEnchere(enchere.getId_enchere());
            }
        }
        articleVenduRepository.deleteByIdUtilisateur(userId);
    }

}
