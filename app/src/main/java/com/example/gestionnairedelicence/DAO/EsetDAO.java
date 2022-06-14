package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionnairedelicence.METIER.ESET;
import com.example.gestionnairedelicence.METIER.VILLE;
import com.example.gestionnairedelicence.METIER.WINDOWS;

import java.util.ArrayList;
import java.util.List;

public class EsetDAO extends DAO<ESET> {
    private static final String TABLE_ESET = "eset";
    private static final String COL_IDESET = "idEset";
    private static final String COL_DATEACHAT = "dateAchat";
    private static final String COL_ACTIVATIONKEY = "activationKey";
    private static final String COL_dateDeFinDeValidite = "dateDeFinDeValidite";

    private SQLite sqLite;
    private SQLiteDatabase db;

    public EsetDAO(Context context) {
        sqLite = new SQLite(context);
    }

    public void open() {
        db = sqLite.getWritableDatabase();
    }

    public void close() {
        sqLite.close();
    }

    @Override
    public int create(ESET uneLicence) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_DATEACHAT, uneLicence.getDateAchat());
        valeurSQL.put(COL_ACTIVATIONKEY, uneLicence.getActivationKey());
        valeurSQL.put(COL_dateDeFinDeValidite, uneLicence.getDateDeFInDeValidite());
        long id = db.insert(TABLE_ESET, null, valeurSQL);
        Log.v("id", String.valueOf(id));
        return ((int) id);
    }

    @Override
    public void update(ESET uneLicence) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_DATEACHAT, uneLicence.getDateAchat());
        valeurSQL.put(COL_ACTIVATIONKEY, uneLicence.getActivationKey());
        valeurSQL.put(COL_dateDeFinDeValidite, uneLicence.getDateDeFInDeValidite());
        db.update(TABLE_ESET, valeurSQL, COL_IDESET + "=" + uneLicence.getIdEset(), null);
    }

    @Override
    public void delete(ESET uneLicence) {
        db.delete(TABLE_ESET, COL_IDESET + "=" + uneLicence.getIdEset(), null);
    }

    @Override
    public List<ESET> read() {
        return null;
    }

    public List<ESET> read(Context context) {
        List<ESET> lesLicence;
        Cursor cEset;
        ESET uneLicence;
        int idEset;
        String dateAchat;
        String activationKey;
        String dateDeFinDeValidite;
        lesLicence = new ArrayList<ESET>();
        cEset = db.query(TABLE_ESET, null, null, null, null, null, null);
        cEset.moveToFirst();
        while (!cEset.isAfterLast()) {
            idEset = cEset.getInt(0);
            dateAchat = cEset.getString(1);
            activationKey = cEset.getString(2);
            dateDeFinDeValidite = cEset.getString(3);
            uneLicence = new ESET(idEset, dateAchat, activationKey, dateDeFinDeValidite);
            lesLicence.add(uneLicence);
        }
        cEset.close();
        return lesLicence;
    }
}
