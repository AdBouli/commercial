package com.example.aboulineau.commercial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aboulineau.commercial.Models.Appels;
import com.example.aboulineau.commercial.Models.Entities.Appel;
import com.example.aboulineau.commercial.Models.Entities.Rdv;
import com.example.aboulineau.commercial.Models.Rdvs;
import com.example.aboulineau.commercial.R;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity
{
    final String EXTRA_ID_COM   = "idCom";
    final String EXTRA_ID_RDV   = "idRdv";
    final String EXTRA_ID_APPEL = "idAppel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        final ListView ListMeillRdv = (ListView)findViewById(R.id.mRdvList);
        final ListView ListMeillAppel = (ListView)findViewById(R.id.mAppelList);
        final ListView ListMoinsBRdv = (ListView)findViewById(R.id.nRdvList);
        final ListView ListMoinsBAppel = (ListView)findViewById(R.id.nAppelList);

        Intent intent = getIntent();

        if (intent != null)
        {
            final int id_com = intent.getIntExtra(EXTRA_ID_COM, 0);

            Rdvs db_rdv     = new Rdvs(this);
            Appels db_appel = new Appels(this);

            final List<Rdv> MeilleursRdvs     = db_rdv.selectMeilleursRdvsClientMois(id_com);
            final List<Appel> MeilleursAppels = db_appel.selectMeilleursAppelsClientMois(id_com);
            final List<Rdv> MoinsBonsRdvs     = db_rdv.selectMoinsBonsRdvsClientMois(id_com);
            final List<Appel> MoinsBonsAppels = db_appel.selectMoinsBonsAppelsClientMois(id_com);
            List<String> mRdv   = new ArrayList<>();
            List<String> mAppel = new ArrayList<>();
            List<String> nRdv   = new ArrayList<>();
            List<String> nAppel = new ArrayList<>();
            for (Rdv rv : MeilleursRdvs)
            {
                mRdv.add(rv.getInfos());
            }
            for (Appel apl : MeilleursAppels)
            {
                mAppel.add(apl.getInfos());
            }
            for (Rdv rv : MoinsBonsRdvs)
            {
                nRdv.add(rv.getInfos());
            }
            for (Appel apl : MoinsBonsAppels)
            {
                nAppel.add(apl.getInfos());
            }
            ArrayAdapter<String> adapterMRV  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mRdv);
            ArrayAdapter<String> adapterMAPL = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mAppel);
            ArrayAdapter<String> adapterNRV  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nRdv);
            ArrayAdapter<String> adapterNAPL = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nAppel);
            ListMeillRdv.setAdapter(adapterMRV);
            ListMeillAppel.setAdapter(adapterMAPL);
            ListMoinsBRdv.setAdapter(adapterNRV);
            ListMoinsBAppel.setAdapter(adapterNAPL);
            ListMeillRdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(StatsActivity.this, ViewRdvActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_RDV, MeilleursRdvs.get((int) id).getId());
                    startActivity(intent);
                }
            });
            ListMeillAppel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(StatsActivity.this, ViewAppelActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_APPEL, MeilleursAppels.get((int) id).getId());
                    startActivity(intent);
                }
            });
            ListMoinsBRdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(StatsActivity.this, ViewRdvActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_RDV, MoinsBonsRdvs.get((int) id).getId());
                    startActivity(intent);
                }
            });
            ListMoinsBAppel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(StatsActivity.this, ViewAppelActivity.class);
                    intent.putExtra(EXTRA_ID_COM, id_com);
                    intent.putExtra(EXTRA_ID_APPEL, MoinsBonsAppels.get((int) id).getId());
                    startActivity(intent);
                }
            });
        }
    }
}
