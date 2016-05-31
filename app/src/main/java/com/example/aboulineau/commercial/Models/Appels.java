package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Appel;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Ville;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Appels extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Appel appel;
    protected Client client;
    protected Commercial com;
    protected Ville ville;

    public Appels (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", appel.getDate());
        values.put("heureRdv", appel.getHeure());
        values.put("notesRdv", appel.getNotes());
        values.put("avisRdv", appel.getAvis());
        values.put("clientRdv", appel.getClient().getId());
        values.put("comRdv", appel.getCom().getId());
        return DB.insert("rdvs", null, values);
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", appel.getDate());
        values.put("heureRdv", appel.getHeure());
        values.put("notesRdv", appel.getNotes());
        values.put("avisRdv", appel.getAvis());
        values.put("clientRdv", appel.getClient().getId());
        values.put("comRdv", appel.getCom().getId());
        return DB.update("rdvs", values, "idRdv = " + appel.getId(), null);
    }

    public List<Appel> selectAll()
    {
        Cursor c = DB.rawQuery("SELECT * FROM rdvs", null);
        ArrayList<Appel> appels = new ArrayList<Appel>();
        c.moveToFirst();
        do
        {
            getClientById(c.getInt(5));
            getComById(c.getInt(6));
            appels.add(new Appel(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com));
        } while (c.moveToNext());
        return appels;
    }

    public Boolean getById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM rdvs WHERE idRdv = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            appel.setId(c.getInt(0));
            appel.setDate(c.getString(1));
            appel.setHeure(c.getString(2));
            appel.setNotes(c.getString(3));
            appel.setAvis(c.getInt(4));
            getClientById(c.getInt(5));
            appel.setClient(client);
            getComById(c.getInt(6));
            appel.setCom(com);
        } else
        {
            result = false;
        }
        return result;
    }

    public Boolean getClientById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM clients WHERE idCli= " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            client.setId(c.getInt(0));
            client.setNom(c.getString(1));
            client.setPrenom(c.getString(2));
            client.setMail(c.getString(3));
            client.setTel(c.getString(4));
            getVilleById(c.getInt(5));
            client.setVille(ville);
        } else
        {
            result = false;
        }
        return result;
    }

    public Boolean getComById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE idCom = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            com.setId(c.getInt(0));
            com.setNom(c.getString(1));
            com.setPrenom(c.getString(2));
            com.setMail(c.getString(3));
            com.setTel(c.getString(4));
            com.setLogin(c.getString(5));
        } else
        {
            result = false;
        }
        return result;
    }

    public Boolean getVilleById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM villes WHERE idVille = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            ville.setId(c.getInt(0));
            ville.setNom(c.getString(1));
            ville.setCode(c.getString(2));
        } else
        {
            result = false;
        }
        return result;
    }
}