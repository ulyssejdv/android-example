package com.jdv.ulysse.myapplication.activities;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.adapters.ClientAdapter;
import com.jdv.ulysse.myapplication.models.Client;

public class ClientListFrament extends ListFragment {

    private ListView listView;
    private static final String TAG = "ClientAddActivity";
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_client);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Log.d(TAG, "onCreate: add toolbar");


        listView = (ListView)findViewById(R.id.listClient);
        ClientAdapter clientAdapter = new ClientAdapter(this, Client.getClients());

        intent = new Intent(this, ClientDetailsActivity.class);

        listView.setAdapter(clientAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = Client.getClients().get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("CLIENT_ID", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

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
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
