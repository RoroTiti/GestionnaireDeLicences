package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.METIER.VILLE;

import java.util.ArrayList;

import static com.example.gestionnairedelicence.DAO.LiaisonDAO.TABLE_LIAISON;

public class ClientDAO extends DAO<CLIENT> {
    private final Context context;
    private static final String TABLE_CLIENT = "client";
    private static final String COL_IDCLIENT = "idClient";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM = "prenom";
    private static final String COL_ADRESSE = "adresse";
    private static final String COL_TEL = "tel";
    private static final String COL_MAIL = "mail";
    private static final String COL_IDVILLE = "idville";

    private final SQLite sqLite;
    private SQLiteDatabase db;

    public ClientDAO(Context context) {
        this.context = context;
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
        valeurSQL.put(COL_IDVILLE, unClient.getVille().getIdVille());
        long id = db.insert(TABLE_CLIENT, null, valeurSQL);
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
        valeurSQL.put(COL_IDVILLE, unClient.getVille().getIdVille());
        db.update(TABLE_CLIENT, valeurSQL, COL_IDCLIENT + "=" + unClient.getIdClient(), null);
    }

    @Override
    public void delete(CLIENT unClient) {
        db.delete(TABLE_LIAISON, COL_IDCLIENT + "=" + unClient.getIdClient(), null);
        db.delete(TABLE_CLIENT, COL_IDCLIENT + "=" + unClient.getIdClient(), null);
    }

    @Override
    public ArrayList<CLIENT> read() {
        VilleDAO uneVilleDAO;

        ArrayList<CLIENT> lesClients = new ArrayList<>();
        Cursor cClient = db.query(TABLE_CLIENT, null, null, null, null, null, COL_NOM);

        while (cClient.moveToNext()) {
            int idClient = cClient.getInt(0);
            String nom = cClient.getString(1);
            String prenom = cClient.getString(2);
            String adresse = cClient.getString(3);
            String tel = cClient.getString(4);
            String mail = cClient.getString(5);

            int idVille = cClient.getInt(6);
            uneVilleDAO = new VilleDAO(context);
            uneVilleDAO.open();
            VILLE uneVille = uneVilleDAO.read(idVille);
            uneVilleDAO.close();

            CLIENT unClient = new CLIENT(idClient, nom, prenom, adresse, tel, mail, uneVille);

            lesClients.add(unClient);
        }

        cClient.close();

        return lesClients;
    }

    public CLIENT read(int idClient) {
        CLIENT client = null;

        Cursor cursor = db.query(TABLE_CLIENT, null, COL_IDCLIENT + "=" + idClient, null, null, null, COL_NOM);

        while (cursor.moveToNext()) {
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String adresse = cursor.getString(3);
            String tel = cursor.getString(4);
            String mail = cursor.getString(5);

            int idVille = cursor.getInt(6);
            VilleDAO uneVilleDAO = new VilleDAO(context);
            uneVilleDAO.open();
            VILLE uneVille = uneVilleDAO.read(idVille);
            uneVilleDAO.close();

            client = new CLIENT(idClient, nom, prenom, adresse, tel, mail, uneVille);
        }

        cursor.close();

        return client;
    }
}
