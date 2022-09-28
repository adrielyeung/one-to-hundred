package com.adriel.onetohundred;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.adriel.onetohundred.adapter.StartActivityAdapter;
import com.adriel.onetohundred.fragment.DisplayFragment;

import java.util.ArrayList;
import java.util.List;

public abstract class DisplayActivity extends AppCompatActivity {

    protected List<String> displayContentSequence = new ArrayList<>();
    protected String displayTitle;
    protected int pos = 0;

    protected ViewPager2 viewPager2;
    private TextView titleTextView;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private Button mainButton;

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setDisplayTitle();
        insertDisplay();

        viewPager2 = findViewById(R.id.container);
        setupViewPager(viewPager2, displayContentSequence.size());

        // Scroll to first fragment
        viewPager2.setCurrentItem(0);

        titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(displayTitle);

        previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(v -> scrollBackward());

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> scrollForward());

        mainButton = findViewById(R.id.toMainButton);
        mainButton.setOnClickListener(v -> finish());
    }

    protected abstract void setDisplayTitle();

    // Override in each child method to provide display title and content
    protected abstract void insertDisplay();

    private void setupViewPager(ViewPager2 viewPager2, int numPages) {
        StartActivityAdapter adapter = new StartActivityAdapter(getSupportFragmentManager(),
                getLifecycle());
        for (int i = 0; i < numPages; i++) {
            adapter.addFragment(DisplayFragment.newInstance(i, displayContentSequence.get(i)),
                    String.format("DisplayFragment%s", i+1));
        }

        viewPager2.setAdapter(adapter);
    }

    // Allow for each fragment to scroll to the others
    private void scrollForward() {
        pos++;
        if (pos >= displayContentSequence.size()) {
            pos = displayContentSequence.size() - 1;
            Toast.makeText(getApplicationContext(), getString(R.string.last_page_error),
                    Toast.LENGTH_LONG).show();
            return;
        }
        viewPager2.setCurrentItem(pos);
    }

    private void scrollBackward() {
        pos--;
        if (pos < 0) {
            pos = 0;
            Toast.makeText(getApplicationContext(), getString(R.string.first_page_error),
                    Toast.LENGTH_LONG).show();
            return;
        }
        viewPager2.setCurrentItem(pos);
    }
}
