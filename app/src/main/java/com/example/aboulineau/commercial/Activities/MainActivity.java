package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aboulineau.commercial.Models.Commerciaux;
import com.example.aboulineau.commercial.R;

public class MainActivity extends AppCompatActivity {

    final String EXTRA_ID_COM = "idCom";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText loginCom  = (EditText)findViewById(R.id.loginField);
        final Button btnValider  = (Button)findViewById(R.id.btnValider);
        final Button btnCreerCom = (Button)findViewById(R.id.btnCreerCom);

        final Commerciaux db_com = new Commerciaux(this);

        btnValider.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (db_com.setByLogin(loginCom.getText().toString()))
                {
                    Intent intent = new Intent(MainActivity.this, ListClientsActivity.class);
                    intent.putExtra(EXTRA_ID_COM, db_com.getCom().getId());
                    startActivity(intent);
                } else
                {
                    Toast.makeText(MainActivity.this, "Identifiant inconnu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCreerCom.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AddCommercialActivity.class);
                startActivity(intent);
            }
        });
    }
}
