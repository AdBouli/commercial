package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Rdv extends Model
{
    private int idRdv;
    private String dateRdv;
    private String heureRdv;
    private String notesRdv;
    private Integer avisRdv;
    private Client clientRdv;
    private Commercial comRdv;

    public Rdv (int id, String date, String heure, String notes, Integer avis, Client client, Commercial com)
    {
        idRdv = id;
        dateRdv = date;
        heureRdv = heure;
        notesRdv = notes;
        avisRdv = avis;
        clientRdv = client;
        comRdv = com;
    }

    public Rdv ()
    {
        idRdv = 0;
        dateRdv = "";
        heureRdv = "";
        notesRdv = "";
        avisRdv = 0;
        clientRdv = new Client();
        comRdv = new Commercial();
    }

    public int getId ()
    {
        return idRdv;
    }

    public void setId (int id)
    {
        idRdv = id;
    }

    public String getDate ()
    {
        return dateRdv;
    }

    public void setDate (String date)
    {
        dateRdv = date;
    }

    public String getHeure ()
    {
        return heureRdv;
    }

    public void setHeure (String heure)
    {
        heureRdv = heure;
    }

    public String getNotes ()
    {
        return notesRdv;
    }

    public void setNotes (String notes)
    {
        notesRdv = notes;
    }

    public Integer getAvis ()
    {
        return avisRdv;
    }

    public void setAvis (int avis)
    {
        if (avis > 10)
        {
            avis = 10;
        } else
        {
            if (avis < 0)
            {
                avis = 0;
            }
        }
        avisRdv = avis;
    }

    public Client getClient ()
    {
        return clientRdv;
    }

    public void setClient (Client client)
    {
        clientRdv = client;
    }

    public Commercial getCom ()
    {
        return comRdv;
    }

    public void setCom (Commercial com)
    {
        comRdv = com;
    }

    public String getInfos()
    {
        return dateRdv + " " + heureRdv + " - " + notesRdv + " - avis: " + avisRdv;
    }
}