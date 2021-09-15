package fr.eni.bo;

public class Retrait {
    private int no_article;
    private String rue;
    private String codePostal;
    private String ville;

    public Retrait() {}

    public Retrait(int no_article, String rue, String codePostal, String ville) {
        this.no_article = no_article;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getNo_article() {
        return no_article;
    }

    public void setNo_article(int no_article) {
        this.no_article = no_article;
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

    @Override
    public String toString() {
        return rue + "\n" + codePostal + " " + ville ;
    }

}
