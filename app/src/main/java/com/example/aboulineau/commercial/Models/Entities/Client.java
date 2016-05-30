package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Client extends Model
{
    private int idClient;
    private String nomClient;
    private String prenomClient;
    private String mailClient;
    private String telephoneClient;
    private Ville villeClient;
    private Commercial comClient;
    private List<Rdv> rdvsClient;
    private List<Appel> appelsClient;

    public Client (int id, String nom, String prenom, String mail, String telephone, Ville ville, Commercial com)
    {
        idClient = id;
        nomClient = nom;
        prenomClient = prenom;
        mailClient = mail;
        telephoneClient = telephone;
        villeClient = ville;
        comClient = com;
        rdvsClient = new ArrayList<Rdv>();
        appelsClient = new ArrayList<Appel>();
    }

    public int getId ()
    {
        return idClient;
    }

    public void setId (int id)
    {
        idClient = id;
    }

    public String getNom ()
    {
        return nomClient;
    }

    public void setNom (String nom)
    {
        nomClient = nom;
    }

    public String getPrenom ()
    {
        return prenomClient;
    }

    public void setPrenom (String prenom)
    {
        prenomClient = prenom;
    }

    public String getMail ()
    {
        return mailClient;
    }

    public void setMail (String mail)
    {
        mailClient = mail;
    }

    public String getTelephone ()
    {
        return telephoneClient;
    }

    public void setTelephone (String telephone)
    {
        telephoneClient = telephone;
    }

    public Ville getVille ()
    {
        return villeClient;
    }

    public void setVille (Ville ville)
    {
        villeClient = ville;
    }

    public Commercial getUser ()
    {
        return comClient;
    }

    public void setUser (Commercial com)
    {
        comClient = com;
    }

    public List<Rdv> getRdvs ()
    {
        return rdvsClient;
    }

    public void setRdvs (List<Rdv> rdvs)
    {
        rdvsClient = rdvs;
    }

    public void addRdv (Rdv rdv)
    {
        rdvsClient.add(rdv);
    }

    public void removeRdv (Rdv rdv)
    {
        rdvsClient.remove(rdv);
    }

    public void clearRdvs ()
    {
        rdvsClient.clear();
    }

    public List<Appel> getAppels ()
    {
        return appelsClient;
    }

    public void setAppels (List<Appel> appels)
    {
        appelsClient = appels;
    }

    public void addAppel (Appel appel)
    {
        appelsClient.add(appel);
    }

    public void removeAppel (Appel appel)
    {
        appelsClient.remove(appel);
    }

    public void clearAppels ()
    {
        appelsClient.clear();
    }

}
