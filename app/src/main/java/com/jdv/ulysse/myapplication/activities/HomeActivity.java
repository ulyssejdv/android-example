package com.jdv.ulysse.myapplication.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.fragments.ClientDetailsFragment;
import com.jdv.ulysse.myapplication.fragments.ClientListFragment;
import com.jdv.ulysse.myapplication.models.Client;

public class HomeActivity extends AppCompatActivity implements ClientListFragment.OnClientSelectedListener {

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_client:
                // User chose the "Settings" item, show the app settings UI...
                Log.d(TAG, "onOptionsItemSelected: click on add");
                startActivity(new Intent(this, ClientAddActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClientSelected(int id) {
        if (findViewById(R.id.detail_client_fragment_land) == null) {
            Log.d(TAG, "onClientSelected: detail client est null");
            Intent intent = new Intent(this, ClientDetailActivity.class);
            intent.putExtra("CLIENT_ID", id);
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            ClientDetailsFragment fragment = (ClientDetailsFragment)fragmentManager.
                    findFragmentById(R.id.detail_client_fragment_land);
            fragment.updateClient(id);
        }
    }
}
