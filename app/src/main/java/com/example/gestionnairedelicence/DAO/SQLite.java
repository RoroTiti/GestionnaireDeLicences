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
                    "libelle varchar(255)," +
                    "cp varchar(255))");
        } catch (Exception e) {
            Log.v("Erreur VILLE", e.toString());
        }

        //Creation Table CLIENT //
        try {
            db.execSQL("CREATE TABLE CLIENT (" +
                    "idClient integer primary key autoincrement," +
                    "nom varchar(255)," +
                    "prenom varchar(255)," +
                    "adresse varchar (255)," +
                    "tel varchar(2555)," +
                    "mail varchar(255)," +
                    "idVille integer," +
                    "foreign key (idVille) references VILLE (idVille))");
        } catch (Exception e) {
            Log.v("Erreur CLIENT", e.toString());
        }

        //Creation Table WINDOWS //
        try {
            db.execSQL("CREATE TABLE WINDOWS(" +
                    "idWindows integer primary key autoincrement," +
                    "dateAchat varchar(255)," +
                    "activationKey varchar(255))");
        } catch (Exception e) {
            Log.v("Erreur WINDOWS", e.toString());
        }

        //Creation Table ESET //
        try {
            db.execSQL("CREATE TABLE ESET(" +
                    "idEset integer primary key autoincrement," +
                    "dateAchat varchar(255)," +
                    "activationKey varchar(255)," +
                    "dateDeFinDeValidite varchar(255))");
        } catch (Exception e) {
            Log.v("Erreur ESET", e.toString());
        }

        //Creation Table LIAISON
        try {
            db.execSQL("CREATE TABLE LIAISON(" +
                    "idLiaison integer primary key autoincrement," +
                    "idWindows integer," +
                    "idClient integer," +
                    "idEset integer," +
                    "foreign key (idClient) references CLIENT (idClient)," +
                    "foreign key (idWindows) references WINDOWS (idWindows)," +
                    "foreign key (idEset) references ESET (idEset))");
        } catch (Exception e) {
            Log.v("Erreur LIAISON", e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}