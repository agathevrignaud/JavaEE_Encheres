package fr.eni.bo;

import java.util.Date;

public class ArticleVendu {
    private int no_article;
    private String nomArticle;
    private String description;
    private Date dateDebutEnchere;
    private Date dateFinEnchere;
    private int prixInitial;
    private int prixVente;
    private Retrait lieuRetrait;
    private int no_utilisateur;
    private int no_categorie;

    public ArticleVendu() {}

    public int getNo_article() {
        return no_article;
    }
    public void setNo_article(int no_article) {
        this.no_article = no_article;
    }

    public String getNomArticle() {
        return nomArticle;
    }
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEnchere() {
        return dateDebutEnchere;
    }
    public void setDateDebutEnchere(Date dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public Date getDateFinEnchere() {
        return dateFinEnchere;
    }
    public void setDateFinEnchere(Date dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public int getPrixInitial() {
        return prixInitial;
    }
    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }
    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public int getNo_utilisateur() {
        return no_utilisateur;
    }
    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public int getNo_categorie() {
        return no_categorie;
    }
    public void setNo_categorie(int no_categorie) {
        this.no_categorie = no_categorie;
    }
}
