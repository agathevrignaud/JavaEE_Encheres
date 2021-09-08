package fr.eni.dal;

import fr.eni.bo.Retrait;

import java.util.List;

public interface RetraitDAO {
    public Retrait selectById(int idRetrait); // idRetrait = no_article
    public void createRetrait(Retrait lieuRetrait);
}
