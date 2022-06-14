package com.example.gestionnairedelicence.METIER;

public class LIAISON {
    private int idLiaison;
    private WINDOWS windows;
    private ESET eset;
    private CLIENT client;

    public LIAISON(int idLiaison, WINDOWS windows, ESET eset, CLIENT client) {
        this.idLiaison = idLiaison;
        this.windows = windows;
        this.eset = eset;
        this.client = client;
    }

    public int getIdLiaison() {
        return idLiaison;
    }

    public void setIdLiaison(int idLiaison) {
        this.idLiaison = idLiaison;
    }

    public WINDOWS getWindows() {
        return windows;
    }

    public void setWindows(WINDOWS windows) {
        this.windows = windows;
    }

    public ESET getEset() {
        return eset;
    }

    public void setEset(ESET eset) {
        this.eset = eset;
    }

    public CLIENT getClient() {
        return client;
    }

    public void setClient(CLIENT client) {
        this.client = client;
    }
}
