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
            + "idCom INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nomCom TEXT NOT NULL, "
            + "prenomCom TEXT NOT NULL, "
            + "mailCom TEXT NOT NULL, "
            + "telCom TEXT NOT NULL, "
            + "loginCom TEXT NOT NULL);";
    private static final String CREATE_VILLES      = "CREATE TABLE villes ("
            + "idVille INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nomVille TEXT NOT NULL, "
            + "codeVille TEXT NOT NULL);";
    private static final String CREATE_CLIENTS     = "CREATE TABLE clients ("
            + "idCli INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nomCli TEXT NOT NULL, "
            + "prenomCli TEXT NOT NULL, "
            + "telCli TEXT NOT NULL, "
            + "mailCli TEXT NOT NULL, "
            + "adresseCli TEXT NOT NULL, "
            + "villeCli INTEGER NOT NULL, "
            + "typeCli INTEGER NOT NULL, "
            + "comCli INTEGER NOT NULL);";
    private static final String CREATE_RDVS        = "CREATE TABLE rdvs ("
            + "idRdv INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "dateRdv TEXT NOT NULL, "
            + "heureRdv TEXT NOT NULL, "
            + "notesRdv TEXT NOT NULL, "
            + "avisRdv INTEGER NOT NULL, "
            + "clientRdv INTEGER NOT NULL, "
            + "comRdv INTEGER NOT NULL);";
    private static final String CREATE_APPELS      = "CREATE TABLE appels ("
            + "idAppel INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "dateAppel TEXT NOT NULL, "
            + "heureAppel TEXT NOT NULL, "
            + "notesAppel TEXT NOT NULL, "
            + "avisAppel INTEGER NOT NULL, "
            + "clientAppel INTEGER NOT NULL, "
            + "comAppel INTEGER NOT NULL);";

    // DROP TABLES
    private static final String DROP_COMMERCIAUX = "DROP TABLE IF EXISTS commerciaux;";
    private static final String DROP_VILLES      = "DROP TABLE IF EXISTS villes;";
    private static final String DROP_CLIENTS     = "DROP TABLE IF EXISTS clients;";
    private static final String DROP_RDVS        = "DROP TABLE IF EXISTS rdvs;";
    private static final String DROP_APPELS      = "DROP TABLE IF EXISTS appels;";

    // INSERT TEST
    private static final String INSERT_COMMERCIAUX = "INSERT INTO commerciaux"
            + "(nomCom, prenomCom, mailCom, telCom, loginCom) VALUES"
            + "('Richardeau', 'Sebastien', 'richardeaus@gmail.com', '0612345678', 'seb'),"
            + "('Dejeans', 'Pierre', 'pierre.dejeans@gmail.com', '0612345678', 'peyo');";
    private static final String INSERT_VILLES      = "INSERT INTO villes"
            + "(nomVille, codeVille) VALUES"
            + "('Loudun', '86200'),"
            + "('Poitiers', '86000');";
    private static final String INSERT_CLIENTS     = "INSERT INTO clients"
            + "(nomCli, prenomCli, mailCli, telCli, adresseCli, villeCli, typeCli, comCli) VALUES"
            + "('Pelon', 'Elie', 'eliep@mail.com', '0549568432', 'rue de Marseille', '1', '1', '1'),"
            + "('Main', 'Alexis', 'alexism@mail.com', '0549129546', 'avenue de Lyon', '2', '1', '2'),"
            + "('Blanc', 'Mickael', 'mickaelb@mail.com', '0549778556', 'rue de Paris', '1', '0', '1'),"
            + "('Boulineau', 'Adrien', 'adrienb@mail.com', '0549896532', 'rue de Bordeaux', '1', '1', '1'),"
            + "('Moricet', 'Luc', 'lucmp@mail.com', '0549195632', 'chemin de Lille', '2', '0', '2'),"
            + "('Landet', 'Jordan', 'jordanl@mail.com', '0549458963', 'place de Poitiers', '2', '1', '2'),"
            + "('Brunet', 'Louis', 'louisb@mail.com', '0549546135', 'rue de Paris', '1', '0', '1'),"
            + "('Cholon', 'Maxime', 'maximecb@mail.com', '0544645135', 'avenue de Paris', '1', '0', '1');";

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
