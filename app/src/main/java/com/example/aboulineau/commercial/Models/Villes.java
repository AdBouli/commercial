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

    public Villes (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomVille", ville.getNom());
        values.put("codeVille", ville.getCode());
        write();
        long res = DB.insert("villes", null, values);
        close();
        return res;
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomVille", ville.getNom());
        values.put("codeVille", ville.getCode());
        write();
        int res = DB.update("villes", values, "idVille = " + ville.getId(), null);
        close();
        return res;
    }

    public ArrayList<Ville> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM villes", null);
        close();
        ArrayList<Ville> villes = new ArrayList<Ville>();
        c.moveToFirst();
        Ville uneVille = new Ville();
        do {
            uneVille.setId(c.getInt(0));
            uneVille.setNom(c.getString(1));
            uneVille.setCode(c.getString(2));
            villes.add(uneVille);
        } while (c.moveToNext());
        c.close();
        return villes;
    }

    public Boolean setById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM villes WHERE idVille = " + id, null);
        close();
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
        c.close();
        return result;
    }
}
