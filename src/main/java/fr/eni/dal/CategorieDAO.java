package fr.eni.dal;

import fr.eni.bo.Categorie;
import java.util.List;

public interface CategorieDAO {
    public List<Categorie> selectAll();
}
