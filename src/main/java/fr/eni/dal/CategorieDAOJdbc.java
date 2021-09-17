package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieDAOJdbc implements CategorieDAO {
    private static final Logger myLogger = Logger.getLogger("LogsDAL_Categorie");
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
    private static final String INSERT_NEW_CATEGORY = "INSERT INTO CATEGORIES VALUES (?)";
    private static final String UPDATE_CATEGORY = "UPDATE CATEGORIES SET libelle=? WHERE no_categorie=?";
    private static final String DELETE_CATEGORY = "DELETE FROM CATEGORIES WHERE no_categorie=?";
    private static final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
    private static final String CHECK_IF_CATEGORY_IS_USED = "SELECT COUNT(no_categorie) as nbrUtilisations FROM ARTICLES_VENDUS WHERE no_categorie=?";

    @Override
    public List<Categorie> selectAll() throws BLLException {
        BLLException bllException = new BLLException();
        List<Categorie> lesCategories = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Categorie laCategorie = new Categorie(
                        rs.getInt("no_categorie"),
                        rs.getString("libelle")
                );
                lesCategories.add(laCategorie);
            }
        } catch(Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_SELECT_ALL_CATEGORIES);
            myLogger.log(Level.WARNING,"Erreur lors de la lecture des catégories en base", bllException);
            throw bllException;
        }
        return lesCategories;
    }

    @Override
    public void createCategory(Categorie laCategorie) throws BLLException {
        BLLException bllException = new BLLException();
        if (laCategorie == null) {
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_CATEGORY);
            throw bllException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_NEW_CATEGORY);
            pstmt.setString(1, laCategorie.getLibelle());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_CATEGORY);
            myLogger.log(Level.WARNING,"Erreur lors de la création d'une nouvelle catégorie", bllException);
            throw bllException;
        }
    }

    @Override
    public void updateCategory(Categorie laCategorie) throws BLLException {
        BLLException bllException = new BLLException();
        if (laCategorie == null) {
            bllException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_CATEGORY);
            throw bllException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CATEGORY);
            pstmt.setString(1, laCategorie.getLibelle());
            pstmt.setInt(2, laCategorie.getNumCategorie());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_CATEGORY);
            myLogger.log(Level.WARNING,"Erreur lors de la mise à jour de la catégorie " + laCategorie.getLibelle(), bllException);
            throw bllException;
        }
    }
    
    @Override
    public void deleteCategory(int idCategory) throws BLLException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORY);
            pstmt.setInt(1, idCategory);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            BLLException bllException = new BLLException();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_DELETE_CATEGORY);
            myLogger.log(Level.WARNING,"Erreur lors de la suppression de la catégorie " + idCategory, bllException);
            throw bllException;
        }
    }

    @Override
    public int getAllUses(int idCategory) throws BLLException {
        int numberOfUses = 0;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(CHECK_IF_CATEGORY_IS_USED);
            pstmt.setInt(1, idCategory);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                numberOfUses = rs.getInt("nbrUtilisations");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            BLLException bllException = new BLLException();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CHECK_IF_CATEGORY_IS_USED);
            myLogger.log(Level.WARNING,"Erreur lors du comptage du nbr d'utilisations de la catégorie " + idCategory, bllException);
            throw bllException;
        }
        return numberOfUses;
    }

}
