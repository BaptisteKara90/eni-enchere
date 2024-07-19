package fr.eni.ecole.enchere.controllers;

import fr.eni.ecole.enchere.bll.CategorieService;
import fr.eni.ecole.enchere.bll.EnchereService;
import fr.eni.ecole.enchere.bll.ImageService;
import fr.eni.ecole.enchere.bll.SearchService;
import fr.eni.ecole.enchere.bo.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    private SearchService searchService;
    private CategorieService categorieService;
    private EnchereService enchereService;
    private ImageService imageService;

    public SearchController(SearchService searchService, CategorieService categorieService, EnchereService enchereService, ImageService imageService) {
        this.searchService = searchService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
        this.imageService = imageService;
    }

    @PostMapping("/search-article")
    public String searchArticle(@ModelAttribute("search") Search search, Model model){

        Search emptyFilter = new Search();
        model.addAttribute("search", emptyFilter);


        List<ArticleVendu> searchResult = searchService.getArticlesWithFilter(search);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Map<String, String> formattedDates = new HashMap<>();

        for (ArticleVendu articleVendu : searchResult) {
            Enchere enchere = enchereService.getEnchere(articleVendu.getNo_article());
            if (enchere != null) {
                articleVendu.setPrix_initial(enchere.getMontant_enchere());
            }
            String formattedDateDebut = articleVendu.getDate_debut_encheres().format(formatter);
            String formattedDateFin = articleVendu.getDate_fin_encheres().format(formatter);

            formattedDates.put(articleVendu.getNo_article() + "_debut", formattedDateDebut);
            formattedDates.put(articleVendu.getNo_article() + "_fin", formattedDateFin);
        }


        List<Image> listImage = imageService.getImages();
        if(listImage != null && listImage.size() > 0) {
            model.addAttribute("images", listImage);
        }
        model.addAttribute("articles", searchResult);

        List<Categorie> listCategories = categorieService.getAllCategories();
        model.addAttribute("categories", listCategories);
        model.addAttribute("formattedDates", formattedDates);

        return "search-article";
    }
}
