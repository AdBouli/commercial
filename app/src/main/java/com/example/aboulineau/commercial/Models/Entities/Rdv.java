package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Commercial;

import java.util.Date;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Rdv extends Model
{
    private int idRdv;
    private Date dateRdv;
    private String heureRdv;
    private String commentaireRdv;
    private Boolean avisRdv;
    private Client clientRdv;
    private Commercial comRdv;

    public Rdv (int id, Date date, String heure, String commentaire, Boolean avis, Client client, Commercial com)
    {
        idRdv = id;
        dateRdv = date;
        heureRdv = heure;
        commentaireRdv = commentaire;
        avisRdv = avis;
        clientRdv = client;
        comRdv = com;
    }

    public int getId ()
    {
        return idRdv;
    }

    public void setId (int id)
    {
        idRdv = id;
    }

    public Date getDate ()
    {
        return dateRdv;
    }

    public void set (Date date)
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

    public String getCommentaire ()
    {
        return commentaireRdv;
    }

    public void setCommentaire (String commentaire)
    {
        commentaireRdv = commentaire;
    }

    public Boolean getAvis ()
    {
        return avisRdv;
    }

    public void setAvis (Boolean avis)
    {
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

    public Commercial getUser ()
    {
        return comRdv;
    }

    public void setUser (Commercial com)
    {
        comRdv = com;
    }
}