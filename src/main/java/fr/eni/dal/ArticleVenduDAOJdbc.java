package fr.eni.dal;

import fr.eni.bo.ArticleVendu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOJdbc implements ArticleVenduDAO{

    private static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, prix_vente, date_fin_encheres, no_utilisateur " +
            "FROM ARTICLES_VENDUS WHERE date_fin_encheres >= ?";
    private static final String SELECT_ARTICLES_BY_NAME = "SELECT no_article, nom_article, prix_vente, date_fin_encheres, no_utilisateur " +
            "FROM ARTICLES_VENDUS WHERE nom_article LIKE %?%";
    private static final String SELECT_ARTICLES_BY_CATEGORY = "SELECT no_article, nom_article, prix_vente, date_fin_encheres, no_utilisateur " +
            "FROM ARTICLES_VENDUS WHERE no_categorie=?";

    // TODO : à modifier par un système de switch/case selon ce qui est remplit côté user ?

    @Override
    public List<ArticleVendu> selectAll() {
        List<ArticleVendu> lesArticles = new ArrayList<>();
        ArticleVendu lArticle = new ArticleVendu();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                lArticle.setNo_article(rs.getInt("no_article"));
                lArticle.setNomArticle(rs.getString("nom_article"));
                lArticle.setPrixVente(rs.getInt("prix_vente"));
                lArticle.setDateFinEnchere(rs.getDate("date_fin_encheres"));
                lArticle.setNo_utilisateur(rs.getInt("no_utilisateur"));
            }
            lesArticles.add(lArticle);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return lesArticles;
    }

    @Override
    public List<ArticleVendu> selectByNameSearch(String nomArticle) {
        List<ArticleVendu> lesArticles = new ArrayList<>();
        ArticleVendu lArticle = new ArticleVendu();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLES_BY_NAME);
            pstmt.setString(1, nomArticle);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                lArticle.setNo_article(rs.getInt("no_article"));
                lArticle.setNomArticle(rs.getString("nom_article"));
                lArticle.setPrixVente(rs.getInt("prix_vente"));
                lArticle.setDateFinEnchere(rs.getDate("date_fin_encheres"));
                lArticle.setNo_utilisateur(rs.getInt("no_utilisateur"));
            }
            lesArticles.add(lArticle);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return lesArticles;
    }

    @Override
    public List<ArticleVendu> selectByCategory(int idCategory) {
        List<ArticleVendu> lesArticles = new ArrayList<>();
        ArticleVendu lArticle = new ArticleVendu();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CATEGORY);
            pstmt.setInt(1, idCategory);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                lArticle.setNo_article(rs.getInt("no_article"));
                lArticle.setNomArticle(rs.getString("nom_article"));
                lArticle.setPrixVente(rs.getInt("prix_vente"));
                lArticle.setDateFinEnchere(rs.getDate("date_fin_encheres"));
                lArticle.setNo_utilisateur(rs.getInt("no_utilisateur"));
            }
            lesArticles.add(lArticle);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return lesArticles;
    }


}
