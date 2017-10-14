package com.jdv.ulysse.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.models.Client;

public class ClientDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ClientDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int idClient = bundle.getInt("CLIENT_ID");
            Client client = Client.getClients().get(idClient);
            TextView nom = (TextView) findViewById(R.id.nomDetail);
            nom.setText(client.getLastName());
            TextView prenom = (TextView) findViewById(R.id.prenomDetail);
            prenom.setText(client.getFirstName());
        }
    }
}
