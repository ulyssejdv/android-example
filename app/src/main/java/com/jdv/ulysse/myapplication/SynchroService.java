package com.jdv.ulysse.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.jdv.ulysse.myapplication.activities.ClientAddActivity;
import com.jdv.ulysse.myapplication.activities.HomeActivity;
import com.jdv.ulysse.myapplication.models.Client;
import com.jdv.ulysse.myapplication.services.ClientRestService;

import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class SynchroService extends IntentService {

    private NotificationManager mn;
    private ClientRestService service;

    public SynchroService() {
        super("SynchroService");

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient httpClient = builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.7:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        service = retrofit.create(ClientRestService.class);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mn = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void getNotif(String msg) {
        NotificationCompat.Builder notif = new NotificationCompat.Builder(this);
        notif.setContentTitle("Synchronisation des clients");
        notif.setContentText(msg);
        notif.setWhen(new Date().getTime());
        notif.setSmallIcon(android.R.drawable.stat_notify_sync);

        Intent[] intents = {new Intent(this, HomeActivity.class)};
        notif.setContentIntent(PendingIntent.getActivities(this, 1, intents, 0));
        mn.notify(1, notif.build());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        getNotif("Synchro en cours");

        Call<List<Client>> clients = service.getClients();

        clients.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> results = response.body();
                for (Client c : results) {
                    Client.addClient(c);
                }

                getNotif("Synchro terminée");

                sendBroadcast(new Intent(ClientAddActivity.ADD_CLIENT_ACTION));
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e(TAG, "onFailure: erreur de synchro clients");
                Toast.makeText(
                        SynchroService.this,
                        "Erreur de synchro des clients",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
