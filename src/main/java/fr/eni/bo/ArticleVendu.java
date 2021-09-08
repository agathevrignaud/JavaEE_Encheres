package fr.eni.bo;

import java.util.Date;

public class ArticleVendu {
    private int no_article;
    private String nomArticle;
    private String description;
    private Date dateDebutEnchere;
    private Date dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private char etatVente;
    private Retrait lieuRetrait;
    private Categorie laCategorie;
    private Utilisateur leVendeur;

    public ArticleVendu() {}

    public int getNo_article() { return no_article; }
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

    public int getMiseAPrix() { return miseAPrix; }
    public void setMiseAPrix(int miseAPrix) { this.miseAPrix = miseAPrix;}

    public int getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public char getEtatVente() { return etatVente; }
    public void setEtatVente(char etatVente) { this.etatVente = etatVente;}

    public Retrait getLieuRetrait() { return lieuRetrait; }
    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public Categorie getLaCategorie() { return laCategorie; }
    public void setLaCategorie(Categorie laCategorie) { this.laCategorie = laCategorie; }

    public Utilisateur getLeVendeur() { return leVendeur; }
    public void setLeVendeur(Utilisateur leVendeur) { this.leVendeur = leVendeur; }
}
