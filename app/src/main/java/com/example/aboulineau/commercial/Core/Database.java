package com.example.aboulineau.commercial.Core;

import android.database.sqlite.SQLiteDatabase;

import com.example.aboulineau.commercial.Models.Entities.Appel;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Rdv;
import com.example.aboulineau.commercial.Models.Entities.Ville;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Database {

    protected SQLiteDatabase DB;
    protected SQLite SQL;
    protected String table;
    protected String primary;

    protected Commercial com;
    protected Ville ville;
    protected Client client;
    protected Rdv rdv;
    protected Appel appel;

    public Database() {
        com = new Commercial();
        ville = new Ville();
        client = new Client();
        rdv = new Rdv();
        appel = new Appel();
    }

    public void read() {
        DB = SQL.getReadableDatabase();
    }

    public int delete(String table, String primary, int id) {
        return DB.delete(table, primary + " = " + id, null);
    }

    public void write() {
        DB = SQL.getWritableDatabase();
    }

    public void close() {
        DB.close();
    }

    public Commercial getCom() {
        return com;
    }

    public Ville getVille() {
        return ville;
    }

    public Client getClient() {
        return client;
    }

    public Rdv getRdv() {
        return rdv;
    }

    public Appel getAppel() {
        return appel;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String value) {
        table = value;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String value) {
        primary = value;
    }

}