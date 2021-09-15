package fr.eni.bo;

public class Categorie {
    private int numCategorie;
    private String libelle;

    public Categorie() {
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie(int no_categorie, String libelle) {
        this.numCategorie = no_categorie;
        this.libelle = libelle;
    }

    public int getNo_categorie() {
        return numCategorie;
    }

    public void setNo_categorie(int no_categorie) {
        this.numCategorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
