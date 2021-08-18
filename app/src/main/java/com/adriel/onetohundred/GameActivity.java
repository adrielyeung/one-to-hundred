package com.adriel.onetohundred;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.adriel.onetohundred.adapter.StartActivityAdapter;
import com.adriel.onetohundred.fragment.ConfirmFragment;
import com.adriel.onetohundred.fragment.EnterNumberFragment;

public class GameActivity extends InGameActivity {

    @Override
    protected void setupViewPager(ViewPager2 viewPager2) {
        StartActivityAdapter adapter = new StartActivityAdapter(getSupportFragmentManager(),
                getLifecycle());
        adapter.addFragment(EnterNumberFragment.newInstance(true), "EnterNumberFragment");
        adapter.addFragment(ConfirmFragment.newInstance(true), "ConfirmFragment");
        viewPager2.setAdapter(adapter);
    }

}