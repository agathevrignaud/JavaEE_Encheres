package fr.eni.bo;


import java.time.LocalDateTime;

public class Enchere {
    private Utilisateur lUtilisateur;
    private ArticleVendu lArticle;
    private LocalDateTime dateEnchere;
    private int montantEnchere;

    public Enchere() {}

    public Enchere(Utilisateur lUtilisateur, ArticleVendu lArticle, LocalDateTime dateEnchere, int montantEnchere) {
        this.lUtilisateur = lUtilisateur;
        this.lArticle = lArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Utilisateur getlUtilisateur() {
        return lUtilisateur;
    }

    public void setlUtilisateur(Utilisateur lUtilisateur) {
        this.lUtilisateur = lUtilisateur;
    }

    public ArticleVendu getlArticle() {
        return lArticle;
    }

    public void setlArticle(ArticleVendu lArticle) {
        this.lArticle = lArticle;
    }

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

}
