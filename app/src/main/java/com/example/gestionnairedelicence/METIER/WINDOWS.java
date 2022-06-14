package com.example.gestionnairedelicence.METIER;

public class WINDOWS {
    private int id;
    private String dateAchat;
    private String activationKey;


    public WINDOWS(int id, String dateAchat, String ActivationKey) {
        this.id = id;
        this.dateAchat = dateAchat;
        this.activationKey = ActivationKey;
    }

    public WINDOWS() {
        this.id = 0;
        this.dateAchat = " ";
        this.activationKey = " ";
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
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    @Override
    public String toString() {
        return "WINDOWS{" +
                "id=" + id +
                ", dateAchat='" + dateAchat + '\'' +
                ", ActivationKey='" + activationKey + '\'' +
                '}';
    }
}
