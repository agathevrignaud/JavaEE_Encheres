package fr.eni.dal;

import fr.eni.bo.Categorie;
import java.util.List;

public interface CategorieDAO {
    public List<Categorie> selectAll();
    public void createCategory(Categorie laCategorie);
    public void updateCategory(Categorie laCategorie);
    public void deleteCategory(int idCategory);
}
