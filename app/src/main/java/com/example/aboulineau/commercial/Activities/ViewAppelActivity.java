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
import com.example.aboulineau.commercial.R;

public class ViewAppelActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM   = "idCom";
    final String EXTRA_ID_APPEL = "idAppel";
    final String EXTRA_ID_CLIENT = "idClient";

    Appels db_appel = new Appels(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_appel);

        final TextView Titre      = (TextView)findViewById(R.id.titreViewAppel);
        final EditText DateField  = (EditText)findViewById(R.id.dateField);
        final EditText HeureField = (EditText)findViewById(R.id.heureField);
        final EditText NotesField = (EditText)findViewById(R.id.notesField);
        final EditText AvisField  = (EditText)findViewById(R.id.avisField);
        final Button BtnUpAppel   = (Button)findViewById(R.id.btnUpAppel);
        final Button BtnDelAppel  = (Button)findViewById(R.id.btnDelRdv);

        Intent intent = getIntent();

        if (intent != null)
        {
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            final int id_appel = intent.getIntExtra(EXTRA_ID_APPEL, 0);

            db_appel.getById(id_appel);

            Titre.setText(db_appel.getThisAppel().getClient().getCoordonnees());

            DateField.setText(db_appel.getThisAppel().getDate());
            HeureField.setText(db_appel.getThisAppel().getHeure());
            NotesField.setText(db_appel.getThisAppel().getNotes());
            AvisField.setText(db_appel.getThisAppel().getAvis());

            BtnUpAppel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dateA = DateField.getText().toString();
                    String heureA = HeureField.getText().toString();
                    String notesA = NotesField.getText().toString();
                    int avisA = Integer.parseInt(AvisField.getText().toString());
                    db_appel.getThisAppel().setDate(dateA);
                    db_appel.getThisAppel().setHeure(heureA);
                    db_appel.getThisAppel().setNotes(notesA);
                    db_appel.getThisAppel().setAvis(avisA);
                    if (db_appel.update() > 0) {
                        Intent intent = new Intent(ViewAppelActivity.this, ViewClientActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        intent.putExtra(EXTRA_ID_CLIENT, db_appel.getThisAppel().getClient().getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(ViewAppelActivity.this, "Echec de la modification de l\'appel.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            BtnDelAppel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db_appel.delete(id_appel) > 0)
                    {
                        Intent intent = new Intent(ViewAppelActivity.this, ViewClientActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        intent.putExtra(EXTRA_ID_CLIENT, db_appel.getThisAppel().getClient().getId());
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(ViewAppelActivity.this, "Echec de la suppression de l\'appel.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
