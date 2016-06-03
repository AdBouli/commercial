package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aboulineau.commercial.Models.Clients;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.Models.Entities.Ville;
import com.example.aboulineau.commercial.Models.Villes;
import com.example.aboulineau.commercial.R;

import java.util.ArrayList;
import java.util.List;

public class UpClientActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM    = "idCom";
    final String EXTRA_ID_CLIENT = "idClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_client);

        final EditText NomField       = (EditText)findViewById(R.id.nomClientField);
        final EditText PrenomField    = (EditText)findViewById(R.id.prenomClientField);
        final EditText TelField       = (EditText)findViewById(R.id.telClientField);
        final EditText MailField      = (EditText)findViewById(R.id.mailClientField);
        final EditText AdresseField   = (EditText)findViewById(R.id.adresseClientField);
        final EditText VilleField     = (EditText)findViewById(R.id.villeClientField);
        final Ville    VilleSelected  = new Ville();
        final EditText NomVilleField  = (EditText)findViewById(R.id.nomVilleField);
        final EditText CodeVilleField = (EditText)findViewById(R.id.codePostalVilleField);
        final Button   BtnCreerVille  = (Button)findViewById(R.id.creerVille);
        final Button   BtnUpClient    = (Button)findViewById(R.id.upClient);
        final Button   BtnDelClient   = (Button)findViewById(R.id.delClient);
        final ListView ListVilles     = (ListView)findViewById(R.id.listVilles);

        final Clients db_client = new Clients(this);
        final Villes  db_ville  = new Villes(this);

        // Liste des villes
        final List<Ville> villes = db_ville.selectAll();
        final List<String> villeString = new ArrayList<>();
        for (Ville v : villes)
        {
            villeString.add(v.getNomComplet());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, villeString);
        ListVilles.setAdapter(adapter);

        // Récupération des informations
        Intent intent = getIntent();

        if (intent != null) {
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            final int id_client = intent.getIntExtra(EXTRA_ID_CLIENT, 0);

            // Remplissage des champs
            if (db_client.getById(id_client)) {
                NomField.setText(db_client.getThisClient().getNom());
                PrenomField.setText(db_client.getThisClient().getPrenom());
                TelField.setText(db_client.getThisClient().getTel());
                MailField.setText(db_client.getThisClient().getMail());
                AdresseField.setText(db_client.getThisClient().getAdresse());
                db_ville.setById(db_client.getThisClient().getVille().getId());
                VilleSelected.setId(db_ville.getThisVille().getId());
                VilleField.setText(db_ville.getThisVille().getNomComplet());
            }
            // Changement de ville
            ListVilles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Ville newV = villes.get((int) id);
                    VilleSelected.setId(newV.getId());
                    VilleField.setText(newV.getNomComplet());
                }
            });

            // Création d'une ville
            BtnCreerVille.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String nomV = NomVilleField.getText().toString();
                    String codeV = CodeVilleField.getText().toString();
                    db_ville.getThisVille().setNom(nomV);
                    db_ville.getThisVille().setCode(codeV);
                    if (db_ville.insert() > 0) {
                        VilleSelected.setId(db_ville.getThisVille().getId());
                        VilleField.setText(db_ville.getThisVille().getNomComplet());
                    } else {
                        Toast.makeText(UpClientActivity.this, "Echec dans la création de la ville", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Modifier client
            BtnUpClient.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String nomC = NomField.getText().toString();
                    String prenomC = PrenomField.getText().toString();
                    String telC = TelField.getText().toString();
                    String mailC = MailField.getText().toString();
                    db_client.getById(id_client);
                    db_client.getThisClient().setNom(nomC);
                    db_client.getThisClient().setPrenom(nomC);
                    db_client.getThisClient().setTel(nomC);
                    db_client.getThisClient().setMail(nomC);
                    db_client.getThisClient().getVille().setId(VilleSelected.getId());
                    if (db_client.update() > 0)
                    {
                        Intent intent = new Intent(UpClientActivity.this, ViewClientActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        intent.putExtra(EXTRA_ID_CLIENT, id_client);
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(UpClientActivity.this, "Echec dans la modification du client", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Supprimer client
            BtnDelClient.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    db_client.getById(id_client);
                    if (db_client.delete(id_client) > 0)
                    {
                        Intent intent = new Intent(UpClientActivity.this, ListClientsActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(UpClientActivity.this, "Echec dans la suppression du client", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
}
