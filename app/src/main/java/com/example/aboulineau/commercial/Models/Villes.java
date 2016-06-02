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

    protected Ville thisVille;

    public Villes (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
        thisVille = new Ville();
    }

    public Ville getThisVille()
    {
        return thisVille;
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomVille", thisVille.getNom());
        values.put("codeVille", thisVille.getCode());
        write();
        long res = DB.insert("villes", null, values);
        close();
        return res;
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomVille", thisVille.getNom());
        values.put("codeVille", thisVille.getCode());
        write();
        int res = DB.update("villes", values, "idVille = " + thisVille.getId(), null);
        close();
        return res;
    }

    /**
     * @return Collection de toutes les villes
     */
    public ArrayList<Ville> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM villes", null);
        ArrayList<Ville> villes = new ArrayList<Ville>();
        c.moveToFirst();
        do {
            ville = new Ville(c.getInt(0), c.getString(1), c.getString(2));
            villes.add(ville);
        } while (c.moveToNext());
        c.close();
        close();
        return villes;
    }

    /**
     * Charge la ville correspondant à l'id
     * @param id id de la ville à charger
     * @return Boolean
     */
    public Boolean setById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM villes WHERE idVille = " + id, null);
        close();
        Boolean result = (c.getCount() == 1);
        if (result)
        {
            thisVille = new Ville(c.getInt(0), c.getString(1), c.getString(2));
        }
        c.close();
        return result;
    }
}
