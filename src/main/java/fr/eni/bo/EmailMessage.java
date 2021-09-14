package fr.eni.bo;

public class EmailMessage {
    private String destinataire;
    private String expediteur;
    private String enCopie;
    private String enCopieCachee;
    private String sujet;
    private String msg;
    private int msgType = TEXT_MSG;
    public static final int HTML_MSG = 1;
    public static final int TEXT_MSG = 2;

    public EmailMessage() {}

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getEnCopie() {
        return enCopie;
    }

    public void setEnCopie(String enCopie) {
        this.enCopie = enCopie;
    }

    public String getEnCopieCachee() {
        return enCopieCachee;
    }

    public void setEnCopieCachee(String enCopieCachee) {
        this.enCopieCachee = enCopieCachee;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
