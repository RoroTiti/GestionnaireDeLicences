package com.example.gestionnairedelicence.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GestionDeLicence";
    private static final int DATABASE_VERSION = 1;

    public SQLite(Context leContexte) {
        super(leContexte, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Creation Table VILLE //
        try {
            db.execSQL("CREATE TABLE VILLE (" +
                    "idVille integer primary key autoincrement," +
                    "libelle varchar(50)," +
                    "cp varchar(5))");
            db.execSQL("insert into VILLE(libelle, cp ) values ('Assais-Les-Jumeaux', '79600')");
            db.execSQL("insert into VILLE(libelle, cp) values ('Loudun','86200')");
            Log.v("SQLite ok", "Table VILLE est créée");
        } catch (Exception e) {
            Log.v("Erreur c table VILLE", e.toString());
        }

        //Creation Table CLIENT //
        try {
            db.execSQL("CREATE TABLE CLIENT (" +
                    "idClient integer primary key autoincrement," +
                    "nom VARCHAR(50)," +
                    "prenom VARCHAR(50)," +
                    "adresse VARCHAR (100)," +
                    "tel VARCHAR(10)," +
                    "mail VARCHAR(50) ," +
                    "idille  integer , foreign key (idVille) references VILLE (idVille))");
            db.execSQL("insert into CLIENT (nom, prenom, adresse, tel , mail) VALUES ('GIRARD','Florent', '3 Bis la patte d oie', '0641172984', 'service-technique@girard-florent.fr', 1)");
            db.execSQL("insert into CLIENT (nom, prenom, adresse, tel, mail) VALUES ('ROUILLON', 'Lea', '12 avenue des champs élysée', '0612345678', 'roullonlea@gmail.com',2)");
            Log.v("SQLite ok ", "Table CLIENT est créée");
        } catch (Exception e) {
            Log.v("Erreur c table CLIENT", e.toString());
        }

        //Creation Table WINDOWS //
        try {
            db.execSQL("CREATE TABLE WINDOWS(" +
                    "idWindows integer primary key autoincrement," +
                    "dateAchat VARCHAR(10)," +
                    "activationKey VARCHAR(30))");
            db.execSQL("insert into WINDOWS(dateAchat, activationKey) VALUES ('11/10/2021',' W269N-WFGWX-YVC9B-4J6C9-T83GX' )");
            db.execSQL("insert into WINDOWS(dateAchat, activationKey) VALUES ('11/10/2021',' W269N-WFGWX-YVC9B-4J6C9-T83GX')");
        } catch (Exception e) {
            Log.v("Erreur c table WINDOWS", e.toString());
        }

        //Creation Table ESET //
        try {
            db.execSQL("CREATE TABLE ESET(" +
                    "idESET integer primary key autoincrement," +
                    "dateAchat VARCHAR(10)," +
                    "activationKey VARCHAR(30)," +
                    "dateDeFinDeValidite VARCHAR(10))");
            db.execSQL("insert into ESET(dateAchat, activationKey, dateDeFinDeValidite) VALUES ('11/10/2021',' W269N-WFGWX-YVC9B-4J6C9-T83GX', '11/10/2022')");
            db.execSQL("insert into ESET(dateAchat, activationKey, dateDeFinDeValidite) VALUES ('11/10/2021',' W269N-WFGWX-YVC9B-4J6C9-T83GX', '11/10/2022')");
        } catch (Exception e) {
            Log.v("Erreur c table ESET", e.toString());
        }

        //Creation Table LIAISON
        try {
            db.execSQL("CREATE TABLE LIAISON(" +
                    "idIndows integer primary key," +
                    "idClient integer primary key ," +
                    "idEset integer primary key," +
                    "foreign key (idClient) references CLIENT (idClient)," +
                    "foreign key (idWindows) references WINDOWS (idWindows)," +
                    "foreign key (idEset) references ESET(idEset))");
        } catch (Exception e) {
            Log.v("Erreur ct LIAISON", e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}