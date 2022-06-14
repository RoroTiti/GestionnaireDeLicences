package com.example.gestionnairedelicence.METIER;

public class ESET {

    private int idEset;
    private String dateAchat;
    private String ActivationKey;
    private String dateDeFInDeValidite;

    public ESET(int idEset, String dateAchat, String ActivationKey, String dateDeFInDeValidite) {
        this.idEset = idEset;
        this.dateAchat = dateAchat;
        this.ActivationKey = ActivationKey;
        this.dateDeFInDeValidite = dateDeFInDeValidite;
    }

    public ESET() {
        this.idEset = 0;
        this.dateAchat = " ";
        this.ActivationKey = " ";
        this.dateDeFInDeValidite = " ";
    }

    public int getIdEset() {
        return idEset;
    }

    public String getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getActivationKey() {
        return ActivationKey;
    }

    public void setActivationKey(String activationKey) {
        ActivationKey = activationKey;
    }

    public String getDateDeFInDeValidite() {
        return dateDeFInDeValidite;
    }

    public void setDateDeFInDeValidite(String dateDeFInDeValidite) {
        this.dateDeFInDeValidite = dateDeFInDeValidite;
    }

    @Override
    public String toString() {
        return "ESET{" +
                "idEset=" + idEset +
                ", dateAchat='" + dateAchat + '\'' +
                ", ActivationKey='" + ActivationKey + '\'' +
                ", dateDeFInDeValidite='" + dateDeFInDeValidite + '\'' +
                '}';
    }
}
