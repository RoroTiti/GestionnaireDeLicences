package com.example.gestionnairedelicence.METIER;

public class VILLE {
    private int idVille;
    private String libelle;
    private String cp;

    public VILLE(int idVille, String libelle, String cp) {
        this.idVille = idVille;
        this.libelle = libelle;
        this.cp = cp;
    }

    public VILLE() {
        this.idVille = 0;
        this.libelle = "";
        this.cp = "";
    }

    public int getIdVille() {
        return idVille;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "VILLE{" +
                "idVille=" + idVille +
                ", libelle='" + libelle + '\'' +
                ", cp='" + cp + '\'' +
                '}';
    }
}
