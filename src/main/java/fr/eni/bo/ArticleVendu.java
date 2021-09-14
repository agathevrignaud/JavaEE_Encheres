package fr.eni.bo;

import java.time.LocalDate;

public class ArticleVendu {
    private int no_article;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEnchere;
    private LocalDate dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private String etatVente; // A - en attente, C - en cours, F - finie
    private Retrait lieuRetrait;
    private Categorie laCategorie;
    private int no_utilisateur;

    public ArticleVendu() {
    }

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

    public int getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    @Override
    public String toString() {
        return "ArticleVendu{" +
                "no_article=" + no_article +
                ", nomArticle='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEnchere=" + dateDebutEnchere +
                ", dateFinEnchere=" + dateFinEnchere +
                ", miseAPrix=" + miseAPrix +
                ", prixVente=" + prixVente +
                ", etatVente='" + etatVente + '\'' +
                ", lieuRetrait=" + lieuRetrait +
                ", laCategorie=" + laCategorie +
                ", no_utilisateur=" + no_utilisateur +
                '}';
    }

}
