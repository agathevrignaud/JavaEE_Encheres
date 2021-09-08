package fr.eni.dal;

import fr.eni.bo.Retrait;

import java.util.List;

public interface RetraitDAO {
    public List<Retrait> selectAll();
    public Retrait selectById(int idRetrait); // idRetrait = no_article
    public void createRetrait(Retrait lieuRetrait);
}
