package com.adriel.onetohundred;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public abstract class InGameActivity extends AppCompatActivity {

    protected ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        viewPager2 = findViewById(R.id.container);
        viewPager2.setUserInputEnabled(false);
        setupViewPager(viewPager2);

        // Scroll to first fragment (EnterNumberFragment)
        viewPager2.setCurrentItem(0);
    }

    // Allow for each fragment to scroll to the others
    public void setViewPager(int fragmentNumber) {
        viewPager2.setCurrentItem(fragmentNumber);
    }

    protected abstract void setupViewPager(ViewPager2 viewPager2);

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.exit_round)
                .setMessage(R.string.exit_round_prompt)
                .setPositiveButton(R.string.yes, (dialog, which) -> finish())
                .setNegativeButton(R.string.no, null)
                .show();
    }

}
