package com.example.gestionnairedelicence.METIER;

public class WINDOWS {
    private int idWindows;
    private String dateAchat;
    private String activationKey;


    public WINDOWS(int id, String dateAchat, String ActivationKey) {
        this.idWindows = id;
        this.dateAchat = dateAchat;
        this.activationKey = ActivationKey;
    }

    public WINDOWS() {
        this.idWindows = 0;
        this.dateAchat = " ";
        this.activationKey = " ";
    }

    public int getIdWindows() {
        return idWindows;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WINDOWS windows = (WINDOWS) o;
        return idWindows == windows.idWindows;
    }
}

