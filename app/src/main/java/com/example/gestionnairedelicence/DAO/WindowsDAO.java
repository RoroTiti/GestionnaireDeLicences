package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionnairedelicence.METIER.WINDOWS;

import java.util.ArrayList;

public class WindowsDAO extends DAO<WINDOWS> {
    private static final String TABLE_WINDOWS = "windows";
    private static final String COL_IDWINDOWS = "idWindows";
    private static final String COL_DATEACHAT = "dateAchat";
    private static final String COL_ACTIVATIONKEY = "activationKey";

    private final SQLite sqLite;
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
        return ((int) id);
    }

    @Override
    public void update(WINDOWS uneLicence) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_DATEACHAT, uneLicence.getDateAchat());
        valeurSQL.put(COL_ACTIVATIONKEY, uneLicence.getActivationKey());
        db.update(TABLE_WINDOWS, valeurSQL, COL_IDWINDOWS + "=" + uneLicence.getIdWindows(), null);
    }

    @Override
    public void delete(WINDOWS uneLicence) {
        db.delete(TABLE_WINDOWS, COL_IDWINDOWS + "=" + uneLicence.getIdWindows(), null);
    }

    @Override
    public ArrayList<WINDOWS> read() {
        ArrayList<WINDOWS> lesLicences = new ArrayList<>();

        Cursor cursor = db.query(TABLE_WINDOWS, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int idWindows = cursor.getInt(0);
            String dateAchat = cursor.getString(1);
            String activationKey = cursor.getString(2);
            WINDOWS uneLicence = new WINDOWS(idWindows, dateAchat, activationKey);
            lesLicences.add(uneLicence);
        }

        cursor.close();

        return lesLicences;
    }

    public WINDOWS read(int idWindows) {
        WINDOWS licence = null;

        Cursor cursor = db.query(TABLE_WINDOWS, null, COL_IDWINDOWS + "=" + idWindows, null, null, null, null);

        if (cursor.moveToNext()) {
            String dateAchat = cursor.getString(1);
            String activationKey = cursor.getString(2);
            licence = new WINDOWS(idWindows, dateAchat, activationKey);
        }

        cursor.close();

        return licence;
    }
}
