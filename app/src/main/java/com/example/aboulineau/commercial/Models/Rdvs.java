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

    public Rdvs (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public Rdv getRdv()
    {
        return rdv;
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
        Rdv unRdv = new Rdv();
        do
        {
            unRdv.setId(c.getInt(0));
            unRdv.setDate(c.getString(1));
            unRdv.setHeure(c.getString(2));
            unRdv.setNotes(c.getString(3));
            unRdv.setAvis(c.getInt(4));
            setClientById(c.getInt(5));
            setComById(c.getInt(6));
            rdvs.add(unRdv);
        } while (c.moveToNext());
        return rdvs;
    }

    public Boolean setById(int id)
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
            setClientById(c.getInt(5));
            setComById(c.getInt(6));
        } else
        {
            result = false;
        }
        return result;
    }

    public Boolean setClientById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM clients WHERE idCli= " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            rdv.getClient().setId(c.getInt(0));
            rdv.getClient().setNom(c.getString(1));
            rdv.getClient().setPrenom(c.getString(2));
            rdv.getClient().setMail(c.getString(3));
            rdv.getClient().setTel(c.getString(4));
            rdv.getClient().setAdresse(c.getString(5));
            setVilleById(c.getInt(6));
            setComById(c.getInt(7));
        } else
        {
            result = false;
        }
        return result;
    }

    public Boolean setComById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE idCom = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            rdv.getCom().setId(c.getInt(0));
            rdv.getCom().setNom(c.getString(1));
            rdv.getCom().setPrenom(c.getString(2));
            rdv.getCom().setMail(c.getString(3));
            rdv.getCom().setTel(c.getString(4));
            rdv.getCom().setLogin(c.getString(5));
        } else
        {
            result = false;
        }
        return result;
    }

    public Boolean setVilleById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM villes WHERE idVille = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            rdv.getClient().getVille().setId(c.getInt(0));
            rdv.getClient().getVille().setNom(c.getString(1));
            rdv.getClient().getVille().setCode(c.getString(2));
        } else
        {
            result = false;
        }
        return result;
    }




}