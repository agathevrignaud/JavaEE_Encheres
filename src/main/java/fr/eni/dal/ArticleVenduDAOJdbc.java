package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;

import java.sql.*;
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
    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres, " +
            "date_fin_encheres, prix_initial,no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_BY_ID = SELECT_ALL_ARTICLES + "WHERE id=?";
    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE id = ?";

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

    @Override
    public void createArticle(ArticleVendu lArticle) {
        if(lArticle==null) {
            //throw exception
        }

        // TODO : Revoir l'histoire des Generated_Keys (récupérer le no_article après création ?)
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE);

            pstmt.setString(1,lArticle.getNomArticle());
            pstmt.setString(2,lArticle.getDescription());
            pstmt.setDate(3, (Date) lArticle.getDateDebutEnchere());
            pstmt.setDate(4, (Date) lArticle.getDateFinEnchere());
            pstmt.setInt(5,lArticle.getMiseAPrix());
            pstmt.setInt(6,lArticle.getNo_utilisateur());
            pstmt.setInt(7,lArticle.getLaCategorie().getNo_categorie());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public ArticleVendu selectById(int id) {
        ArticleVendu result = null;
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_ID);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()){
                result = map(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public void deleteArticle(int id){
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private ArticleVendu map(ResultSet rs) throws SQLException {
        int no_article = rs.getInt("no_article");
        String nom_article = rs.getString("nom_article");
        String description = rs.getString("description");
        Date debut_encheres = rs.getDate("date_debut_encheres");
        Date fin_encheres = rs.getDate("date_fin_encheres");
        int prix_initial = rs.getInt("prix_initial");
        int prix_vente = rs.getInt("prix_vente");
        String etat_vente = rs.getString("etat_vente");
        int no_utilisateur = rs.getInt("no_utilisateur");
        int no_categorie = rs.getInt("no_categorie");
        return new ArticleVendu(no_article, nom_article, description, debut_encheres, fin_encheres, prix_initial, prix_vente,etat_vente, no_utilisateur, no_categorie);
    }

}
