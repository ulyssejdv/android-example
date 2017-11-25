package com.jdv.ulysse.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.SynchroService;
import com.jdv.ulysse.myapplication.models.Client;
import com.jdv.ulysse.myapplication.services.ClientRestService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ClientAddActivity extends AppCompatActivity {


    public static final String ADD_CLIENT_ACTION = "ADD_CLIENT_ACTION";
    private TextView selAge;

    private EditText lastnameEditText;
    private EditText firstnameEditText;
    private SeekBar ageControl;
    private String genre;
    private Spinner spinner;

    private static final String TAG = "ClientAddActivity";
    private EditText emailEditText;
    private Switch actifSwitch;
    private ClientRestService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_add);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        lastnameEditText = (EditText) findViewById(R.id.lastname_edit_text);
        firstnameEditText = (EditText) findViewById(R.id.firstname_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        actifSwitch = (Switch) findViewById(R.id.switch1);
        ageControl = (SeekBar) findViewById(R.id.seekBar);
        selAge = (TextView) findViewById(R.id.sel_age);
        selAge.setText(String.valueOf(ageControl.getProgress()));
        ageControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selAge.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.levels_array, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);

        OkHttpClient httpClient = builder.build();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ama-gestion-clients.appspot.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        service = retrofit.create(ClientRestService.class);

    }

    public void onAddButtonClick(View view) {

        // Create a new client with form data
        Client client = new Client();
        client.setFirstName(firstnameEditText.getText().toString());
        client.setLastName(lastnameEditText.getText().toString());
        client.setActiv(actifSwitch.isChecked());
        client.setAge(Integer.parseInt(selAge.getText().toString()));
        client.setEmail(emailEditText.getText().toString());
        client.setGender(genre);
        client.setLevel(spinner.getSelectedItem().toString());

        // Add client to the list (DB like)
        // Client.addClient(client);

        Call<Void> voidCall = service.addClient(client);
        voidCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: OK");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: OK");
            }
        });

        // Go to the list activity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);


        sendBroadcast(new Intent(ADD_CLIENT_ACTION));
        finish();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioMan:
                if (checked)
                    genre = "Homme";
                    break;
            case R.id.radioWoman:
                if (checked)
                    genre = "Femme";
                    break;
        }
    }
}
