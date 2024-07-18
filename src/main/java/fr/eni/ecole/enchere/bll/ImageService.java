package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Image;
import fr.eni.ecole.enchere.dal.image.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        super();
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    public Image getImage(int id) {
        return imageRepository.findById(id);
    }

    public void addImage(Image image) {
        imageRepository.save(image);
    }

    public void deleteImage(int id) {
        imageRepository.deleteById(id);
    }

    public Image findImageByArticle(int no_article) {
       return imageRepository.getImageByArticle(no_article);
    }

    public void deleteImageByArticle(int no_article) {
        imageRepository.deleteImageByArticle(no_article);
    }
}
