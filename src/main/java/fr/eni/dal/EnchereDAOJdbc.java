package fr.eni.dal;

import fr.eni.bo.Enchere;

import java.time.LocalDateTime;
import java.util.List;

public class EnchereDAOJdbc implements EnchereDAO {
    @Override
    public List<Enchere> selectAll() {
        return null;
    }

    @Override
    public Enchere selectById(int idUser, int idArticle, LocalDateTime date) {
        return null;
    }

    @Override
    public void createEnchere(Enchere lEnchere) {

    }
}
