package com.adriel.onetohundred;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InstructionActivity extends NoTitleActivity {

    private List<String> instruction_steps = new ArrayList<>();
    private int pos = 0;
    private Button mainButton;
    private ImageButton nextButton;
    private TextView instructionTextView;
    private ImageButton previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        mainButton = findViewById(R.id.toMainButton);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        instructionTextView = findViewById(R.id.instructionTextView);

        insertInstructions();
        configureToMainButton();
        configureNextButton();
        configurePreviousButton();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void insertInstructions() {
        instruction_steps.add(getString(R.string.instruction_1));
        instruction_steps.add(getString(R.string.instruction_2));
        instruction_steps.add(getString(R.string.instruction_3));
        instruction_steps.add(getString(R.string.instruction_4));
        instruction_steps.add(getString(R.string.instruction_5));
        instruction_steps.add(getString(R.string.instruction_6));
        instruction_steps.add(getString(R.string.instruction_7));
        instruction_steps.add(getString(R.string.instruction_8));
        instruction_steps.add(getString(R.string.instruction_9));
        instruction_steps.add(getString(R.string.instruction_10));
    }

    private void configureToMainButton() {
        mainButton.setOnClickListener(v -> finish());
    }

    private void configureNextButton() {
        nextButton.setOnClickListener(nextListener);
    }

    private void configurePreviousButton() {
        previousButton.setOnClickListener(previousListener);
    }

    View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pos += 1;
            if (pos >= instruction_steps.size()) {
                pos = instruction_steps.size()-1;
            }
            instructionTextView.setText(instruction_steps.get(pos));
        }
    };

    View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pos -= 1;
            if (pos < 0) {
                pos = 0;
            }
            instructionTextView.setText(instruction_steps.get(pos));
        }
    };
}
