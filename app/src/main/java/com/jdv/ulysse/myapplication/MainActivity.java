package com.jdv.ulysse.myapplication;

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

public class MainActivity extends AppCompatActivity {



    private TextView sel_age;

    private EditText lastnameEditText;
    private EditText firstnameEditText;
    private SeekBar ageControl;
    private String genre;
    private Spinner spinner;

    private static final String TAG = "MainActivity";
    private EditText emailEditText;
    private Switch actifSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lastnameEditText = (EditText) findViewById(R.id.lastname_edit_text);
        firstnameEditText = (EditText) findViewById(R.id.firstname_edit_text);
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        actifSwitch = (Switch) findViewById(R.id.switch1);
        ageControl = (SeekBar) findViewById(R.id.seekBar);
        sel_age = (TextView) findViewById(R.id.sel_age);
        sel_age.setText(String.valueOf(ageControl.getProgress()));
        ageControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sel_age.setText(String.valueOf(progress));
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
        String lastName = lastnameEditText.getText().toString();
        String firstname = firstnameEditText.getText().toString();
        int age = Integer.parseInt(sel_age.getText().toString());
        String email = emailEditText.getText().toString();
        String level = spinner.getSelectedItem().toString();
        Boolean act = actifSwitch.isChecked();
        Client c = new Client(firstname, lastName, age, email, genre, level, act);
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
