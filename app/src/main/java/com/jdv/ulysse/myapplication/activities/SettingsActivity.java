package com.jdv.ulysse.myapplication.activities;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.jdv.ulysse.myapplication.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
