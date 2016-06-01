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
        write();
        long res = DB.insert("rdvs", null, values);
        close();
        return res;
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
        write();
        int res = DB.update("rdvs", values, "idRdv = " + appel.getId(), null);
        close();
        return res;
    }

    public List<Appel> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs", null);
        close();
        ArrayList<Appel> appels = new ArrayList<Appel>();
        c.moveToFirst();
        Appel unAppel = new Appel();
        do
        {
            unAppel.setId(c.getInt(0));
            unAppel.setDate(c.getString(1));
            unAppel.setHeure(c.getString(2));
            unAppel.setNotes(c.getString(3));
            unAppel.setAvis(c.getInt(4));
            setClientById(c.getInt(5));
            setComById(c.getInt(6));
            appels.add(unAppel);
        } while (c.moveToNext());
        c.close();
        return appels;
    }

    public Boolean getById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs WHERE idRdv = " + id, null);
        close();
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            appel.setId(c.getInt(0));
            appel.setDate(c.getString(1));
            appel.setHeure(c.getString(2));
            appel.setNotes(c.getString(3));
            appel.setAvis(c.getInt(4));
            setClientById(c.getInt(5));
            setComById(c.getInt(6));
        } else
        {
            result = false;
        }
        c.close();
        return result;
    }

    public Boolean setClientById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM clients WHERE idCli= " + id, null);
        close();
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            appel.getClient().setId(c.getInt(0));
            appel.getClient().setNom(c.getString(1));
            appel.getClient().setPrenom(c.getString(2));
            appel.getClient().setMail(c.getString(3));
            appel.getClient().setTel(c.getString(4));
            appel.getClient().setAdresse(c.getString(5));
            setVilleClientById(c.getInt(5));
        } else
        {
            result = false;
        }
        c.close();
        return result;
    }

    public Boolean setComById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE idCom = " + id, null);
        close();
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            appel.getCom().setId(c.getInt(0));
            appel.getCom().setNom(c.getString(1));
            appel.getCom().setPrenom(c.getString(2));
            appel.getCom().setMail(c.getString(3));
            appel.getCom().setTel(c.getString(4));
            appel.getCom().setLogin(c.getString(5));
        } else
        {
            result = false;
        }
        c.close();
        return result;
    }

    public Boolean setVilleClientById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM villes WHERE idVille = " + id, null);
        close();
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            appel.getClient().getVille().setId(c.getInt(0));
            appel.getClient().getVille().setNom(c.getString(1));
            appel.getClient().getVille().setCode(c.getString(2));
        } else
        {
            result = false;
        }
        c.close();
        return result;
    }
}