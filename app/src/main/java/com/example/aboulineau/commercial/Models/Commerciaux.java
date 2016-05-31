package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;

import java.util.ArrayList;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Commerciaux extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Commercial com;

    public Commerciaux (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public Commercial getCom()
    {
        return com;
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomCom", com.getNom());
        values.put("prenomCom", com.getPrenom());
        values.put("mailCom", com.getMail());
        values.put("telCom", com.getTel());
        values.put("loginCom", com.getLogin());
        return DB.insert("commerciaux", null, values);
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomCom", com.getNom());
        values.put("prenomCom", com.getPrenom());
        values.put("mailCom", com.getMail());
        values.put("telCom", com.getTel());
        values.put("loginCom", com.getLogin());
        return DB.update("commerciaux", values, "idCom = " + com.getId(), null);
    }

    public ArrayList<Commercial> selectAll()
    {
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux", null);
        ArrayList<Commercial> commerciaux = new ArrayList<Commercial>();
        c.moveToFirst();
        Commercial unCom = new Commercial();
        do
        {
            unCom.setId(c.getInt(0));
            unCom.setNom(c.getString(1));
            unCom.setPrenom(c.getString(2));
            unCom.setMail(c.getString(3));
            unCom.setTel(c.getString(4));
            unCom.setLogin(c.getString(5));
            commerciaux.add(unCom);
        } while (c.moveToNext());
        return commerciaux;
    }

    public Boolean setById(int id)
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

    public Boolean setByLogin(String login)
    {
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE loginCom = " + login, null);
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

    public int setClients()
    {
        Cursor c = DB.rawQuery("SELECT * FROM clients WHERE comClient = " + com.getId(), null);
        c.moveToFirst();
        Client unClient = new Client();
        do
        {
            unClient.setId(c.getInt(0));
            unClient.setNom(c.getString(1));
            unClient.setPrenom(c.getString(2));
            unClient.setMail(c.getString(3));
            unClient.setTel(c.getString(4));
            unClient.setAdresse(c.getString(5));
            Cursor c2 = DB.rawQuery("SELECT * FROM villes WHERE idVille " + c.getInt(6), null);
            if (c2.getCount() == 1)
            {
                unClient.getVille().setId(c2.getInt(0));
                unClient.getVille().setNom(c2.getString(1));
                unClient.getVille().setCode(c2.getString(2));
            }
            unClient.setCom(com);
            com.addClient(unClient);
        } while (c.moveToNext());
        return c.getCount();
    }
}
