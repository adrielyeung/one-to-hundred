package com.adriel.onetohundred;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends NoTitleActivity {

    private Button mainButton;
    private TextView endMsgTextView, reasonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String loserName = (String)
                getIntent().getSerializableExtra(String.class.getCanonicalName());

        int bombNumber = (int) getIntent().getSerializableExtra(Integer.class.getCanonicalName());

        endMsgTextView = findViewById(R.id.appNameTextView);
        endMsgTextView.setText(String.format(getString(R.string.finish_game), loserName));

        reasonTextView = findViewById(R.id.reasonTextView);
        // Forced trigger
        if ((boolean) getIntent().getSerializableExtra(Boolean.class.getCanonicalName())) {
            reasonTextView.setText(String.format(getString(R.string.forced_trigger_prompt),
                    (bombNumber - 1), (bombNumber + 1)));
        } else {
            reasonTextView.setText(String.format(getString(R.string.hit_bomb_number_prompt),
                    bombNumber));
        }

        mainButton = findViewById(R.id.mainMenuButton);

        configureToMainButton();

        // Add bomb explosion sound
        MediaPlayer.create(EndActivity.this, R.raw.bomb_explosion).start();
    }

    private void configureToMainButton() {
        mainButton.setOnClickListener(v -> finish());
    }
}