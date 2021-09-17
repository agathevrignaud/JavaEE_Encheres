package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    List<Categorie> selectAll() throws BLLException;
    void createCategory(Categorie laCategorie) throws BLLException;
    void updateCategory(Categorie laCategorie) throws BLLException;
    void deleteCategory(int idCategory) throws BLLException;
    int getAllUses(int idCategory) throws BLLException;

}
