package com.jdv.ulysse.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jdv.ulysse.myapplication.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onGoFormClick(View view) {
        Intent intent = new Intent(this, ClientAddActivity.class);
        startActivity(intent);
    }

    public void onGoListClick(View view) {
        Intent intent = new Intent(this, ClientListActivity.class);
        startActivity(intent);
    }
}
