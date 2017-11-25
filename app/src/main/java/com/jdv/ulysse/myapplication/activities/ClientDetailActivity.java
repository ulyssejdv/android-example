package com.jdv.ulysse.myapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.fragments.ClientDetailsFragment;

public class ClientDetailActivity extends AppCompatActivity {

    private static final String TAG = "ClientDetailActivity";

    private int idClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idClient = bundle.getInt("CLIENT_ID");
        Log.d(TAG, "onCreate: " + idClient);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ClientDetailsFragment fragment = (ClientDetailsFragment)fragmentManager.
                findFragmentById(R.id.details_client_fragment);
        fragment.updateClient(idClient);
    }
}
