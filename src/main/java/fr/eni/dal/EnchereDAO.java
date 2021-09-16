package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    List<Enchere> selectBidByIdArticle(int idArticle);
    Enchere selectHighestBidByIdArticle(int idArticle);
    Enchere createEnchere(Enchere lEnchere) throws BLLException;
    void deleteAllBidsByUser(int idUser) throws BLLException;
    void deleteAllBidsOnArticle(int idArticle) throws BLLException;
}
