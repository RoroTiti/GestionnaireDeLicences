package com.example.gestionnairedelicence.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.gestionnairedelicence.METIER.*;

import java.util.ArrayList;

public class LiaisonDAO extends DAO<LIAISON> {
    private final Context context;
    static final String TABLE_LIAISON = "liaison";
    private static final String COL_IDLIAISON = "idLiaison";
    private static final String COL_IDWINDOWS = "idWindows";
    private static final String COL_IDESET = "idEset";
    private static final String COL_IDCLIENT = "idClient";

    private final SQLite sqLite;
    private SQLiteDatabase db;

    public LiaisonDAO(Context context) {
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
    public int create(LIAISON uneLiaison) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_IDWINDOWS, uneLiaison.getWindows() != null ? uneLiaison.getWindows().getIdWindows() : null);
        valeurSQL.put(COL_IDESET, uneLiaison.getEset() != null ? uneLiaison.getEset().getIdEset() : null);
        valeurSQL.put(COL_IDCLIENT, uneLiaison.getClient().getIdClient());

        long id = db.insert(TABLE_LIAISON, null, valeurSQL);

        return ((int) id);
    }

    @Override
    public void update(LIAISON uneLiaison) {
        ContentValues valeurSQL = new ContentValues();
        valeurSQL.put(COL_IDWINDOWS, uneLiaison.getWindows() != null ? uneLiaison.getWindows().getIdWindows() : null);
        valeurSQL.put(COL_IDESET, uneLiaison.getEset() != null ? uneLiaison.getEset().getIdEset() : null);
        valeurSQL.put(COL_IDCLIENT, uneLiaison.getClient().getIdClient());

        db.update(TABLE_LIAISON, valeurSQL, COL_IDLIAISON + "=" + uneLiaison.getIdLiaison(), null);
    }

    @Override
    public void delete(LIAISON uneLiaison) {
        db.delete(TABLE_LIAISON, COL_IDLIAISON + "=" + uneLiaison.getIdLiaison(), null);
    }

    @Override
    public ArrayList<LIAISON> read() {
        ArrayList<LIAISON> lesLiaisons = new ArrayList<>();

        Cursor cWindows = db.query(TABLE_LIAISON, null, null, null, null, null, null);

        while (cWindows.moveToNext()) {
            int idLiaison = cWindows.getInt(0);

            int idWindows = cWindows.getInt(1);
            WindowsDAO windowsDAO = new WindowsDAO(context);
            windowsDAO.open();
            WINDOWS windows = windowsDAO.read(idWindows);
            windowsDAO.close();

            int idClient = cWindows.getInt(2);
            ClientDAO clientDAO = new ClientDAO(context);
            clientDAO.open();
            CLIENT client = clientDAO.read(idClient);
            clientDAO.close();

            int idEset = cWindows.getInt(3);
            EsetDAO esetDAO = new EsetDAO(context);
            esetDAO.open();
            ESET eset = esetDAO.read(idEset);
            esetDAO.close();

            LIAISON uneLicence = new LIAISON(idLiaison, windows, eset, client);
            lesLiaisons.add(uneLicence);
        }

        cWindows.close();

        return lesLiaisons;
    }

    public LIAISON read(int idLiaison) {
        LIAISON liaison = null;

        Cursor cWindows = db.query(TABLE_LIAISON, null, COL_IDLIAISON + "=" + idLiaison, null, null, null, null);

        while (cWindows.moveToNext()) {
            int idWindows = cWindows.getInt(1);
            WindowsDAO windowsDAO = new WindowsDAO(context);
            windowsDAO.open();
            WINDOWS windows = windowsDAO.read(idWindows);
            windowsDAO.close();

            int idClient = cWindows.getInt(2);
            ClientDAO clientDAO = new ClientDAO(context);
            clientDAO.open();
            CLIENT client = clientDAO.read(idClient);
            clientDAO.close();

            int idEset = cWindows.getInt(3);
            EsetDAO esetDAO = new EsetDAO(context);
            esetDAO.open();
            ESET eset = esetDAO.read(idEset);
            esetDAO.close();

            liaison = new LIAISON(idLiaison, windows, eset, client);
        }

        cWindows.close();

        return liaison;
    }
}
