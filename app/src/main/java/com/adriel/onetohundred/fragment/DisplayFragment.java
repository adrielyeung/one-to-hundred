package com.adriel.onetohundred.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.adriel.onetohundred.DisplayActivity;
import com.adriel.onetohundred.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";
    private static final String ARG_DISPLAYTEXT = "displayText";

    private int id;
    private String displayText;

    private TextView contentTextView;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param displayText Text to display on Fragment.
     * @return A new instance of fragment DisplayFragment.
     */
    public static DisplayFragment newInstance(int id, String displayText) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_DISPLAYTEXT, displayText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
            displayText = getArguments().getString(ARG_DISPLAYTEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        contentTextView = view.findViewById(R.id.contentTextView);
        contentTextView.setText(displayText);
        return view;
    }

    // When each fragment is displayed to user through scrolling,
    // need to set position "pos" in Activity class,
    // so that when buttons are used next, it can scroll to correct page
    @Override
    public void onResume() {
        super.onResume();
        ((DisplayActivity)getActivity()).setPos(id);
    }
}