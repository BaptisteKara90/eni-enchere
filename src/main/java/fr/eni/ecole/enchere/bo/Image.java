package fr.eni.ecole.enchere.bo;

public class Image {

    private int id_image;
    private int no_article;
    private String image;

    public Image(int id_image, int no_article, String image) {
        this.id_image = id_image;
        this.no_article = no_article;
        this.image = image;
    }

    public Image(int no_article, String image) {
        this.no_article = no_article;
        this.image = image;
    }

    public Image() {
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public int getNo_article() {
        return no_article;
    }

    public void setNo_article(int no_article) {
        this.no_article = no_article;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id_image=" + id_image +
                ", no_article=" + no_article +
                ", image='" + image + '\'' +
                '}';
    }
}
