package fr.eni.bo;

public class Categorie {
    private int numCategorie;
    private String libelle;

    public Categorie() {}

    public Categorie(String libelleCategorie) {
        this.libelle = libelle;
    }

    public Categorie(int numCategorie, String libelle) {
        this(libelle);
        this.numCategorie = numCategorie;
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
