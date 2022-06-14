package com.example.gestionnairedelicence.IHM.Adapters;

public class Licence {
    private int idLiaison;
    private String Logiciel;
    private String ActivationKey;
    private String DateAchat;
    private String Client;

    public Licence(int idLiaison, String logiciel, String activationKey, String dateAchat, String client) {
        this.idLiaison = idLiaison;
        Logiciel = logiciel;
        ActivationKey = activationKey;
        DateAchat = dateAchat;
        Client = client;
    }

    public int getIdLiaison() {
        return idLiaison;
    }

    public void setIdLiaison(int idLiaison) {
        this.idLiaison = idLiaison;
    }

    public String getLogiciel() {
        return Logiciel;
    }

    public void setLogiciel(String logiciel) {
        Logiciel = logiciel;
    }

    public String getActivationKey() {
        return ActivationKey;
    }

    public void setActivationKey(String activationKey) {
        ActivationKey = activationKey;
    }

    public String getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(String dateAchat) {
        DateAchat = dateAchat;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }
}
