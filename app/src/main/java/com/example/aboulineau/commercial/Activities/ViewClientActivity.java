package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aboulineau.commercial.Models.Clients;
import com.example.aboulineau.commercial.Models.Commerciaux;
import com.example.aboulineau.commercial.R;

public class ViewClientActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM    = "idCom";
    final String EXTRA_ID_CLIENT = "idClient";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_client);

        final TextView NomsTX       = (TextView)findViewById(R.id.noms);
        final TextView CoordoneesTX = (TextView)findViewById(R.id.coordonees);
        final TextView TypeTX       = (TextView)findViewById(R.id.type);
        final TextView AdresseTX    = (TextView)findViewById(R.id.adresse);
        final TextView CodePostalTX = (TextView)findViewById(R.id.codePostal);
        final TextView VilleTX      = (TextView)findViewById(R.id.ville);

        final Commerciaux db_com = new Commerciaux(this);
        final Clients db_client  = new Clients(this);

        Intent intent = getIntent();

        if (intent != null)
        {
            int id_com    = intent.getIntExtra(EXTRA_ID_COM, 0);
            int id_client = intent.getIntExtra(EXTRA_ID_CLIENT, 0);

            db_client.getById(id_client);
            NomsTX.setText(db_client.getThisClient().getNomComptet());
            CoordoneesTX.setText(db_client.getThisClient().getCoordonnees());
            if (db_client.getThisClient().getType() == 1)
            {
                TypeTX.setText("Client");
            } else
            {
                TypeTX.setText("Prospect");
            }
        }

    }
}
