package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    List<Enchere> selectBidByIdArticle(int idArticle) throws BLLException;
    Enchere selectHighestBidByIdArticle(int idArticle) throws BLLException;
    Enchere createEnchere(Enchere lEnchere) throws BLLException;
    void deleteAllBidsByUser(int idUser) throws BLLException;
    void deleteAllBidsOnArticle(int idArticle) throws BLLException;
}
