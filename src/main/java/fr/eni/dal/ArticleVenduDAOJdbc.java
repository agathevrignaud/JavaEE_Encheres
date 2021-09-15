package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOJdbc implements ArticleVenduDAO {

    private static final String SELECT_ALL_ARTICLES = "SELECT A.no_article, A.nom_article, A.description, " +
            "A.date_debut_encheres, A.date_fin_encheres, A.prix_initial, A.prix_vente, A.etat_vente, A.no_utilisateur, U.pseudo, C.*, " +
            "R.rue, R.ville, R.code_postal " +
            "FROM ARTICLES_VENDUS A " +
            "INNER JOIN UTILISATEURS U " +
            "ON A.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN CATEGORIES C " +
            "ON A.no_categorie = C.no_categorie " +
            "INNER JOIN RETRAITS R " +
            "ON A.no_article = R.no_article";
    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres, " +
            "date_fin_encheres, prix_initial, etat_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";

    // TODO : fournir List<ArticleVendu> avec toutes les infos, le tri se fera côté front ?

    @Override
    public List<ArticleVendu> selectAll() {
        List<ArticleVendu> lesArticles = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleVendu lArticle = new ArticleVendu();
                Retrait lieuRetrait = new Retrait();
                Categorie laCategorie = new Categorie();

                lArticle.setNo_article(rs.getInt("no_article"));
                lArticle.setNomArticle(rs.getString("nom_article"));
                lArticle.setDescription(rs.getString("description"));
                lArticle.setDateDebutEnchere(rs.getDate("date_debut_encheres").toLocalDate());
                lArticle.setDateFinEnchere(rs.getDate("date_fin_encheres").toLocalDate());

                // TODO : check si prix non nuls
                lArticle.setMiseAPrix(rs.getInt("prix_initial"));
                lArticle.setPrixVente(rs.getInt("prix_vente"));
                lArticle.setEtatVente(rs.getString("etat_vente"));

                lieuRetrait.setNo_article(rs.getInt("no_article"));
                lieuRetrait.setRue(rs.getString("rue"));
                lieuRetrait.setCodePostal(rs.getString("code_postal"));
                lieuRetrait.setVille(rs.getString("ville"));
                lArticle.setLieuRetrait(lieuRetrait);

                laCategorie.setNo_categorie(rs.getInt("no_categorie"));
                laCategorie.setLibelle(rs.getString("libelle"));
                lArticle.setLaCategorie(laCategorie);

                lArticle.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lArticle.setPseudoUtilisateur(rs.getString("pseudo"));

                lesArticles.add(lArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lesArticles;
    }


    @Override
    public void createArticle(ArticleVendu lArticle) {
        if (lArticle == null) {
            //throw exception
        }

        // TODO : Revoir l'histoire des Generated_Keys (récupérer le no_article après création ?)
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE);

            pstmt.setString(1, lArticle.getNomArticle());
            pstmt.setString(2, lArticle.getDescription());
            pstmt.setDate(3, Date.valueOf(lArticle.getDateDebutEnchere()));
            pstmt.setDate(4, Date.valueOf(lArticle.getDateFinEnchere()));
            pstmt.setInt(5, lArticle.getMiseAPrix());
            pstmt.setString(6, lArticle.getEtatVente());
            pstmt.setInt(7, lArticle.getNo_utilisateur());
            pstmt.setInt(8, lArticle.getLaCategorie().getNo_categorie());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
