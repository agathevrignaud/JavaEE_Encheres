package fr.eni.bo;

import java.util.Date;

public class ArticleVendu {

    private String nomArticle;

    private String description;

    private Date dateDebutEnchere;

    private Date dateFinEnchere;

    private int prixInitial;

    private int prixVente;

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
}
