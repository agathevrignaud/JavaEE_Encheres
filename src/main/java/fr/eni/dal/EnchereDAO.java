package fr.eni.dal;

import fr.eni.bo.Enchere;

import java.time.LocalDateTime;
import java.util.List;

public interface EnchereDAO {
    public List<Enchere> selectAll();
    public Enchere selectById(int idUser, int idArticle, LocalDateTime date);
    public void createEnchere(Enchere lEnchere);
}
