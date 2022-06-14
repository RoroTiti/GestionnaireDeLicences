package com.example.gestionnairedelicence.METIER;

public class CLIENT {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String mail;
    private VILLE ville;

    public CLIENT(int id, String nom, String prenom, String adresse, String tel, String mail, VILLE ville) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.ville = ville;
    }

    public CLIENT() {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.tel = " ";
        this.mail = " ";
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public VILLE getVille() {
        return ville;
    }

    public void setVille(VILLE ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "CLIENT{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", ville=" + ville +
                '}';
    }
}
