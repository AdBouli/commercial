package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboulineau.commercial.Models.Rdvs;
import com.example.aboulineau.commercial.R;

public class ViewRdvActivity extends AppCompatActivity
{
    final String EXTRA_ID_COM    = "idCom";
    final String EXTRA_ID_RDV    = "idRdv";
    final String EXTRA_ID_CLIENT = "idClient";

    Rdvs db_rdv = new Rdvs(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_rdv);

        final TextView Titre      = (TextView)findViewById(R.id.titreViewRdv);
        final EditText DateField  = (EditText)findViewById(R.id.dateField);
        final EditText HeureField = (EditText)findViewById(R.id.heureField);
        final EditText NotesField = (EditText)findViewById(R.id.notesField);
        final EditText AvisField  = (EditText)findViewById(R.id.avisField);
        final Button BtnUpRdv     = (Button)findViewById(R.id.btnUpRdv);
        final Button BtnDelRdv    = (Button)findViewById(R.id.btnDelRdv);

        Intent intent = getIntent();

        if (intent != null)
        {
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            final int id_rdv = intent.getIntExtra(EXTRA_ID_RDV, 0);

            db_rdv.getById(id_rdv);

            Titre.setText(db_rdv.getThisRdv().getClient().getCoordonnees());

            DateField.setText(db_rdv.getThisRdv().getDate());
            HeureField.setText(db_rdv.getThisRdv().getHeure());
            NotesField.setText(db_rdv.getThisRdv().getNotes());
            AvisField.setText(db_rdv.getThisRdv().getAvis().toString());

            BtnUpRdv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dateA = DateField.getText().toString();
                    String heureA = HeureField.getText().toString();
                    String notesA = NotesField.getText().toString();
                    int avisA = Integer.parseInt(AvisField.getText().toString());
                    db_rdv.getThisRdv().setDate(dateA);
                    db_rdv.getThisRdv().setHeure(heureA);
                    db_rdv.getThisRdv().setNotes(notesA);
                    db_rdv.getThisRdv().setAvis(avisA);
                    if (db_rdv.update() > 0) {
                        Intent intent = new Intent(ViewRdvActivity.this, ViewClientActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        intent.putExtra(EXTRA_ID_CLIENT, db_rdv.getThisRdv().getClient().getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(ViewRdvActivity.this, "Echec de la modification du rendez-vous.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            BtnDelRdv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (db_rdv.delete(id_rdv) > 0) {
                        Intent intent = new Intent(ViewRdvActivity.this, ViewClientActivity.class);
                        intent.putExtra(EXTRA_ID_COM, id_com);
                        intent.putExtra(EXTRA_ID_CLIENT, db_rdv.getThisRdv().getClient().getId());
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(ViewRdvActivity.this, "Echec de la suppression du rendez-vous.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
