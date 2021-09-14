package fr.eni.dal;

import fr.eni.bo.Retrait;

public interface RetraitDAO {
    public Retrait selectById(int idRetrait); // idRetrait = no_article

    public void createRetrait(Retrait lieuRetrait);
    void deleteRetrait(int id);
}
