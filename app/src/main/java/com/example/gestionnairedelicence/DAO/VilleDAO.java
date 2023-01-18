package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gestionnairedelicence.METIER.VILLE;

import java.util.ArrayList;
import java.util.List;

public class VilleDAO extends DAO<VILLE> {
    private static final String TABLE_VILLE = "ville";
    private static final String COL_IDVILLE = "idVille";
    private static final String COL_LIBELLE = "libelle";
    private static final String COL_CP = "cp";

    private SQLite sqLite;
    private SQLiteDatabase db;

    public VilleDAO(Context context) {
        sqLite = new SQLite(context);
    }

    public void open() {
        db = sqLite.getWritableDatabase();
    }

    public void close() {
        sqLite.close();
    }

    @Override
    public int create(VILLE uneVille) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_LIBELLE, uneVille.getLibelle());
        valeurSQL.put(COL_CP, uneVille.getCp());
        long id = db.insert(TABLE_VILLE, null, valeurSQL);
        return ((int) id);
    }

    @Override
    public void update(VILLE uneVille) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_LIBELLE, uneVille.getLibelle());
        valeurSQL.put(COL_CP, uneVille.getCp());
        db.update(TABLE_VILLE, valeurSQL, COL_IDVILLE + "=" + uneVille.getIdVille(), null);
    }

    @Override
    public void delete(VILLE uneVille) {
        db.delete(TABLE_VILLE, COL_IDVILLE + "=" + uneVille.getIdVille(), null);
    }

    @Override
    public List<VILLE> read() {
        List<VILLE> lesVilles;
        Cursor cVille;
        VILLE uneVille;
        int idVille;
        String libelle;
        String cp;

        lesVilles = new ArrayList<>();
        cVille = db.query(TABLE_VILLE, null, null, null, null, null, COL_LIBELLE);

        while (cVille.moveToNext()) {
            idVille = cVille.getInt(0);
            libelle = cVille.getString(1);
            cp = cVille.getString(2);
            uneVille = new VILLE(idVille, libelle, cp);
            lesVilles.add(uneVille);
        }

        cVille.close();

        return lesVilles;
    }

    @Override
    public VILLE read(int id) {
        Cursor cVille;
        VILLE uneVille;
        String libelle;
        String cp;

        cVille = db.query(TABLE_VILLE, null, COL_IDVILLE + "=" + id, null, null, null, null);

        if (cVille.moveToNext()) {
            id = cVille.getInt(0);
            libelle = cVille.getString(1);
            cp = cVille.getString(2);
            uneVille = new VILLE(id, libelle, cp);
        } else {
            uneVille = null;
        }

        cVille.close();

        return uneVille;
    }
}
