package fr.eni.bo;

import java.time.LocalDate;

/**
 * Classe Article vendu
 */
public class ArticleVendu {
    private int numArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEnchere;
    private LocalDate dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private String etatVente; // P - en attente, C - en cours, F - finie, A - annulée
    private Retrait lieuRetrait;
    private Categorie laCategorie;
    private Utilisateur lUtilisateur;

    public ArticleVendu() {}

    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
    }

    public ArticleVendu(int numArticle, String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente) {
        this(nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix);
        this.numArticle = numArticle;
        this.prixVente = prixVente;
    }

    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, String etatVente, Retrait lieuRetrait, Categorie laCategorie, Utilisateur lUtilisateur) {
        this(nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix);
        this.etatVente = etatVente;
        this.lieuRetrait = lieuRetrait;
        this.laCategorie = laCategorie;
        this.lUtilisateur = lUtilisateur;
    }

    public ArticleVendu(int numArticle, String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente, Utilisateur lUtilisateur) {
        this(numArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix, prixVente);
        this.lUtilisateur = lUtilisateur;
    }

    public ArticleVendu(int numArticle, String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente, String etatVente, Retrait lieuRetrait, Categorie laCategorie, Utilisateur lUtilisateur) {
        this(numArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix, prixVente, lUtilisateur);
        this.lieuRetrait = lieuRetrait;
        this.laCategorie = laCategorie;

    }

    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, String etatVente, Categorie laCategorie, Utilisateur idUser) {
        this(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix);
        this.etatVente = etatVente;
        this.laCategorie = laCategorie;
        this.lUtilisateur = idUser;
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

    public String getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
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
