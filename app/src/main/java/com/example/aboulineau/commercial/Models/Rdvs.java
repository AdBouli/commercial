package com.example.aboulineau.commercial.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;
import com.example.aboulineau.commercial.Models.Entities.Rdv;
import com.example.aboulineau.commercial.Models.Entities.Ville;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Rdvs extends Database
{

    protected Rdv thisRdv;

    public Rdvs (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
        table = "rdvs";
        primary = "idRdv";
        thisRdv = new Rdv();
    }

    /**
     * @return thisRdv
     */
    public Rdv getThisRdv()
    {
        return thisRdv;
    }

    /**
     * requète insert into
     * @return id du nouveau rdv
     */
    public long insert()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", thisRdv.getDate().toString());
        values.put("heureRdv", thisRdv.getHeure());
        values.put("notesRdv", thisRdv.getNotes());
        values.put("avisRdv", thisRdv.getAvis());
        values.put("clientRdv", thisRdv.getClient().getId());
        values.put("comRdv", thisRdv.getCom().getId());
        write();
        long res = DB.insert("rdvs", null, values);
        close();
        return res;
    }

    /**
     * requète update
     * @return id du rdv modifié
     */
    public int update()
    {
        ContentValues values = new ContentValues();
        values.put("dateRdv", thisRdv.getDate().toString());
        values.put("heureRdv", thisRdv.getHeure());
        values.put("notesRdv", thisRdv.getNotes());
        values.put("avisRdv", thisRdv.getAvis());
        values.put("clientRdv", thisRdv.getClient().getId());
        values.put("comRdv", thisRdv.getCom().getId());
        write();
        int res = DB.update("rdvs", values, "idRdv = " + thisRdv.getId(), null);
        close();
        return res;
    }

    /**
     * @return Collection de tous les rendez-vous
     */
    public List<Rdv> selectAll()
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom;", null);
        ArrayList<Rdv> rdvs = new ArrayList<Rdv>();
        c.moveToFirst();
        do
        {
            ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
            client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
            com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
            rdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
            rdvs.add(rdv);
        } while (c.moveToNext());
        c.close();
        close();
        return rdvs;
    }

    /**
     * Charge le rendez-vous correspondant à l'id
     * @param id id du rendez-vous à chargé
     * @return True si rendez-vous chargé, false sinon
     */
    public Boolean getById(int id) {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom WHERE idRdv = " + id, null);
        Boolean result = (c.getCount() == 1);
        if (result)
        {
            c.moveToFirst();
            ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
            client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
            com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
            thisRdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
        }
        c.close();
        close();
        return result;
    }

    /**
     * @param idClient id du client à chercher
     * @param idCom id du commercial à chercher
     * @return Liste des rdv recherchés
     */
    public List<Rdv> select(int idClient, int idCom)
    {
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom WHERE clientRdv = " + idClient + " AND comRdv = " + idCom + " ORDER BY dateRdv DESC, heureRdv DESC;", null);
        ArrayList<Rdv> rdvs = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
                client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
                com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
                rdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
                rdvs.add(rdv);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return rdvs;
    }

    /**
     * @param idCom id du commercial à charger
     * @return liste des rdv client des 30 derniers jours avec un avis au dessus de la moyenne
     */
    public List<Rdv> selectMeilleursRdvsClientMois(int idCom)
    {
        Calendar dateRef = Calendar.getInstance();
        dateRef.add(Calendar.MONTH, -1);
        String dateAjd = Calendar.getInstance().toString();
        String dateMois = dateRef.toString();
        read();
        // Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom ", null);
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom WHERE comRdv = " + idCom + " AND typeClient = 1  AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "' AND avisRdv > (SELECT avg(avisRdv) FROM rdvs WHERE comRdv = 0 AND typeClient = 1  AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "')", null);
        ArrayList<Rdv> rdvs = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
                client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
                com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
                rdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
                rdvs.add(rdv);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return rdvs;
    }

    /**
     * @param idCom id du commercial à charger
     * @return liste des rdv prospect des 30 derniers jours avec un avis au dessus de la moyenne
     */
    public List<Rdv> selectMeilleursRdvsProspectMois(int idCom)
    {
        Calendar dateRef = Calendar.getInstance();
        dateRef.add(Calendar.MONTH, -1);
        String dateAjd = Calendar.getInstance().toString();
        String dateMois = dateRef.toString();
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom WHERE comRdv = " + idCom + " AND typeClient = 0  AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "'AND avisRdvl > (SELECT avg(avisRdv) FROM rdvs WHERE comRdv = 0 AND typeClient = 0 AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "')", null);
        ArrayList<Rdv> rdvs = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
                client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
                com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
                rdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
                rdvs.add(rdv);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return rdvs;
    }

    /**
     * @param idCom id du commercial à charger
     * @return liste des rdv clients des 30 derniers jours avec un avis en dessous de la moyenne
     */
    public List<Rdv> selectMoinsBonsRdvsClientMois(int idCom)
    {
        Calendar dateRef = Calendar.getInstance();
        dateRef.add(Calendar.MONTH, -1);
        String dateAjd = Calendar.getInstance().toString();
        String dateMois = dateRef.toString();
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom WHERE comRdv = " + idCom + " AND typeClient = 1  AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "' AND avisRdv < (SELECT avg(avisRdv) FROM rdvs WHERE comRdv = 0 AND typeClient = 1  AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "')", null);
        ArrayList<Rdv> rdvs = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
                client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
                com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
                rdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
                rdvs.add(rdv);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return rdvs;
    }

    /**
     * @param idCom id du commercial à charger
     * @return liste des rdv prospect des 30 derniers jours avec un avis en dessous de la moyenne
     */
    public List<Rdv> selectMoinsBonsRdvsProspectMois(int idCom)
    {
        Calendar dateRef = Calendar.getInstance();
        dateRef.add(Calendar.MONTH, -1);
        String dateAjd = Calendar.getInstance().toString();
        String dateMois = dateRef.toString();
        read();
        Cursor c = DB.rawQuery("SELECT * FROM rdvs INNER JOIN clients ON clientRdv = idClient INNER JOIN villes ON villeClient = idVille INNER JOIN commerciaux ON comRdv = idCom WHERE comRdv = " + idCom + " AND typeClient = 0  AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "'AND avisRdvl < (SELECT avg(avisRdv) FROM rdvs WHERE comRdv = 0 AND typeClient = 0 AND dateRdv BETWEEN '" + dateMois + "' AND '" + dateAjd + "')", null);
        ArrayList<Rdv> rdvs = new ArrayList<>();
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do
            {
                ville = new Ville(c.getInt(16), c.getString(17), c.getString(18));
                client = new Client(c.getInt(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12), ville, c.getInt(14), com);
                com = new Commercial(c.getInt(19), c.getString(20), c.getString(21), c.getString(22), c.getString(23), c.getString(24));
                rdv = new Rdv(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4), client, com);
                rdvs.add(rdv);
            } while (c.moveToNext());
        }
        c.close();
        close();
        return rdvs;
    }






}