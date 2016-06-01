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
    private String telClient;
    private String adresseClient;
    private Ville villeClient;
    private Commercial comClient;
    private List<Rdv> rdvsClient;
    private List<Appel> appelsClient;

    public Client (int id, String nom, String prenom, String mail, String tel, String address, Ville ville, Commercial com)
    {
        idClient = id;
        nomClient = nom;
        prenomClient = prenom;
        mailClient = mail;
        telClient = tel;
        adresseClient = address;
        villeClient = ville;
        comClient = com;
        rdvsClient = new ArrayList<Rdv>();
        appelsClient = new ArrayList<Appel>();
    }

    public Client ()
    {
        idClient = 0;
        nomClient = "";
        prenomClient = "";
        mailClient = "";
        telClient = "";
        adresseClient = "";
        villeClient = new Ville();
        comClient = new Commercial();
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

    public String getTel ()
    {
        return telClient;
    }

    public void setTel (String telephone)
    {
        telClient = telephone;
    }

    public String getAdresse ()
    {
        return adresseClient;
    }

    public void setAdresse (String adresse)
    {
        adresseClient = adresse;
    }

    public Ville getVille ()
    {
        return villeClient;
    }

    public void setVille (Ville ville)
    {
        villeClient = ville;
    }

    public Commercial getCom ()
    {
        return comClient;
    }

    public void setCom (Commercial com)
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

    public String getNomComptet()
    {
        return prenomClient + " " + nomClient;
    }

    public String getAdresseComplete()
    {
        return adresseClient + " " + getVille().getCode() + " " + getVille().getNom();
    }

    public String getCoordonnees()
    {
        return getNomComptet() + " - " + getAdresseComplete() + " - " + mailClient + " - " + telClient;
    }

}
