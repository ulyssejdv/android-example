package com.jdv.ulysse.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.jdv.ulysse.myapplication.R;
import com.jdv.ulysse.myapplication.models.Client;

public class ClientAddActivity extends AppCompatActivity {



    private TextView selAge;

    private EditText lastnameEditText;
    private EditText firstnameEditText;
    private SeekBar ageControl;
    private String genre;
    private Spinner spinner;

    private static final String TAG = "ClientAddActivity";
    private EditText emailEditText;
    private Switch actifSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_add);
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
        Client.addClient(client);

        // Go to the list activity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
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
