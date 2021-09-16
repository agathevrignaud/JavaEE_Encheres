package fr.eni.bo;

public class Categorie {
    private int numCategorie;
    private String libelle;

    public Categorie() {}

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie(int numCategorie, String libelle) {
        this.numCategorie = numCategorie;
        this.libelle = libelle;
    }

    public int getNumCategorie() {
        return numCategorie;
    }

    public void setNumCategorie(int no_categorie) {
        this.numCategorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
