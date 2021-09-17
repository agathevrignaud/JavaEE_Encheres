package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.DAOFactory;

import java.time.LocalDate;
import java.util.List;

public class ArticleVenduManager {
    private final ArticleVenduDAO articleVenduDAO;

    public ArticleVenduManager() { articleVenduDAO = DAOFactory.getArticleVenduDAO();}

    public List<ArticleVendu> getAllArticles() {
        return articleVenduDAO.selectAll();
    }

    public ArticleVendu getArticleById(int idArticle) {
        return articleVenduDAO.selectArticleById(idArticle);
    }

    public ArticleVendu addNewArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
                              int miseAPrix, String etatVente, Utilisateur lUtilisateur, Categorie laCategorie) {
        ArticleVendu lArticle = new ArticleVendu(
                nomArticle,
                description,
                dateDebutEncheres,
                dateFinEncheres,
                miseAPrix,
                etatVente,
                laCategorie,
                lUtilisateur
        );
        return articleVenduDAO.createArticle(lArticle);
    }

    public void updateArticlePrice(int bid, int idArticle) {
        articleVenduDAO.updateBidOnArticle(bid, idArticle);
    }

    public void cancelAllSalesByUser(int idUser) {
        articleVenduDAO.deleteAllArticlesByUserId(idUser);
    }

    public void updateArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
                              int miseAPrix, String etatVente, Categorie laCategorie, Utilisateur utilisateur) {
        ArticleVendu articleVendu;
        articleVendu = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, etatVente, laCategorie, utilisateur);
        articleVenduDAO.updateArticle(articleVendu);
    }

    public void deleteArticle(int id) {
        articleVenduDAO.deleteArticleById(id);
    }

}
