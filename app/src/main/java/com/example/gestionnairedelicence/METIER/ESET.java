package com.example.gestionnairedelicence.METIER;

public class ESET {
    private int idEset;
    private String dateAchat;
    private String activationKey;
    private String dateDeFInDeValidite;

    public ESET(int idEset, String dateAchat, String ActivationKey, String dateDeFInDeValidite) {
        this.idEset = idEset;
        this.dateAchat = dateAchat;
        this.activationKey = ActivationKey;
        this.dateDeFInDeValidite = dateDeFInDeValidite;
    }

    public ESET() {
        this.idEset = 0;
        this.dateAchat = " ";
        this.activationKey = " ";
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
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getDateDeFInDeValidite() {
        return dateDeFInDeValidite;
    }

    public void setDateFinValidite(String dateDeFInDeValidite) {
        this.dateDeFInDeValidite = dateDeFInDeValidite;
    }

    @Override
    public String toString() {
        return "ESET{" +
                "idEset=" + idEset +
                ", dateAchat='" + dateAchat + '\'' +
                ", ActivationKey='" + activationKey + '\'' +
                ", dateDeFInDeValidite='" + dateDeFInDeValidite + '\'' +
                '}';
    }
}
