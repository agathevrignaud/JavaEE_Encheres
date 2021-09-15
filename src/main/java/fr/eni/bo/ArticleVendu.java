package fr.eni.bo;

import java.time.LocalDate;

public class ArticleVendu {
    private int numArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEnchere;
    private LocalDate dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private Retrait lieuRetrait;
    private Categorie laCategorie;
    private Utilisateur lUtilisateur;

    public ArticleVendu() {}

    public ArticleVendu(int numArticle, String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente, Retrait lieuRetrait, Categorie laCategorie, Utilisateur lUtilisateur) {
        this.numArticle = numArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.lieuRetrait = lieuRetrait;
        this.laCategorie = laCategorie;
        this.lUtilisateur = lUtilisateur;
    }

    public int getNumArticle() {
        return numArticle;
    }

    public void setNumArticle(int numArticle) {
        this.numArticle = numArticle;
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

    public LocalDate getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public LocalDate getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(LocalDate dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
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

    public Categorie getLaCategorie() {
        return laCategorie;
    }

    public void setLaCategorie(Categorie laCategorie) {
        this.laCategorie = laCategorie;
    }

    public Utilisateur getlUtilisateur() {
        return lUtilisateur;
    }

    public void setlUtilisateur(Utilisateur lUtilisateur) {
        this.lUtilisateur = lUtilisateur;
    }
}
