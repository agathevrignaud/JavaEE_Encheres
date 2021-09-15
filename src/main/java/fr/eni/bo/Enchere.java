package fr.eni.bo;

import java.time.LocalDateTime;

public class Enchere {
    private int noUtilisateur;
    private String nomUtilisateur;
    private int noArticle;
    private LocalDateTime dateEnchere;
    private int montantEnchere;

    public Enchere() {}

    public int getNo_utilisateur() {
        return noUtilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.noUtilisateur = no_utilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public int getNo_article() {
        return noArticle;
    }

    public void setNo_article(int no_article) {
        this.noArticle = no_article;
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
