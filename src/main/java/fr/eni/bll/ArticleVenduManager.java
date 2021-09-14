package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.DAOFactory;

import java.time.LocalDate;
import java.util.List;

public class ArticleVenduManager {

    private ArticleVenduDAO articleVenduDAO;

    public ArticleVenduManager() {
        articleVenduDAO = DAOFactory.getArticleVenduDAO();
    }

    public void addNewArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
                              int miseAPrix, String etatVente, int idUser, Categorie laCategorie, Retrait lieuRetrait) {
        ArticleVendu lArticle = new ArticleVendu();

        lArticle.setNomArticle(nomArticle);
        lArticle.setDescription(description);
        lArticle.setDateDebutEnchere(dateDebutEncheres);
        lArticle.setDateFinEnchere(dateFinEncheres);
        lArticle.setMiseAPrix(miseAPrix);
        lArticle.setEtatVente(etatVente);
        lArticle.setNo_utilisateur(idUser);
        lArticle.setLaCategorie(laCategorie);
        lArticle.setLieuRetrait(lieuRetrait);

        articleVenduDAO.createArticle(lArticle);
    }

    /**
     * Retourne une liste de tous les articles
     */
    public List<ArticleVendu> getAllArticles() {
        return articleVenduDAO.selectAll();
    }

    /**
     * Séléctionner un article vendu
     */
    public ArticleVendu selectArticleVendu(int idArticleVendu) {

        return articleVenduDAO.selectById(idArticleVendu);
    }

    /**
     * Supprimer un article (id)
     */
    public void deleteArticle(int id) {
        articleVenduDAO.deleteArticle(id);
    }

   
}
