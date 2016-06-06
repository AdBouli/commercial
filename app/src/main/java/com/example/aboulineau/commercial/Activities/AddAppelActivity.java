package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboulineau.commercial.Models.Appels;
import com.example.aboulineau.commercial.Models.Clients;
import com.example.aboulineau.commercial.R;

public class AddAppelActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM    = "idCom";
    final String EXTRA_ID_CLIENT = "idClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appel);

        final TextView TitreAppel  = (TextView)findViewById(R.id.titreAddAppel);
        final EditText DateField   = (EditText)findViewById(R.id.dateField);
        final EditText HeureField  = (EditText)findViewById(R.id.heureField);
        final EditText NotesField  = (EditText)findViewById(R.id.notesField);
        final EditText AvisField   = (EditText)findViewById(R.id.avisField);
        final Button BtnCreerAppel = (Button)findViewById(R.id.creerAppel);

        final Appels db_appel = new Appels(this);
        final Clients db_client = new Clients(this);

        Intent intent = getIntent();

        if (intent != null)
        {
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            final int id_client = intent.getIntExtra(EXTRA_ID_CLIENT, 0);

            // Edition du titre
            db_client.getById(id_client);
            TitreAppel.setText(db_client.getThisClient().getCoordonnees());

            BtnCreerAppel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String dateA = DateField.getText().toString();
                    String heureA = HeureField.getText().toString();
                    String notesA = NotesField.getText().toString();
                    String avisA = AvisField.getText().toString();
                    if (dateA.isEmpty()  || heureA.isEmpty() || notesA.isEmpty() || avisA.isEmpty())
                    {
                        Toast.makeText(AddAppelActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        db_appel.getThisAppel().setDate(dateA);
                        db_appel.getThisAppel().setHeure(heureA);
                        db_appel.getThisAppel().setNotes(notesA);
                        db_appel.getThisAppel().setAvis(Integer.parseInt(avisA));
                        db_appel.getThisAppel().getClient().setId(id_client);
                        db_appel.getThisAppel().getCom().setId(id_com);
                        if (db_appel.insert() > 0)
                        {
                            Intent intent = new Intent(AddAppelActivity.this, ViewClientActivity.class);
                            intent.putExtra(EXTRA_ID_COM, id_com);
                            intent.putExtra(EXTRA_ID_CLIENT, id_client);
                            startActivity(intent);
                        } else
                        {
                            Toast.makeText(AddAppelActivity.this, "Echec de la création de l\'appel.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }



    }
}
