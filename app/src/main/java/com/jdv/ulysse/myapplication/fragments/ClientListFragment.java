package com.jdv.ulysse.myapplication.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.jdv.ulysse.myapplication.activities.ClientAddActivity;
import com.jdv.ulysse.myapplication.activities.ClientDetailActivity;
import com.jdv.ulysse.myapplication.adapters.ClientAdapter;
import com.jdv.ulysse.myapplication.fragments.ClientDetailsFragment;
import com.jdv.ulysse.myapplication.models.Client;

public class ClientListFragment extends ListFragment {

    private IntentFilter filter;
    private ClientAdapter clientAdapter;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            clientAdapter.notifyDataSetChanged();
        }
    };

    public interface OnClientSelectedListener {
        void onClientSelected(int id);
    }

    private OnClientSelectedListener listener;

    private static final String TAG = "ClientAddActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientAdapter = new ClientAdapter(getActivity(), Client.getClients());
        setListAdapter(clientAdapter);

        filter = new IntentFilter(ClientAddActivity.ADD_CLIENT_ACTION);
        getActivity().registerReceiver(receiver,filter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof OnClientSelectedListener) {
            listener = (OnClientSelectedListener) activity;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick: ");
        if (listener != null) {
            listener.onClientSelected(position);
        }

    }
}
