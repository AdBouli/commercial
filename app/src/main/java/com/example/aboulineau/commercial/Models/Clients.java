package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Ville;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Clients extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Client client;
    protected Ville ville;
    protected Commercial com;

    public Clients (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomClient", client.getNom());
        values.put("prenomClient", client.getPrenom());
        values.put("mailClient", client.getMail());
        values.put("telClient", client.getTel());
        values.put("villeClient", client.getVille().getId());
        values.put("comClient", client.getCom().getId());
        return DB.insert("clients", null, values);
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomClient", client.getNom());
        values.put("prenomClient", client.getPrenom());
        values.put("mailClient", client.getMail());
        values.put("telClient", client.getTel());
        values.put("villeClient", client.getVille().getId());
        values.put("comClient", client.getCom().getId());
        return DB.update("clients", values, "idClient = " + client.getId(), null);
    }

    public List<Client> selectAll()
    {
        Cursor c = DB.rawQuery("SELECT * FROM clients", null);
        ArrayList<Client> clients = new ArrayList<Client>();
        c.moveToFirst();
        do
        {
            getVilleById(c.getInt(5));
            getComById(c.getInt(6));
            clients.add(new Client(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), ville, com));
        } while (c.moveToNext());
        return clients;
    }

    public Boolean getById(int id)
    {
        Cursor c = DB.rawQuery("SELECT * FROM clients WHERE idCli = " + id, null);
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
}