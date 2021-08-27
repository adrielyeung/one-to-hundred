package com.adriel.onetohundred.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.adriel.onetohundred.InGameActivity;
import com.adriel.onetohundred.R;
import com.adriel.onetohundred.viewmodel.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PromptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PromptFragment extends Fragment {

    // Argument to check whether activity is the GameActivity
    private static final String ARG_IN_GAME = "in_game";
    private boolean inGame;
    private String thisPlayerTitle, nextPlayerTitle;

    private TextView playerTextView, passDeviceTextView;
    private Button continueButton;
    private SharedViewModel viewModel;

    public PromptFragment(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param inGame true if an activity within the game session is calling,
     *               false otherwise
     *
     * @return A new instance of fragment PromptFragment.
     */
    public static PromptFragment newInstance(boolean inGame) {
        return new PromptFragment(inGame);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            inGame = getArguments().getBoolean(ARG_IN_GAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prompt, container, false);
        playerTextView = view.findViewById(R.id.playerTextViewPrompt);
        passDeviceTextView = view.findViewById(R.id.passDeviceTextView);
        continueButton = view.findViewById(R.id.continueButton);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Set up viewModel to get items from other fragment
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if (this.inGame) {
            viewModel.getData().observe(getViewLifecycleOwner(), transferObject -> {
                        thisPlayerTitle = transferObject.getThisPlayerTitle();
                        nextPlayerTitle = transferObject.getNextPlayerTitle();
                    }
            );

            playerTextView.setText(thisPlayerTitle);
            passDeviceTextView.setText(String.format(getString(R.string.pass_device_prompt),
                    nextPlayerTitle, nextPlayerTitle));
            // Scroll to EnterNumberFragment
            continueButton.setOnClickListener(view1 ->
                    ((InGameActivity)getActivity()).setViewPager(0));
        } else {
            playerTextView.setText(R.string.any_player);
            passDeviceTextView.setText(String.format(getString(R.string.pass_device_prompt),
                    getString(R.string.judge), getString(R.string.judge)));
            // Scroll to EnterNameFragment
            continueButton.setOnClickListener(view1 ->
                    ((InGameActivity)getActivity()).setViewPager(3));
        }
    }
}