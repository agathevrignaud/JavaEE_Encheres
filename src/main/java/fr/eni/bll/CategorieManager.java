package fr.eni.bll;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;

import java.util.List;

public class CategorieManager {
    private CategorieDAO categorieDAO;

    public CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public List<Categorie> getAllCategories() {
        return categorieDAO.selectAll();
    }
}
