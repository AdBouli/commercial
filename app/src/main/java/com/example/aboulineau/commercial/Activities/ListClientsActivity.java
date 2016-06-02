package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aboulineau.commercial.Models.Clients;
import com.example.aboulineau.commercial.Models.Commerciaux;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.R;

import java.util.ArrayList;
import java.util.List;

public class ListClientsActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM         = "idCom";
    final String EXTRA_SEARCH_FIELD   = "nomsClient";
    final String EXTRA_CHECK_CLIENT   = "typeClient";
    final String EXTRA_CHECK_PROSPECT = "typeProspect";
    final String EXTRA_DEP_FIELD      = "depVilleClient";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clients);

        final TextView NomTitre      = (TextView)findViewById(R.id.nomTitre);
        final TextView ContactTitre  = (TextView)findViewById(R.id.contactTitre);
        final EditText SearchField   = (EditText)findViewById(R.id.searchClientField);
        final CheckBox ClientCheck   = (CheckBox)findViewById(R.id.checkBoxCli);
        final CheckBox ProspectCheck = (CheckBox)findViewById(R.id.checkBoxPro);
        final EditText DepField      = (EditText)findViewById(R.id.depField);
        final Button BtnSearchClient = (Button)findViewById(R.id.searchClient);
        final ListView ListClients   = (ListView)findViewById(R.id.listClients);

        final Commerciaux db_com = new Commerciaux(this);
        final Clients db_client  = new Clients(this);

        Intent intent = getIntent();

        // Si les variables son passées
        if (intent != null) {
            int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            db_com.getById(id_com);
            NomTitre.setText(db_com.getThisCom().getNomComplet());
            ContactTitre.setText(db_com.getThisCom().getMail() + " - " + db_com.getThisCom().getTel());

            // Affiche la liste de ses clients
            db_com.setClients();
            List<Client> clients = db_com.getThisCom().getClients();
            List<String> clientsString = new ArrayList<>();
            for (Client client : clients)
            {
                clientsString.add(client.getCoordonnees());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientsString);
            ListClients.setAdapter(adapter);

            // test
            NomTitre.setText(db_com.getThisCom().getNomComplet());
            ContactTitre.setText(db_com.getThisCom().getMail() + " - " + db_com.getThisCom().getTel());


        }

        BtnSearchClient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String noms = SearchField.getText().toString();
                Boolean cBox = ClientCheck.isChecked();
                Boolean pBox = ProspectCheck.isChecked();
                String dep = DepField.getText().toString();

                Intent intent = new Intent(ListClientsActivity.this, ListClientsActivity.class);
                intent.putExtra(EXTRA_ID_COM, db_com.getThisCom().getId());
                intent.putExtra(EXTRA_SEARCH_FIELD, noms);
                intent.putExtra(EXTRA_CHECK_CLIENT, cBox);
                intent.putExtra(EXTRA_CHECK_PROSPECT, pBox);
                intent.putExtra(EXTRA_DEP_FIELD, dep);
                startActivity(intent);
            }
        });

    }
}
