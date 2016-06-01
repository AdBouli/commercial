package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Commerciaux extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    public Commerciaux (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomCom", com.getNom());
        values.put("prenomCom", com.getPrenom());
        values.put("mailCom", com.getMail());
        values.put("telCom", com.getTel());
        values.put("loginCom", com.getLogin());
        write();
        long res = DB.insert("commerciaux", null, values);
        close();
        return res;
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomCom", com.getNom());
        values.put("prenomCom", com.getPrenom());
        values.put("mailCom", com.getMail());
        values.put("telCom", com.getTel());
        values.put("loginCom", com.getLogin());
        write();
        int res = DB.update("commerciaux", values, "idCom = " + com.getId(), null);
        close();
        return res;
    }

    public ArrayList<Commercial> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux", null);
        ArrayList<Commercial> commerciaux = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                com.setId(c.getInt(0));
                com.setNom(c.getString(1));
                com.setPrenom(c.getString(2));
                com.setMail(c.getString(3));
                com.setTel(c.getString(4));
                com.setLogin(c.getString(5));
                commerciaux.add(com);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return commerciaux;
    }

    public Boolean setById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE idCom = " + id, null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            c.moveToFirst();
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
        c.close();
        close();
        return result;
    }

    public Boolean setByLogin(String login) {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE loginCom = '" + login + "';", null);
        Boolean result;
        if (c.getCount() == 1)
        {
            result = true;
            c.moveToFirst();
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
        c.close();
        close();
        return result;
    }

    public int setClients()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM clients WHERE comCli = " + com.getId(), null);
        int res = c.getCount();
        List<Client> clients = new ArrayList<>();
        if (res > 0)
        {
            c.moveToFirst();
            Cursor c2;
            do
            {
                Client unClient = new Client();
                client.setId(c.getInt(0));
                client.setNom(c.getString(1));
                client.setPrenom(c.getString(2));
                client.setMail(c.getString(3));
                client.setTel(c.getString(4));
                client.setAdresse(c.getString(5));
                c2 = DB.rawQuery("SELECT * FROM villes WHERE idVille = " + c.getInt(6), null);
                if (c2.getCount() == 1)
                {
                    c2.moveToFirst();
                    ville.setId(c2.getInt(0));
                    ville.setNom(c2.getString(1));
                    ville.setCode(c2.getString(2));
                }
                client.setVille(ville);
                c2.close();
                clients.add(client);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return res;
    }

    public Boolean loginExist(String login)
    {
        read();
        Cursor c = DB.rawQuery("SELECT idCom FROM commerciaux WHERE loginCom = '" + login + "';", null);
        Boolean result = (c.getCount() > 0);
        c.close();
        close();
        return result;
    }
}
