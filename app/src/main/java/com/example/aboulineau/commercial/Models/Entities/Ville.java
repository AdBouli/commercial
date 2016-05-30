package com.example.aboulineau.commercial.Models.Entities;

import com.example.aboulineau.commercial.Core.Model;
import com.example.aboulineau.commercial.Models.Entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Ville extends Model
{
    private int idVille;
    private String nomVille;
    private int codePostalVille;
    private List<Client> clientsVille;

    public Ville (int id, String nom, int codePostal, List<Client> clients)
    {
        idVille = id;
        nomVille = nom;
        codePostalVille = codePostal;
        clientsVille = clients;
        clients = new ArrayList<Client>();
    }

    public int getId ()
    {
        return idVille;
    }

    public void setId (int id)
    {
        idVille = id;
    }

    public String getNom ()
    {
        return nomVille;
    }

    public void setNom (String nom)
    {
        nomVille = nom;
    }

    public int getCodePostal ()
    {
        return codePostalVille;
    }

    public void setCodePostal (int codePostal)
    {
        codePostalVille = codePostal;
    }

    public List<Client> getClients ()
    {
        return clientsVille;
    }

    public void setClients (List<Client> clients)
    {
        clientsVille = clients;
    }

    public void addClient (Client client)
    {
        clientsVille.add(client);
    }

    public void removeClient (Client client)
    {
        clientsVille.remove(client);
    }

    public void clearClients ()
    {
        clientsVille.clear();
    }

}
