package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aboulineau.commercial.Models.Appels;
import com.example.aboulineau.commercial.Models.Clients;
import com.example.aboulineau.commercial.Models.Entities.Appel;
import com.example.aboulineau.commercial.Models.Entities.Rdv;
import com.example.aboulineau.commercial.Models.Rdvs;
import com.example.aboulineau.commercial.R;

import java.util.ArrayList;
import java.util.List;

public class ViewClientActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM    = "idCom";
    final String EXTRA_ID_CLIENT = "idClient";
    final String EXTRA_ID_RDV    = "idRdv";
    final String EXTRA_ID_APPEL  = "idAppel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_client);

        final TextView NomsTV       = (TextView)findViewById(R.id.noms);
        final TextView CoordoneesTV = (TextView)findViewById(R.id.coordonees);
        final TextView TypeTV       = (TextView)findViewById(R.id.type);
        final TextView AdresseTV    = (TextView)findViewById(R.id.adresse);
        final TextView CodePostalTV = (TextView)findViewById(R.id.codePostal);
        final TextView VilleTV      = (TextView)findViewById(R.id.ville);
        final TextView TitreRdvs    = (TextView)findViewById(R.id.titreLastRdv);
        final TextView TitreAppels  = (TextView)findViewById(R.id.titreLastAppel);
        final ListView ListRdvs     = (ListView)findViewById(R.id.listRdvs);
        final ListView ListAppels   = (ListView)findViewById(R.id.listAppels);

        final Button BtnModifierClient = (Button)findViewById(R.id.btnDelRdv);
        final Button BtnCreerRdv       = (Button)findViewById(R.id.btnCreerRdv);
        final Button BtnCreerAppel     = (Button)findViewById(R.id.btnCreerAppel);

        final Clients db_client = new Clients(this);
        final Rdvs db_rdv       = new Rdvs(this);
        final Appels db_appel   = new Appels(this);

        Intent intent = getIntent();

        if (intent != null)
        {
            final int id_com    = intent.getIntExtra(EXTRA_ID_COM, 0);
            final int id_client = intent.getIntExtra(EXTRA_ID_CLIENT, 0);

            // Set les informations du client
            db_client.getById(id_client);
            NomsTV.setText(db_client.getThisClient().getNomComptet());
            CoordoneesTV.setText(db_client.getThisClient().getMail() + " - " + db_client.getThisClient().getTel());
            if (db_client.getThisClient().getType() == 1)
            {
                TypeTV.setText("Client");
            } else
            {
                TypeTV.setText("Prospect");
            }
            AdresseTV.setText(db_client.getThisClient().getAdresse());
            CodePostalTV.setText(db_client.getThisClient().getVille().getCode());
            VilleTV.setText(db_client.getThisClient().getVille().getNom());

            // Set la liste des rendez-vous
            final List<Rdv> rdvs = db_rdv.select(id_client, id_com);
            List<String> rdvsString = new ArrayList<>();
            for (Rdv rv : rdvs)
            {
                rdvsString.add(rv.getInfos());
            }
            if (rdvsString.size() > 0)
            {
                ArrayAdapter<String> adapterRV = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rdvsString);
                ListRdvs.setAdapter(adapterRV);
            } else
            {
                TitreRdvs.setText("Aucun rendez-vous.");
            }

            ListRdvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewClientActivity.this, ViewRdvActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_RDV, rdvs.get((int) id).getId());
                    startActivity(intent);
                }
            });

            // Set la liste des appels
            final List<Appel> appels = db_appel.select(id_client, id_com);
            List<String> appelsString = new ArrayList<>();
            for (Appel apl : appels)
            {
                appelsString.add(apl.getInfos());
            }
            if (appelsString.size() > 0)
            {
                ArrayAdapter<String> adapterAP = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appelsString);
                ListAppels.setAdapter(adapterAP);
            } else
            {
                TitreAppels.setText("Aucun appel.");
            }

            ListAppels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewClientActivity.this, ViewAppelActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_APPEL, appels.get((int) id).getId());
                    startActivity(intent);
                }
            });

            BtnModifierClient.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ViewClientActivity.this, UpClientActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_CLIENT, id_client);
                    startActivity(intent);
                }
            });

            BtnCreerRdv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ViewClientActivity.this, AddRdvActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_CLIENT, id_client);
                    startActivity(intent);
                }
            });

            BtnCreerAppel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ViewClientActivity.this, AddAppelActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_CLIENT, id_client);
                    startActivity(intent);
                }
            });


        }

    }
}
