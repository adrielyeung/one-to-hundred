package com.adriel.onetohundred;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button instructionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.mainMenuButton);
        instructionButton = findViewById(R.id.instructionButton);

        configureStartButton();
        configureInstructionButton();
    }

    private void configureStartButton() {
        startButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StartActivity.class)));
    }

    private void configureInstructionButton() {
        instructionButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InstructionActivity.class)));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.exit_game)
                .setMessage(R.string.exit_game_prompt)
                .setPositiveButton(R.string.yes, (dialog, which) -> finish())
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
