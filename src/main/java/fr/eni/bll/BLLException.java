package fr.eni.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {
        private static final long serialVersionUID = 1L;
        private List<Integer> listeCodesErreur;

        public BLLException() {
            super();
            this.listeCodesErreur=new ArrayList<>();
        }


        public void ajouterErreur(int code) {
            if(!this.listeCodesErreur.contains(code)) {
                this.listeCodesErreur.add(code);
            }
        }

        public boolean hasErreurs() {
            return this.listeCodesErreur.size()>0;
        }

        public List<Integer> getListeCodesErreur() {
            return this.listeCodesErreur;
        }
}
