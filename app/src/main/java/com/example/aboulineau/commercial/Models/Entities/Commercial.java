package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Commercial extends Model
{
    private int idCom;
    private String nomCom;
    private String prenomCom;
    private String mailCom;
    private String telCom;
    private String loginCom;
    private List<Client> clientsCom;
    private List<Rdv> rdvsCom;
    private List<Appel> appelsCom;

    public Commercial (int id, String nom, String prenom, String mail, String telephone, String login)
    {
        idCom = id;
        nomCom = nom;
        prenomCom = prenom;
        mailCom = mail;
        telCom = telephone;
        loginCom = login;
        clientsCom = new ArrayList<Client>();
        rdvsCom = new ArrayList<Rdv>();
        appelsCom = new ArrayList<Appel>();
    }

    public Commercial ()
    {
        idCom = 0;
        nomCom = "";
        prenomCom = "";
        mailCom = "";
        telCom = "";
        loginCom = "";
        clientsCom = new ArrayList<Client>();
        rdvsCom = new ArrayList<Rdv>();
        appelsCom = new ArrayList<Appel>();
    }

    public int getId ()
    {
        return idCom;
    }

    public void setId (int id)
    {
        idCom = id;
    }

    public String getNom ()
    {
        return nomCom;
    }

    public void setNom (String nom)
    {
        nomCom = nom;
    }

    public String getPrenom ()
    {
        return prenomCom;
    }

    public void setPrenom (String prenom)
    {
        prenomCom = prenom;
    }

    public String getMail ()
    {
        return mailCom;
    }

    public void setMail (String mail)
    {
        mailCom = mail;
    }

    public String getTel ()
    {
        return telCom;
    }

    public void setTel (String tel)
    {
        telCom = tel;
    }

    public String getLogin ()
    {
        return loginCom;
    }

    public void setLogin (String login)
    {
        loginCom = login;
    }

    public List<Client> getClients ()
    {
        return clientsCom;
    }

    public void setClients (List<Client> clients)
    {
        clientsCom = clients;
    }

    public void addClient (Client client)
    {
        clientsCom.add(client);
    }

    public void removeClient (Client client)
    {
        clientsCom.remove(client);
    }

    public void clearClients ()
    {
        clientsCom.clear();
    }

    public List<Rdv> getRdvs ()
    {
        return rdvsCom;
    }

    public void setRdvs (List<Rdv> rdvs)
    {
        rdvsCom = rdvs;
    }

    public void addRdv (Rdv rdv)
    {
        rdvsCom.add(rdv);
    }

    public void removeRdv (Rdv rdv)
    {
        rdvsCom.remove(rdv);
    }

    public void clearRdvs ()
    {
        rdvsCom.clear();
    }

    public List<Appel> getAppels ()
    {
        return appelsCom;
    }

    public void setAppels (List<Appel> appels)
    {
        appelsCom = appels;
    }

    public void addAppel (Appel appel)
    {
        appelsCom.add(appel);
    }

    public void removeAppel (Appel appel)
    {
        appelsCom.remove(appel);
    }

    public void clearAppels ()
    {
        appelsCom.clear();
    }

    public String getNomComplet()
    {
        return prenomCom + " " + nomCom;
    }

}
