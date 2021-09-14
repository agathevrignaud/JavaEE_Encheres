package fr.eni.dal;

import fr.eni.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    List<Categorie> selectAll();

    void createCategory(Categorie laCategorie);

    void updateCategory(Categorie laCategorie);

    void deleteCategory(int idCategory);

    int getAllUses(int idCategory);
}
