package fr.eni.dal;

import fr.eni.bo.Retrait;

public interface RetraitDAO {
    Retrait selectById(int idRetrait);
    Retrait createRetrait(Retrait lieuRetrait);
}
