package com.jdv.ulysse.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.models.Client;

import org.w3c.dom.Text;

import java.io.FileOutputStream;

public class ClientDetailsFragment extends Fragment {

    private static final String TAG = "ClientDetailsFragment";

    private TextView nom;
    private TextView prenom;
    private TextView email;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.detail_client, container, true);
        nom = (TextView) view.findViewById(R.id.nomDetail);
        prenom = (TextView) view.findViewById(R.id.prenomDetail);
        email = (TextView) view.findViewById(R.id.emailDetail);

        imageView = (ImageView) view.findViewById(R.id.avatar_imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 111);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void updateClient(int id) {
        Client client = Client.getClients().get(id);
        nom.setText(client.getLastName());
        prenom.setText(client.getFirstName());
    }
}
