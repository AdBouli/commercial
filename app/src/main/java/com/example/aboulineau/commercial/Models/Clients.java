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

    public Clients (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public Client getClient()
    {
        return client;
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomClient", client.getNom());
        values.put("prenomClient", client.getPrenom());
        values.put("mailClient", client.getMail());
        values.put("telClient", client.getTel());
        values.put("adresseClient", client.getAdresse());
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
        values.put("adresseClient", client.getAdresse());
        values.put("villeClient", client.getVille().getId());
        values.put("comClient", client.getCom().getId());
        return DB.update("clients", values, "idClient = " + client.getId(), null);
    }

    public List<Client> selectAll()
    {
        Cursor c = DB.rawQuery("SELECT * FROM clients", null);
        ArrayList<Client> clients = new ArrayList<Client>();
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
            setVilleById(c.getInt(6));
            setComById(c.getInt(7));
            clients.add(unClient);
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
            client.setAdresse(c.getString(5));
            setVilleById(c.getInt(6));
            setComById(c.getInt(7));
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
            client.getVille().setId(c.getInt(0));
            client.getVille().setNom(c.getString(1));
            client.getVille().setCode(c.getString(2));
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
            client.getCom().setId(c.getInt(0));
            client.getCom().setNom(c.getString(1));
            client.getCom().setPrenom(c.getString(2));
            client.getCom().setMail(c.getString(3));
            client.getCom().setTel(c.getString(4));
            client.getCom().setLogin(c.getString(5));
        } else
        {
            result = false;
        }
        return result;
    }
}