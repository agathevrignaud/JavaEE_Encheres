package fr.eni.dal;

import fr.eni.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    List<Enchere> selectBidByIdArticle(int idArticle);
    Enchere selectHighestBidByIdArticle(int idArticle);
    Enchere createEnchere(Enchere lEnchere);
    void deleteAllBidsByUser(int idUser);
}
