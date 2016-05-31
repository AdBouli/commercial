package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Rdv;
import com.example.aboulineau.commercial.Models.Entities.Ville;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Rdvs extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Rdv rdv;
    protected Client client;
    protected Commercial com;
    protected Ville ville;

    public Rdvs (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", rdv.getDate());
        values.put("heureRdv", rdv.getHeure());
        values.put("notesRdv", rdv.getNotes());
        values.put("avisRdv", rdv.getAvis());
        values.put("clientRdv", rdv.getClient().getId());
        values.put("comRdv", rdv.getCom().getId());
        return DB.insert("rdvs", null, values);
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", rdv.getDate());
        values.put("heureRdv", rdv.getHeure());
        values.put("notesRdv", rdv.getNotes());
        values.put("avisRdv", rdv.getAvis());
        values.put("clientRdv", rdv.getClient().getId());
        values.put("comRdv", rdv.getCom().getId());
        return DB.update("rdvs", values, "idRdv = " + rdv.getId(), null);
    }

    public List<Rdv> selectAll()
    {
        Cursor c = DB.rawQuery("SELECT * FROM rdvs", null);
        ArrayList<Rdv> rdvs = new ArrayList<Rdv>();
        c.moveToFirst();
        do
        {
            getClientById(c.getInt(5));
            getComById(c.getInt(6));
            rdvs.add(new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com));
        } while (c.moveToNext());
        return rdvs;
    }

    public Boolean getById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM rdvs WHERE idRdv = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            rdv.setId(c.getInt(0));
            rdv.setDate(c.getString(1));
            rdv.setHeure(c.getString(2));
            rdv.setNotes(c.getString(3));
            rdv.setAvis(c.getInt(4));
            getClientById(c.getInt(5));
            rdv.setClient(client);
            getComById(c.getInt(6));
            rdv.setCom(com);
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
            getComById(c.getInt(6));
            client.setCom(com);
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