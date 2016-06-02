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

    protected Client thisClient;

    public Clients (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
        thisClient = new Client();
    }

    public Client getThisClient()
    {
        return thisClient;
    }

    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("nomClient", thisClient.getNom());
        values.put("prenomClient", thisClient.getPrenom());
        values.put("telClient", thisClient.getTel());
        values.put("mailClient", thisClient.getMail());
        values.put("adresseClient", thisClient.getAdresse());
        values.put("villeClient", thisClient.getVille().getId());
        values.put("comClient", thisClient.getCom().getId());
        write();
        long res = DB.insert("clients", null, values);
        close();
        return res;
    }

    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("nomClient", thisClient.getNom());
        values.put("prenomClient", thisClient.getPrenom());
        values.put("telClient", thisClient.getTel());
        values.put("mailClient", thisClient.getMail());
        values.put("adresseClient", thisClient.getAdresse());
        values.put("villeClient", thisClient.getVille().getId());
        values.put("comClient", thisClient.getCom().getId());
        write();
        int res = DB.update("clients", values, "idClient = " + thisClient.getId(), null);
        close();
        return res;
    }

    public List<Client> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM clients C INNER JOIN villes V ON C.villeClient = V.idVille INNER JOIN commerciaux O ON C.comClient = O.idCom;", null);
        ArrayList<Client> clients = new ArrayList<>();
        c.moveToFirst();
        do
        {
            ville = new Ville(c.getInt(9), c.getString(10), c.getString(11));
            com = new Commercial(c.getInt(12), c.getString(13), c.getString(14), c.getString(15), c.getString(16), c.getString(17));
            client = new Client(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), ville, c.getString(7), com);
            clients.add(client);
        } while (c.moveToNext());
        c.close();
        close();
        return clients;
    }

    /**
     * Charge le client correspondant à l'id
     * @param id id du client à chargé
     * @return Boolean
     */
    public Boolean getById(int id)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM clients C INNER JOIN villes V ON C.villeClient = V.idVille INNER JOIN commerciaux O ON C.comClient = O.idCom WHERE idCli = " + id, null);
        Boolean result = (c.getCount() == 1);
        if (result)
        {
            ville = new Ville(c.getInt(9), c.getString(10), c.getString(11));
            com = new Commercial(c.getInt(12), c.getString(13), c.getString(14), c.getString(15), c.getString(16), c.getString(17));
            thisClient = new Client(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), ville, c.getString(7), com);
        }
        c.close();
        close();
        return result;
    }

    /**
     * Recherche dans la base de donnée
     */
    public List<Client> search(String nom_prenom, String departement, int idCom, Boolean siClient, Boolean siProspect)
    {
        String[] where_nom_premon = nom_prenom.split(" ");
        String clauses_where = "comClient = " + idCom;
        for (int i = 0; i < where_nom_premon.length; i++)
        {
            if (i == 0)
            {
                clauses_where += " AND (";
            }
            clauses_where += "nomClient LIKE '%" + where_nom_premon[i] + "%' OR prenomClient LIKE '%" + where_nom_premon[i] + "%' ";
            if (i == where_nom_premon.length - 1)
            {
                clauses_where += ") ";
            }else
            {
                clauses_where += "OR ";
            }
        }
        if (!siClient && siProspect)
        {
            clauses_where += "AND typeClient = 0 ";
        }
        if (siClient && !siProspect)
        {
            clauses_where += "AND typeClient = 1 ";
        }
        clauses_where += "AND codeVille LIKE '" + departement + "%' ";
        read();
        Cursor c = DB.rawQuery("SELECT * FROM clients C INNER JOIN villes V ON C.villeClient = V.idVille WHERE " + clauses_where, null);
        ArrayList<Client> clients = new ArrayList<>();
        c.moveToFirst();
        do
        {
            ville = new Ville(c.getInt(9), c.getString(10), c.getString(11));
            client = new Client(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), ville, c.getString(7), com);
            clients.add(client);
        } while (c.moveToNext());
        c.close();
        close();
        return clients;

    }
}