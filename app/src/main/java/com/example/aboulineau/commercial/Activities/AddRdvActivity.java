package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboulineau.commercial.Models.Clients;
import com.example.aboulineau.commercial.Models.Rdvs;
import com.example.aboulineau.commercial.R;

public class AddRdvActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM    = "idCom";
    final String EXTRA_ID_CLIENT = "idClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rdv);

        final TextView TitreRdv   = (TextView)findViewById(R.id.titreAddRdv);
        final EditText DateField  = (EditText)findViewById(R.id.dateField);
        final EditText HeureField = (EditText)findViewById(R.id.heureField);
        final EditText NotesField = (EditText)findViewById(R.id.notesField);
        final EditText AvisField  = (EditText)findViewById(R.id.avisField);
        final Button BtnCreerRdv  = (Button)findViewById(R.id.creerRdv);

        final Rdvs db_rdv = new Rdvs(this);
        final Clients db_client = new Clients(this);

        Intent intent = getIntent();

        if (intent != null)
        {
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            final int id_client =intent.getIntExtra(EXTRA_ID_CLIENT, 0);

            // Edition du titre
            db_client.getById(id_client);
            TitreRdv.setText(db_client.getThisClient().getCoordonnees());

            BtnCreerRdv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    db_rdv.getThisRdv().setDate(DateField.getText().toString());
                    db_rdv.getThisRdv().setHeure(HeureField.getText().toString());
                    db_rdv.getThisRdv().setNotes(NotesField.getText().toString());
                    db_rdv.getThisRdv().setAvis(Integer.parseInt(AvisField.getText().toString()));
                    db_rdv.getThisRdv().getClient().setId(id_client);
                    db_rdv.getThisRdv().getCom().setId(id_com);
                    if (db_rdv.insert() > 0)
                    {
                        Intent intent = new Intent(AddRdvActivity.this, ViewClientActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        intent.putExtra(EXTRA_ID_CLIENT, id_client);
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(AddRdvActivity.this, "Echec de la création du rendez-vous.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}
