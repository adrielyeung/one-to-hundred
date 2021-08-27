package com.adriel.onetohundred;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends NoTitleActivity {

    private static final int SPLASH_TIME = 2000;

    // Animation variables
    private Animation topAnim, bottomAnim;
    private ImageView imageView;
    private TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Hook animation to image
        imageView = findViewById(R.id.appIcon);
        appTitle = findViewById(R.id.appTitle);

        imageView.setAnimation(topAnim);
        appTitle.setAnimation(bottomAnim);

        // Handler to progress to next screen after delaying for SPLASH_TIME secs
        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            // Remove the splash screen or "back" button will return here
            finish();
        }, SPLASH_TIME);
    }

}