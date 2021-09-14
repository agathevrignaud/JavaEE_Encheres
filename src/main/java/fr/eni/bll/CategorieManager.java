package fr.eni.bll;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class CategorieManager {
    private CategorieDAO categorieDAO;

    public CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public List<Categorie> getAllCategories() throws BLLException {
        return categorieDAO.selectAll();
    }

    public void addNewCategory(String libelleCategorie) throws BLLException {
        if (libelleCategorie != null) {
            Categorie laCategorie = new Categorie(libelleCategorie);
            categorieDAO.createCategory(laCategorie);
        } else {
            System.out.println("Impossible de créer la nouvelle catégorie : le nom est nul(l)");
            throw new BLLException();
        }
    }

    public void updateCategory(int idCategory, String libelleCategorie) throws BLLException {
        if (libelleCategorie != null) {
            Categorie laCategorie = new Categorie(idCategory, libelleCategorie);
            categorieDAO.updateCategory(laCategorie);
        } else {
            System.out.println("Impossible de modifier la nouvelle catégorie : le nom est nul(l)");
            throw new BLLException();
        }
    }

    public void deleteCategory(int idCategory) {
        categorieDAO.deleteCategory(idCategory);
    }

    public int getAllUses(int idCategory) {
        return categorieDAO.getAllUses(idCategory);
    }

    public Categorie selectCategoryById(int idCategory) {return categorieDAO.selectCategoryById(idCategory);}

}
