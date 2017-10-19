package com.jdv.ulysse.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.models.Client;

public class ClientDetailsFragment extends Fragment {

    private static final String TAG = "ClientDetailsFragment";

    private TextView nom;
    private TextView prenom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_detail_client, container, true);
        nom = (TextView) view.findViewById(R.id.nomDetail);
        prenom = (TextView) view.findViewById(R.id.prenomDetail);
        return view;
    }

    public void updateClient(int id) {
        Client client = Client.getClients().get(id);
        nom.setText(client.getLastName());
        prenom.setText(client.getFirstName());
    }
}
