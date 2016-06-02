package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Appel;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Rdv;
import com.example.aboulineau.commercial.Models.Entities.Ville;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Appels extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Appel thisAppel;

    public Appels (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
        thisAppel = new Appel();
    }

    public Appel getThisAppel()
    {
        return thisAppel;
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", thisAppel.getDate());
        values.put("heureRdv", thisAppel.getHeure());
        values.put("notesRdv", thisAppel.getNotes());
        values.put("avisRdv", thisAppel.getAvis());
        values.put("clientRdv", thisAppel.getClient().getId());
        values.put("comRdv", thisAppel.getCom().getId());
        write();
        long res = DB.insert("rdvs", null, values);
        close();
        return res;
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", thisAppel.getDate());
        values.put("heureRdv", thisAppel.getHeure());
        values.put("notesRdv", thisAppel.getNotes());
        values.put("avisRdv", thisAppel.getAvis());
        values.put("clientRdv", thisAppel.getClient().getId());
        values.put("comRdv", thisAppel.getCom().getId());
        write();
        int res = DB.update("rdvs", values, "idRdv = " + thisAppel.getId(), null);
        close();
        return res;
    }

    /**
     * @return Collection de tous les appels
     */
    public List<Appel> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs R INNER JOIN clients C ON R.clientAppel = C.idClient", null);
        close();
        ArrayList<Appel> appels = new ArrayList<Appel>();
        c.moveToFirst();
        do
        {
            ville = new Ville();
            client = new Client();
            com = new Commercial();
            appel = new Appel();
            appels.add(appel);
        } while (c.moveToNext());
        c.close();
        return appels;
    }

    public Boolean getById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs WHERE idRdv = " + id, null);
        close();
        Boolean result = (c.getCount() == 1);
        if (result)
        {
            ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
            client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getString(14), com);
            com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
            thisAppel = new Appel(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
        }
        c.close();
        return result;
    }
}