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

import com.example.aboulineau.commercial.Models.Commerciaux;
import com.example.aboulineau.commercial.Models.Entities.Client;
import com.example.aboulineau.commercial.R;

import java.util.ArrayList;
import java.util.List;

public class ListClientsActivity extends AppCompatActivity
{

    final String EXTRA_ID_COM = "idCom";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clients);

        final TextView Titre         = (TextView)findViewById(R.id.titre);
        final EditText SearchField   = (EditText)findViewById(R.id.searchClientField);
        final CheckBox ClientCheck   = (CheckBox)findViewById(R.id.checkBoxCli);
        final CheckBox ProspectCheck = (CheckBox)findViewById(R.id.checkBoxPro);
        final Button BtnSearchClient = (Button)findViewById(R.id.searchClient);
        final ListView ListClients   = (ListView)findViewById(R.id.listClients);

        final Commerciaux db_com = new Commerciaux(this);

        Intent intent = getIntent();

        // Si les variables son passées
        if (intent != null) {
            int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);
            db_com.setById(id_com);
            String titre = db_com.getCom().getNomComplet() + " - "
                    + db_com.getCom().getMail() + " - "
                    + db_com.getCom().getTel();
            Titre.setText(titre);

            // Affiche la liste de ses clients
            db_com.setClients();
            System.out.println(db_com.getCom().testClients());
            List<Client> clients = db_com.getCom().getClients();
            List<String> clientsString = new ArrayList<>();
            for (Client client : clients)
            {
                clientsString.add(client.getCoordonnees());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientsString);
            ListClients.setAdapter(adapter);
        }

        BtnSearchClient.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

            }
        });
    }
}
