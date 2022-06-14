package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.METIER.VILLE;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends DAO<CLIENT> {
    private static final String TABLE_CLIENT = "client";
    private static final String COL_IDCLIENT = "idClient";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM = "prenom";
    private static final String COL_ADRESSE = "adresse";
    private static final String COL_TEL = "tel";
    private static final String COL_MAIL = "mail";
    private static final String COL_IDVILLE = "idville";

    private SQLite sqLite;
    private SQLiteDatabase db;

    public ClientDAO(Context context) {
        sqLite = new SQLite(context);
    }

    public void open() {
        db = sqLite.getWritableDatabase();
    }

    public void close() {
        sqLite.close();
    }

    @Override
    public int create(CLIENT unClient) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_NOM, unClient.getNom());
        valeurSQL.put(COL_PRENOM, unClient.getPrenom());
        valeurSQL.put(COL_ADRESSE, unClient.getAdresse());
        valeurSQL.put(COL_TEL, unClient.getTel());
        valeurSQL.put(COL_MAIL, unClient.getMail());
        valeurSQL.put(COL_IDCLIENT, unClient.getVille().getIdVille());
        long id = db.insert(TABLE_CLIENT, null, valeurSQL);
        Log.v("id", String.valueOf(id));
        return ((int) id);
    }

    @Override
    public void update(CLIENT unClient) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_NOM, unClient.getNom());
        valeurSQL.put(COL_PRENOM, unClient.getPrenom());
        valeurSQL.put(COL_ADRESSE, unClient.getAdresse());
        valeurSQL.put(COL_TEL, unClient.getTel());
        valeurSQL.put(COL_MAIL, unClient.getMail());
        valeurSQL.put(COL_IDCLIENT, unClient.getVille().getIdVille());
        db.update(TABLE_CLIENT, valeurSQL, COL_IDCLIENT + "=" + unClient.getId(), null);
    }

    @Override
    public void delete(CLIENT unClient) {
        db.delete(TABLE_CLIENT, COL_IDCLIENT + "=" + unClient.getId(), null);
    }

    @Override
    public List<CLIENT> read() {
        return null;
    }

    public List<CLIENT> read(Context context) {
        List<CLIENT> lesClients;
        Cursor cClient;
        CLIENT unClient;
        int idClient;
        String nom;
        String prenom;
        String adresse;
        String tel;
        String mail;
        int idVille;
        VILLE uneVille;
        VilleDAO uneVilleDAO;

        lesClients = new ArrayList<CLIENT>();
        cClient = db.query(TABLE_CLIENT, null, null, null, null, null, COL_NOM);
        cClient.moveToFirst();
        while (!cClient.isAfterLast()) {
            idClient = cClient.getInt(0);
            nom = cClient.getString(1);
            prenom = cClient.getString(2);
            adresse = cClient.getString(3);
            tel = cClient.getString(4);
            mail = cClient.getString(5);
            idVille = cClient.getInt(6);
            uneVilleDAO = new VilleDAO(context);
            uneVilleDAO.open();
            uneVille = uneVilleDAO.read(idVille);
            unClient = new CLIENT(idClient, nom, prenom, adresse, tel, mail, uneVille);
            lesClients.add(unClient);
            cClient.moveToNext();
        }
        cClient.close();

        return lesClients;
    }
}
