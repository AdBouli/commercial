package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aboulineau.commercial.Models.Commerciaux;
import com.example.aboulineau.commercial.R;

public class ListClientsActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM = "idCom";

    final Commerciaux db_com = new Commerciaux(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clients);

        Intent intent = getIntent();

        if (intent != null) {
            int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            db_com.setById(id_com);
            db_com.getCom().getClients();
        }
    }
}
