package com.example.gestionnairedelicence.METIER;

public class WINDOWS {
    private int id;
    private String dateAchat;
    private String ActivationKey;


    public WINDOWS(int id, String dateAchat, String ActivationKey) {
        this.id = id;
        this.dateAchat = dateAchat;
        this.ActivationKey = ActivationKey;
    }

    public WINDOWS() {
        this.id = 0;
        this.dateAchat = " ";
        this.ActivationKey = " ";
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "WINDOWS{" +
                "id=" + id +
                ", dateAchat='" + dateAchat + '\'' +
                ", ActivationKey='" + ActivationKey + '\'' +
                '}';
    }
}
