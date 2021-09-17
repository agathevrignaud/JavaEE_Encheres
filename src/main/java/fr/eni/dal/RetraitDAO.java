package fr.eni.dal;

import fr.eni.bo.Retrait;

public interface RetraitDAO {
    Retrait selectById(int idRetrait);
    Retrait createRetrait(Retrait lieuRetrait);

    void deleteRetrait(int id);

    void updateRetrait(Retrait retrait);
}
