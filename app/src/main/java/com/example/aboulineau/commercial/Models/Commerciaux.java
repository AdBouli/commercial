package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
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
        while (c.moveToNext())
        {

        }
    }
}
