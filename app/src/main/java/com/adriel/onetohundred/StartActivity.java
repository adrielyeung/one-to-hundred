package com.adriel.onetohundred;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.adriel.onetohundred.adapter.StartActivityAdapter;
import com.adriel.onetohundred.fragment.ConfirmFragment;
import com.adriel.onetohundred.fragment.EnterNameFragment;
import com.adriel.onetohundred.fragment.EnterNumberFragment;

public class StartActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

    // Load the fragments in order of appearance
    private void setupViewPager(ViewPager2 viewPager2) {
        StartActivityAdapter adapter = new StartActivityAdapter(getSupportFragmentManager(),
                getLifecycle());
        adapter.addFragment(EnterNumberFragment.newInstance(false), "EnterNumberFragment");
        adapter.addFragment(ConfirmFragment.newInstance(false), "ConfirmFragment");
        adapter.addFragment(EnterNameFragment.newInstance(), "EnterNameFragment");
        viewPager2.setAdapter(adapter);
    }
}
