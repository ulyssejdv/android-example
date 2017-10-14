package com.jdv.ulysse.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.models.Client;

import java.util.List;

/**
 * Created by ulysse on 05/10/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {

    //tweets est la liste des models à afficher
    public ClientAdapter(Context context, List<Client> clients) {
        super(context, 0, clients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_adapter,parent, false);
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
        viewHolder.nom.setText(client.getLastName());
        viewHolder.prenom.setText(client.getFirstName());
        viewHolder.avatar.setImageResource(R.mipmap.ic_account_circle_black_24dp);
        return convertView;
    }

    private class ClientViewHolder{
        public TextView nom;
        public TextView prenom;
        public ImageView avatar;
    }
}
