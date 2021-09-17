package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.Retrait;

public interface RetraitDAO {
    Retrait selectById(int idRetrait) throws BLLException;
    Retrait createRetrait(Retrait lieuRetrait) throws BLLException;
    void updateRetrait(Retrait lieuRetrait) throws BLLException;
}
