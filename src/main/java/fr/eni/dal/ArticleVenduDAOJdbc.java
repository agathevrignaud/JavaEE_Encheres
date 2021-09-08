package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOJdbc implements ArticleVenduDAO{

    private static final String SELECT_ALL_ARTICLES = "SELECT A.no_article, A.nom_article, A.description, " +
            "A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.no_utilisateur, C.*, " +
            "R.rue, R.ville, R.code_postal " +
            "FROM ARTICLES_VENDUS A " +
            "INNER JOIN UTILISATEURS U " +
            "ON A.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN CATEGORIES C " +
            "ON A.no_categorie = C.no_categorie" +
            "INNER JOIN RETRAITS R " +
            "ON A.no_article = R.no_article";

    // TODO : fournir List<ArticleVendu> avec toutes les infos, le tri se fera côté front ?

    @Override
    public List<ArticleVendu> selectAll() {
        List<ArticleVendu> lesArticles = new ArrayList<>();
        ArticleVendu lArticle = new ArticleVendu();
        Retrait lieuRetrait = new Retrait();
        Categorie laCategorie = new Categorie();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                lArticle.setNo_article(rs.getInt("A.no_article"));
                lArticle.setNomArticle(rs.getString("A.nom_article"));
                lArticle.setDescription(rs.getString("A.description"));
                lArticle.setDateDebutEnchere(rs.getDate("A.date_debut_encheres"));
                lArticle.setDateFinEnchere(rs.getDate("A.date_fin_encheres"));
                // TODO : check si prix non nuls
                lArticle.setMiseAPrix(rs.getInt("A.prix_initial"));
                lArticle.setPrixVente(rs.getInt("A.prix_vente"));
                lArticle.setEtatVente(rs.getString("A.etat_vente"));

                lieuRetrait.setNo_article(rs.getInt("A.no_article"));
                lieuRetrait.setRue(rs.getString("R.rue"));
                lieuRetrait.setCodePostal(rs.getString("R.code_postal"));
                lieuRetrait.setVille(rs.getString("R.ville"));
                lArticle.setLieuRetrait(lieuRetrait);

                laCategorie.setNo_categorie(rs.getInt("C.no_categorie"));
                laCategorie.setLibelle(rs.getString("C.libelle"));
                lArticle.setLaCategorie(laCategorie);

                lArticle.setNo_utilisateur(rs.getInt("A.no_utilisateur"));
            }
            lesArticles.add(lArticle);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return lesArticles;
    }


}
