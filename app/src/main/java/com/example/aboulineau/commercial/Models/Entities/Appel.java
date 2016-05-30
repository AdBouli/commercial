package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;

import java.util.Date;

/**
 * Created by aboulineau on 30/05/2016.
*/
public class Appel extends Model
{
    private int idAppel;
    private Date dateAppel;
    private String heureAppel;
    private String notesAppel;
    private Integer avisAppel;
    private Client clientAppel;
    private Commercial comAppel;

    public void Appel (int id, Date date, String heure, String notes, Integer avis, Client client, Commercial com)
    {
        idAppel = id;
        dateAppel = date;
        heureAppel = heure;
        notesAppel = notes;
        avisAppel = avis;
        clientAppel = client;
        comAppel = com;
    }

    public int getId ()
    {
        return idAppel;
    }

    public void setId (int id)
    {
        idAppel = id;
    }

    public Date getDate ()
    {
        return dateAppel;
    }

    public void setDate (Date date)
    {
        dateAppel = date;
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

    public Integer getAvis ()
    {
        return avisAppel;
    }

    public void setAvis (Integer avis)
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
}