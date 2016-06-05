package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aboulineau.commercial.Models.Commerciaux;
import com.example.aboulineau.commercial.R;

public class AddCommercialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_commercial);

        final EditText nomCom     = (EditText)findViewById(R.id.nomField);
        final EditText prenomCom  = (EditText)findViewById(R.id.prenomField);
        final EditText mailCom    = (EditText)findViewById(R.id.mailField);
        final EditText telCom     = (EditText)findViewById(R.id.telField);
        final EditText loginCom   = (EditText)findViewById(R.id.loginField);
        final Button btnCreerCom  = (Button)findViewById(R.id.btnCreerCom);

        final Commerciaux db_com  = new Commerciaux(this);

        btnCreerCom.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                String nom    = nomCom.getText().toString();
                String prenom = prenomCom.getText().toString();
                String mail   = mailCom.getText().toString();
                String tel    = telCom.getText().toString();
                String login  = loginCom.getText().toString();
                // Si identifiant vide
                if (login.isEmpty())
                {
                    Toast.makeText(AddCommercialActivity.this, "Veulliez saisir un identifiant..", Toast.LENGTH_SHORT).show();
                }else {
                    // Si l'identifiant existe déjà
                    if (db_com.loginExist(login)) {
                        Toast.makeText(AddCommercialActivity.this, "L\'identifiant \'" + login + "\' existe déjà.", Toast.LENGTH_SHORT).show();
                    } else {
                        db_com.getThisCom().setNom(nom);
                        db_com.getThisCom().setPrenom(prenom);
                        db_com.getThisCom().setMail(mail);
                        db_com.getThisCom().setTel(tel);
                        db_com.getThisCom().setLogin(login);
                        long res = db_com.insert();
                        // Si la création du compte est un succès
                        if (res > 0) {
                            Toast.makeText(AddCommercialActivity.this, "Création du compte réussie avec succès !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddCommercialActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AddCommercialActivity.this, "Echec de la création du compte.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}