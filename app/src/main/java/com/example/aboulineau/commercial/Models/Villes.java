package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Ville;

import java.util.ArrayList;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Villes extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Ville ville;

    public Villes (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomVille", ville.getNom());
        values.put("codeVille", ville.getCode());
        return DB.insert("villes", null, values);
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomVille", ville.getNom());
        values.put("codeVille", ville.getCode());
        return DB.update("villes", values, "idVille = " + ville.getId(), null);
    }

    public ArrayList<Ville> selectAll()
    {
        Cursor c = DB.rawQuery("SELECT * FROM villes", null);
        ArrayList<Ville> villes = new ArrayList<Ville>();
        c.moveToFirst();
        do {
            villes.add(new Ville(c.getInt(0), c.getString(1), c.getString(2)));
        } while (c.moveToNext());
        return villes;
    }

    public Boolean getById(int id)
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
