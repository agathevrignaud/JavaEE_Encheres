package fr.eni.dal;

import fr.eni.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    List<Enchere> selectByIdArticle(int idArticle);
    void createEnchere(Enchere lEnchere);
}
