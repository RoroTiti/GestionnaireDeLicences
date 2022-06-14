package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionnairedelicence.METIER.CLIENT;
import com.example.gestionnairedelicence.METIER.WINDOWS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class WindowsDAO extends DAO<WINDOWS> {
    private static final String TABLE_WINDOWS = "windows";
    private static final String COL_IDWINDOWS = "idWindows";
    private static final String COL_DATEACHAT = "dateAchat";
    private static final String COL_ACTIVATIONKEY = "activationKey";

    private SQLite sqLite;
    private SQLiteDatabase db;

    public WindowsDAO(Context context) {
        sqLite = new SQLite(context);
    }

    public void open() {
        db = sqLite.getWritableDatabase();
    }

    public void close() {
        sqLite.close();
    }


    @Override
    public int create(WINDOWS uneLicence) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_DATEACHAT, uneLicence.getDateAchat());
        valeurSQL.put(COL_ACTIVATIONKEY, uneLicence.getActivationKey());
        long id = db.insert(TABLE_WINDOWS, null, valeurSQL);
        Log.v("id", String.valueOf(id));
        return ((int) id);
    }

    @Override
    public void update(WINDOWS uneLicence) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_DATEACHAT, uneLicence.getDateAchat());
        valeurSQL.put(COL_ACTIVATIONKEY, uneLicence.getActivationKey());
        db.update(TABLE_WINDOWS, valeurSQL, COL_IDWINDOWS + "=" + uneLicence.getId(), null);
    }

    @Override
    public void delete(WINDOWS uneLicence) {
        db.delete(TABLE_WINDOWS, COL_IDWINDOWS + "=" + uneLicence.getId(), null);
    }

    @Override
    public List<WINDOWS> read() {
        return null;
    }

    public List<WINDOWS> read(Context context) {
        List<WINDOWS> lesLicence;
        Cursor cWindows;
        WINDOWS uneLicence;
        int idWindows;
        String dateAchat;
        String activationKey;
        lesLicence = new ArrayList<WINDOWS>();
        cWindows = db.query(TABLE_WINDOWS, null, null, null, null, null, null);
        cWindows.moveToFirst();
        while (!cWindows.isAfterLast()) {
            idWindows = cWindows.getInt(0);
            dateAchat = cWindows.getString(1);
            activationKey = cWindows.getString(2);
            uneLicence = new WINDOWS(idWindows, dateAchat, activationKey);
            lesLicence.add(uneLicence);
        }
        cWindows.close();
        return lesLicence;
    }
}
