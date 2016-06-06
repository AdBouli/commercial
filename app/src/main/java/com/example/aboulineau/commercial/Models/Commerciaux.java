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
public class Commerciaux extends Database
{

    protected Commercial thisCom;

    public Commerciaux (Context context)
    {

        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
        table = "commerciaux";
        primary = "idCom";
        thisCom = new Commercial();
    }

    /**
     * @return thisCom
     */
    public Commercial getThisCom()
    {
        return thisCom;
    }

    /**
     * requète insert into
     * @return id du nouveau commercial
     */
    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomCom", thisCom.getNom());
        values.put("prenomCom", thisCom.getPrenom());
        values.put("mailCom", thisCom.getMail());
        values.put("telCom", thisCom.getTel());
        values.put("loginCom", thisCom.getLogin());
        write();
        long res = DB.insert("commerciaux", null, values);
        close();
        return res;
    }

    /**
     * requète update
     * @return id du commercial modifié
     */
    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomCom", thisCom.getNom());
        values.put("prenomCom", thisCom.getPrenom());
        values.put("mailCom", thisCom.getMail());
        values.put("telCom", thisCom.getTel());
        values.put("loginCom", thisCom.getLogin());
        write();
        int res = DB.update("commerciaux", values, "idCom = " + thisCom.getId(), null);
        close();
        return res;
    }

    /**
     * @return Collection de tous les commerciaux
     */
    public ArrayList<Commercial> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux;", null);
        ArrayList<Commercial> commerciaux = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                com = new Commercial(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
                commerciaux.add(com);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return commerciaux;
    }

    /**
     * Charge le commercial correspondant à l'id
     * @param id id du commercial à charger
     * @return Boolean
     */
    public Boolean getById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE idCom = " + id, null);
        Boolean result = (c.getCount() == 1);
        if (result)
        {
            c.moveToFirst();
            thisCom = new Commercial(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
        }
        c.close();
        close();
        return result;
    }

    /**
     * Charge le commercial correspondant au login dans this.com
     * @param login login du commercial a charger
     * @return Boolean
     */
    public Boolean getByLogin(String login) {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM commerciaux WHERE loginCom = '" + login + "';", null);
        Boolean result = (c.getCount() == 1);
        if (result)
        {
            c.moveToFirst();
            thisCom = new Commercial(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
        }
        c.close();
        close();
        return result;
    }

    /**
     * Vérifie si le login existe dans la base de données
     * @param login à tester
     * @return Boolean
     */
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
