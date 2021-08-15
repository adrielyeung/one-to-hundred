package com.adriel.onetohundred.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.adriel.onetohundred.GameActivity;
import com.adriel.onetohundred.R;
import com.adriel.onetohundred.StartActivity;
import com.adriel.onetohundred.util.TransferObject;
import com.adriel.onetohundred.viewmodel.SharedViewModel;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmFragment extends Fragment {

    // Argument to check whether activity is the GameActivity
    private static final String ARG_IN_GAME = "in_game";
    private boolean inGame;
    private TextView playerTextView;
    private TextView numTextView;
    private TextView passDeviceTextView;
    private Button confirmButton;
    private Button changeButton;
    private SharedViewModel viewModel;
    private int numberEntered;
    private String thisPlayerTitle;
    private String nextPlayerTitle;

    public ConfirmFragment(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param inGame true if an activity within the game session is calling,
     *                false otherwise
     * @return A new instance of fragment ConfirmFragment.
     */
    public static ConfirmFragment newInstance(boolean inGame) {
        return new ConfirmFragment(inGame);
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
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        playerTextView = view.findViewById(R.id.playerTextViewConfirm);
        numTextView = view.findViewById(R.id.numTextViewConfirm);
        passDeviceTextView = view.findViewById(R.id.passDeviceTextView);
        confirmButton = view.findViewById(R.id.confirmButton);
        changeButton = view.findViewById(R.id.changeButton);

        if (this.inGame) {
            confirmButton.setOnClickListener(confirmGuessNumberListener);
            changeButton.setOnClickListener(changeGuessNumberListener);
        } else {
            confirmButton.setOnClickListener(confirmNumPlayersListener);
            changeButton.setOnClickListener(changeNumPlayersListener);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up viewModel to get number of players from other fragment
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), transferObject -> {
            numberEntered = transferObject.getInteger();
            thisPlayerTitle = transferObject.getThisPlayerTitle();
            nextPlayerTitle = transferObject.getNextPlayerTitle();
            setLayout();
        });
    }

    private void setLayout() {
        playerTextView.setText(thisPlayerTitle);
        numTextView.setText(String.valueOf(numberEntered));
        passDeviceTextView.setText(String.format(getString(R.string.pass_device_prompt),
                nextPlayerTitle));
    }

    View.OnClickListener confirmNumPlayersListener = view -> {
        // Scroll to next fragment (EnterNameFragment)
        ((StartActivity)getActivity()).setViewPager(2);
    };

    View.OnClickListener changeNumPlayersListener = view -> {
        // Scroll to previous fragment (EnterNumberFragment)
        ((StartActivity)getActivity()).setViewPager(0);
    };

    View.OnClickListener confirmGuessNumberListener = view -> {
        TransferObject.getInstance().setConfirmed(true);
        ((GameActivity)getActivity()).setViewPager(0);
        viewModel.setData(TransferObject.getInstance());
    };

    View.OnClickListener changeGuessNumberListener = view ->  {
        TransferObject.getInstance().setConfirmed(false);
        ((GameActivity)getActivity()).setViewPager(0);
        viewModel.setData(TransferObject.getInstance());
    };
}