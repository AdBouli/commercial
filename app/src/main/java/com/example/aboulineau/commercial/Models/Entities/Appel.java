package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;

import java.util.Date;

/**
 * Created by aboulineau on 30/05/2016.
*/
public class Appel extends Model
{
    private int idAppel;
    private String dateAppel;
    private String heureAppel;
    private String notesAppel;
    private int avisAppel;
    private Client clientAppel;
    private Commercial comAppel;

    public Appel (int id, String date, String heure, String notes, int avis, Client client, Commercial com)
    {
        idAppel = id;
        dateAppel = date;
        heureAppel = heure;
        notesAppel = notes;
        avisAppel = avis;
        clientAppel = client;
        comAppel = com;
    }

    public Appel ()
    {
        idAppel = 0;
        dateAppel = "";
        heureAppel = "";
        notesAppel = "";
        avisAppel = 0;
        clientAppel = new Client();
        comAppel = new Commercial();
    }

    public int getId ()
    {
        return idAppel;
    }

    public void setId (int id)
    {
        idAppel = id;
    }

    public String getDate ()
    {
        String dateCut[] = dateAppel.split("-");
        return dateCut[2] + "/" + dateCut[1] + "/" + dateCut[0];
    }

    public void setDate (String date)
    {
        String[] dateCut = date.split("/");
        dateAppel = dateCut[2] + "-" + dateCut[1] + "-" + dateCut[0];
    }

    public String getHeure ()
    {
        return heureAppel;
    }

    public void setHeure (String heure)
    {
        heureAppel = heure;
    }

    public String getNotes ()
    {
        return notesAppel;
    }

    public void setNotes (String notes)
    {
        notesAppel = notes;
    }

    public int getAvis ()
    {
        return avisAppel;
    }

    public void setAvis (int avis)
    {
        avisAppel = avis;
    }

    public Client getClient ()
    {
        return clientAppel;
    }

    public void setClient (Client client)
    {
        clientAppel = client;
    }

    public Commercial getCom ()
    {
        return comAppel;
    }

    public void setCom (Commercial com)
    {
        comAppel = com;
    }

    public String getInfos()
    {
        return dateAppel + " " + heureAppel + " - " + notesAppel + " - avis: " + avisAppel;
    }
}