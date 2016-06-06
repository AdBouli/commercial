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
            + "idClient INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nomClient TEXT NOT NULL, "
            + "prenomClient TEXT NOT NULL, "
            + "telClient TEXT NOT NULL, "
            + "mailClient TEXT NOT NULL, "
            + "adresseClient TEXT NOT NULL, "
            + "villeClient INTEGER NOT NULL, "
            + "typeClient INTEGER NOT NULL, "
            + "comClient INTEGER NOT NULL);";
    private static final String CREATE_RDVS        = "CREATE TABLE rdvs ("
            + "idRdv INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "dateRdv DATE NOT NULL, "
            + "heureRdv TEXT NOT NULL, "
            + "notesRdv TEXT NOT NULL, "
            + "avisRdv INTEGER NOT NULL, "
            + "clientRdv INTEGER NOT NULL, "
            + "comRdv INTEGER NOT NULL);";
    private static final String CREATE_APPELS      = "CREATE TABLE appels ("
            + "idAppel INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "dateAppel DATE NOT NULL, "
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
            + "(nomClient, prenomClient, telClient, mailClient, adresseClient, villeClient, typeClient, comClient) VALUES"
            + "('Pelon', 'Elie', '0549568432', 'eliep@mail.com', 'rue de Marseille', '1', '1', '1'),"
            + "('Main', 'Alexis', '0549129546', 'alexism@mail.com', 'avenue de Lyon', '2', '1', '2'),"
            + "('Blanc', 'Mickael', '0549778556', 'mickaelb@mail.com', 'rue de Paris', '1', '0', '1'),"
            + "('Boulineau', 'Adrien', '0549896532', 'adrienb@mail.com', 'rue de Bordeaux', '1', '1', '1'),"
            + "('Moricet', 'Luc', '0549195632', 'lucmp@mail.com', 'chemin de Lille', '2', '0', '2'),"
            + "('Landet', 'Jordan', '0549458963', 'jordanl@mail.com', 'place de Poitiers', '2', '1', '2'),"
            + "('Brunet', 'Louis', '0549546135', 'louisb@mail.com', 'rue de Paris', '1', '0', '1'),"
            + "('Cholon', 'Maxime', '0544645135', 'maximecb@mail.com', 'avenue de Paris', '1', '0', '1');";
    private static final String INSERT_RDVS        = "INSERT INTO rdvs"
            + "(dateRdv, heureRdv, notesRdv, avisRdv, clientRdv, comRdv) VALUES"
            + "('2016-06-02', '10:00', 'Premier contact', '5', '1', '1'),"
            + "('2016-06-02', '11:00', 'Manque de confiance', '4', '1', '1'),"
            + "('2016-06-03', '10:00', 'Bien, prise de commande', '7', '1', '1');";
    private static final String INSERT_APPELS      = "INSERT INTO appels"
            + "(dateAppel, heureAppel, notesAppel, avisAppel, clientAppel, comAppel) VALUES"
            + "('2016-06-01', '11.30', 'Prise de rendez-vous', '8', '1', '1'),"
            + "('2016-06-03', '14.30', 'Confirmation commande', '7', '1', '1');";

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    private static final String ALTERS = "";

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
        db.execSQL(INSERT_RDVS);
        db.execSQL(INSERT_APPELS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(SQLite.class.getName(), "Upgrade de la base de données");
        db.execSQL(ALTERS);
    }


}
