package com.example.aboulineau.commercial.Core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class SQLite extends SQLiteOpenHelper
{
    // CREATE TABLES
    private static final String CREATE_COMMERCIAUX = "CREATE TABLE commerciaux ("
            + "idCom INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nomCom TEXT NOT NULL,"
            + "prenomCom TEXT NOT NULL,"
            + "mailCom TEXT NOT NULL,"
            + "telCom TEXT NOT NULL,"
            + "loginCom TEXT NOT NULL)";
    private static final String CREATE_VILLES      = "CREATE TABLE villes ("
            + "idVille INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nomVille TEXT NOT NULL,"
            + "codeVille TEXT NOT NULL)";
    private static final String CREATE_CLIENTS     = "CREATE TABLE clients ("
            + "idCli INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nomCli TEXT NOT NULL,"
            + "prenomCli TEXT NOT NULL,"
            + "telCli TEXT NOT NULL,"
            + "mailCli TEXT NOT NULL,"
            + "adresseCli TEXT NOT NULL,"
            + "villeCli INTEGER NOT NULL,"
            + "typeCli INTEGER NOT NULL"
            + "comCli INTEGER NOT NULL)";
    private static final String CREATE_RDVS        = "CREATE TABLE rdvs ("
            + "idRdv INTEGER PRIMARY KEY AUTOINCREMENT"
            + "dateRdv TEXT NOT NULL,"
            + "heureRdv TEXT NOT NULL,"
            + "notesRdv TEXT NOT NULL,"
            + "avisRdv INTEGER NOT NULL,"
            + "clientRdv INTEGER NOT NULL,"
            + "comRdv INTEGER NOT NULL)";
    private static final String CREATE_APPELS      = "CREATE TABLE appels ("
            + "idAppel INTEGER PRIMARY KEY AUTOINCREMENT"
            + "dateAppel TEXT NOT NULL,"
            + "heureAppel TEXT NOT NULL,"
            + "notesAppel TEXT NOT NULL,"
            + "avisAppel INTEGER NOT NULL,"
            + "clientAppel INTEGER NOT NULL,"
            + "comAppel INTEGER NOT NULL)";

    // DROP TABLES
    private static final String DROP_COMMERCIAUX = "DROP TABLE IF EXISTS commerciaux";
    private static final String DROP_VILLES      = "DROP TABLE IF EXISTS villes";
    private static final String DROP_CLIENTS     = "DROP TABLE IF EXISTS clients";
    private static final String DROP_RDVS        = "DROP TABLE IF EXISTS rdvs";
    private static final String DROP_APPELS      = "DROP TABLE IF EXISTS appels";

    // INSERT TEST
    private static final String INSERT_COMMERCIAUX = "INSERT INTO commerciaux"
            + "(nomCom, prenomCom, mailCom, telCom, loginCom) VALUES"
            + "('Richardeau', 'Sebastien', 'richardeaus@gmail.com', '0612345678', 'seb')";
    private static final String INSERT_VILLES      = "INSERT INTO villes"
            + "(nomVille, codeVille) VALUES"
            + "('Paris', '75000')";
    private static final String INSERT_CLIENTS     = "INSERT INTO clients"
            + "(nomClient, prenomClient, mailClient, telClient, adresseClient, villeClient, comClient) VALUES"
            + "('client1', 'client1', 'mail1', '0101010101', 'rue de Paris', '1', '1')"
            + "('client2', 'client2', 'mail2', '0101010101', 'rue de Paris', '1', '1')"
            + "('client3', 'client3', 'mail3', '0101010101', 'rue de Paris', '1', '1')"
            + "('client4', 'client4', 'mail4', '0101010101', 'rue de Paris', '1', '1')"
            + "('client5', 'client5', 'mail5', '0101010101', 'rue de Paris', '1', '1')"
            + "('client6', 'client6', 'mail6', '0101010101', 'rue de Paris', '1', '1')"
            + "('client7', 'client7', 'mail7', '0101010101', 'rue de Paris', '1', '1')";

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_COMMERCIAUX);
        db.execSQL(CREATE_VILLES);
        db.execSQL(CREATE_CLIENTS);
        db.execSQL(CREATE_RDVS);
        db.execSQL(CREATE_APPELS);
        // datas test
        db.execSQL(INSERT_COMMERCIAUX);
        db.execSQL(INSERT_VILLES);
        db.execSQL(INSERT_CLIENTS);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(SQLite.class.getName(), "Upgrade de la base de données");
        db.execSQL(DROP_COMMERCIAUX);
        db.execSQL(DROP_VILLES);
        db.execSQL(DROP_CLIENTS);
        db.execSQL(DROP_RDVS);
        db.execSQL(DROP_APPELS);
        onCreate(db);
    }


}
