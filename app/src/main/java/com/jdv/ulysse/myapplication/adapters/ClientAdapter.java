package com.jdv.ulysse.myapplication.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.models.Client;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ulysse on 05/10/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences preferences;
    private static final String NAMING_MODE_PREFERENCE = "naming_mode_preferences";
    private static final String PRENOM_NOM = "PRENOM_NOM";

    private String namingMode;

    //tweets est la liste des models à afficher
    public ClientAdapter(Context context, List<Client> clients) {
        super(context, 0, clients);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        preferences.registerOnSharedPreferenceChangeListener(this);

        namingMode = preferences.getString(NAMING_MODE_PREFERENCE, PRENOM_NOM);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_list_item,parent, false);
        }

        ClientViewHolder viewHolder = (ClientViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ClientViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.prenom);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        List<Client> clients = Client.getClients();
        Client client = getItem(position);

        //il ne reste plus qu'à remplir notre vue

        if (PRENOM_NOM.equals(namingMode)) {
            viewHolder.nom.setText(client.getFirstName());
            viewHolder.prenom.setText(client.getLastName());
        } else {
            viewHolder.nom.setText(client.getLastName());
            viewHolder.prenom.setText(client.getFirstName());
        }

        viewHolder.avatar.setImageResource(R.drawable.ic_person_black_24dp);
        return convertView;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(NAMING_MODE_PREFERENCE)) {
            namingMode = sharedPreferences.getString(NAMING_MODE_PREFERENCE, PRENOM_NOM);
            notifyDataSetChanged();
        }
    }

    private class ClientViewHolder{
        public TextView nom;
        public TextView prenom;
        public ImageView avatar;
    }
}
