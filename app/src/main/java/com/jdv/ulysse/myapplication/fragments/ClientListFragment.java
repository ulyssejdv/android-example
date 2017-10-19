package com.jdv.ulysse.myapplication.fragments;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jdv.ulysse.myapplication.activities.ClientDetailActivity;
import com.jdv.ulysse.myapplication.adapters.ClientAdapter;
import com.jdv.ulysse.myapplication.fragments.ClientDetailsFragment;
import com.jdv.ulysse.myapplication.models.Client;

public class ClientListFragment extends ListFragment {

    private static final String TAG = "ClientAddActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientAdapter clientAdapter = new ClientAdapter(getActivity(), Client.getClients());
        setListAdapter(clientAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get client from database
        Client client = Client.getClients().get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("CLIENT_ID", position);
        Intent intent = new Intent(getActivity(), ClientDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
