package fr.eni.dal;

import fr.eni.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    public List<Enchere> selectByIdArticle(int idArticle);

    public void createEnchere(Enchere lEnchere);
}
