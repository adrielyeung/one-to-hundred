package com.adriel.onetohundred;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends NoTitleActivity {

    Button mainButton;
    TextView endMsgTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String loserName = (String)
                getIntent().getSerializableExtra(String.class.getCanonicalName());

        endMsgTextView = findViewById(R.id.endMsgTextView);
        endMsgTextView.setText(String.format(getString(R.string.finish_game), loserName));
        mainButton = findViewById(R.id.mainMenuButton);

        configureToMainButton();
    }

    private void configureToMainButton() {
        mainButton.setOnClickListener(v -> finish());
    }
}