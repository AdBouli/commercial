package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class SearchClientsActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM         = "idCom";
    final String EXTRA_SEARCH_FIELD   = "nomsClient";
    final String EXTRA_CHECK_CLIENT   = "typeClient";
    final String EXTRA_CHECK_PROSPECT = "typeProspect";
    final String EXTRA_DEP_FIELD      = "depVilleClient";
    final String EXTRA_ID_CLIENT      = "idClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clients);

        final TextView Titre         = (TextView)findViewById(R.id.Titre);
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
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            db_com.getById(id_com);
            Titre.setText(db_com.getThisCom().getNomComplet() + " - " + db_com.getThisCom().getMail() + " - " + db_com.getThisCom().getTel());
            // Affiche la liste des clients rechercher
            String search_noms = intent.getStringExtra(EXTRA_SEARCH_FIELD);
            Boolean siClient   = intent.getBooleanExtra(EXTRA_CHECK_CLIENT, true);
            Boolean siProspect = intent.getBooleanExtra(EXTRA_CHECK_PROSPECT, true);
            String departement = intent.getStringExtra(EXTRA_DEP_FIELD);

            final List<Client> clients = db_client.search(search_noms, departement, id_com, siClient, siProspect);
            List<String> clientsString = new ArrayList<>();
            for (Client client : clients)
            {
                clientsString.add(client.getCoordonnees());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientsString);
            ListClients.setAdapter(adapter);

            ListClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SearchClientsActivity.this, ViewClientActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_CLIENT, clients.get((int) id).getId());
                    startActivity(intent);
                }
            });

        }

        BtnSearchClient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SearchClientsActivity.this, SearchClientsActivity.class);
                intent.putExtra(EXTRA_ID_COM, db_com.getThisCom().getId());
                intent.putExtra(EXTRA_SEARCH_FIELD, SearchField.getText().toString());
                intent.putExtra(EXTRA_CHECK_CLIENT, ClientCheck.isChecked());
                intent.putExtra(EXTRA_CHECK_PROSPECT, ProspectCheck.isChecked());
                intent.putExtra(EXTRA_DEP_FIELD, DepField.getText().toString());
                startActivity(intent);
            }
        });



    }
}
