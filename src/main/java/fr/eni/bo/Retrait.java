package fr.eni.bo;

public class Retrait {
    private String rue;
    private String codePostal;
    private String ville;
    private ArticleVendu lArticle;

    public Retrait() {}

    public Retrait(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Retrait(String rue, String codePostal, String ville, ArticleVendu lArticle) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.lArticle = lArticle;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ArticleVendu getlArticle() {
        return lArticle;
    }

    public void setlArticle(ArticleVendu lArticle) {
        this.lArticle = lArticle;
    }

    @Override
    public String toString() {
        return rue + "\n" + codePostal + " " + ville ;
    }

}
