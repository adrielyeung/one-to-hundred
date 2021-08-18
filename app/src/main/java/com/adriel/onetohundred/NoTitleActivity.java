package com.adriel.onetohundred;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class NoTitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide app title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

}
