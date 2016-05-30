package com.example.aboulineau.commercial.Models;

import android.content.Context;

import com.example.aboulineau.commercial.Core.Database;
import com.example.aboulineau.commercial.Core.SQLite;
import com.example.aboulineau.commercial.Models.Entities.Client;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Clients extends Database
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "clientele.db";

    protected Client client;

    public Clients (Context context)
    {
        SQL = new SQLite(context, DB_NAME, null, DB_VERSION);
    }
}