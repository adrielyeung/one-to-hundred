package com.adriel.onetohundred.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.adriel.onetohundred.EndActivity;
import com.adriel.onetohundred.InGameActivity;
import com.adriel.onetohundred.R;
import com.adriel.onetohundred.util.InputFilterMinMax;
import com.adriel.onetohundred.util.TransferObject;
import com.adriel.onetohundred.viewmodel.SharedViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Allow users to enter a number. Used in both setting number of players and within game session.
 *
 * Use the {@link EnterNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterNumberFragment extends Fragment {

    // Argument to check whether activity is the GameActivity
    private static final String ARG_IN_GAME = "in_game";
    private boolean inGame;
    private int bombNumber;
    private int guessNumber;
    private int rangeMin = 1;
    private int rangeMax = 100;
    private int playerMin = 2;
    private int playerMax = 9;
    private int playerNum = 0;
    private SharedViewModel viewModel;
    private List<String> nameList;

    private EditText editText;
    private Button nextStepButton;
    private TextView playerTextView;
    private TextView guideTextView;
    private TextView numMin;
    private TextView numMax;

    public EnterNumberFragment(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param inGame true if an activity within the game session is calling,
     *               false otherwise
     *
     * @return A new instance of fragment EnterNumberFragment.
     */
    public static EnterNumberFragment newInstance(boolean inGame) {
        return new EnterNumberFragment(inGame);
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
        View view =  inflater.inflate(R.layout.fragment_enter_number, container, false);

        editText = view.findViewById(R.id.editTextNumber);

        nextStepButton = view.findViewById(R.id.nextStepButton);
        playerTextView = view.findViewById(R.id.playerTextView);
        guideTextView = view.findViewById(R.id.guideTextView);
        numMin = view.findViewById(R.id.numMin);
        numMax = view.findViewById(R.id.numMax);

        if (this.inGame) {
            // Must fix min filter to 1, otherwise numbers like "1"0 cannot be input
            editText.setFilters(new InputFilter[]{new InputFilterMinMax(
                    "1", String.valueOf(rangeMax - 1))});
            numMin.setText(String.valueOf(rangeMin));
            numMax.setText(String.valueOf(rangeMax));
            nextStepButton.setOnClickListener(startGameListener);
        } else {
            editText.setFilters(new InputFilter[]{new InputFilterMinMax(
                    String.valueOf(playerMin), String.valueOf(playerMax))});
            numMin.setText(String.valueOf(playerMin));
            numMax.setText(String.valueOf(playerMax));
            nextStepButton.setOnClickListener(nextStepListener);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up viewModel to transfer number of players data to other fragment
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), transferObject -> {});

        if (this.inGame) {
            TransferObject transferObject =
                    (TransferObject) getActivity().getIntent().getSerializableExtra(
                    TransferObject.class.getCanonicalName());
            nameList = transferObject.getStringList();

            // Initialise game with judge selecting bomb number
            playerTextView.setText(String.format(getString(R.string.judge_name),
                    nameList.get(0)));
            guideTextView.setText(R.string.guide_enter_bomb);
            editText.setText("");
        }
    }

    // When confirmed bomb / guess number and returned
    @Override
    public void onResume() {
        super.onResume();
        if (this.inGame) {
            viewModel.getData().observe(getViewLifecycleOwner(), transferObject -> {
                if (transferObject.isConfirmed()) {
                    transferObject.setConfirmed(false);

                    if (guessNumber > 0) {
                        // Check if bombNumber and guessNumber are equal
                        if (guessNumber > bombNumber) {
                            rangeMax = guessNumber;
                        } else if (guessNumber < bombNumber) {
                            rangeMin = guessNumber;
                        } else {
                            Intent endIntent = new Intent(getActivity(), EndActivity.class);
                            endIntent.putExtra(String.class.getCanonicalName(), nameList.get(playerNum));
                            startActivity(endIntent);
                            getActivity().finish();
                            return;
                        }
                    }

                    playerNum++;
                    if (playerNum >= nameList.size()) {
                        playerNum = 1;
                    }
                    // Set layout for next player
                    setNextPlayerLayout(playerNum);

                    // Change listener to nextPlayerListener
                    nextStepButton.setOnClickListener(nextPlayerListener);
                }
            });
        }
    }

    // Check if number entered is empty or does not fall in range
    // Returns number (String format) if no error
    private String checkNumberInRange(@NonNull EditText testEditText) {
        int testNumber;
        try {
            testNumber = Integer.parseInt(String.valueOf(testEditText.getText()));
        } catch (NumberFormatException e) {
            return getString(R.string.num_empty_error);
        }

        if (testNumber <= rangeMin || testNumber >= rangeMax) {
            return String.format(getString(R.string.num_not_in_range_error),
                    rangeMin, rangeMax);
        }

        return String.valueOf(testNumber);
    }

    // Set layout for each player
    private void setNextPlayerLayout(int player) {
        playerTextView.setText(String.format(getString(R.string.player_name),
                player, nameList.get(player)));
        guideTextView.setText(R.string.guide_guess_bomb);
        numMin.setText(String.valueOf(rangeMin));
        numMax.setText(String.valueOf(rangeMax));
        editText.setText("");
    }

    // Listens to judge selecting bomb number
    View.OnClickListener startGameListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String errMsg = checkNumberInRange(editText);

            // Check validity of bombNumber
            try {
                bombNumber = Integer.parseInt(errMsg);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), errMsg, Toast.LENGTH_LONG).show();
                return;
            }

            TransferObject.getInstance().setInteger(bombNumber);
            TransferObject.getInstance().setThisPlayerTitle(String.valueOf(playerTextView.getText()));
            TransferObject.getInstance().setNextPlayerTitle(String.format(getString(R.string.player_name),
                    1, TransferObject.getInstance().getStringList().get(1)));
            viewModel.setData(TransferObject.getInstance());

            // Scroll to next fragment (ConfirmFragment)
            ((InGameActivity)getActivity()).setViewPager(1);
        }
    };

    // Listens to each player inputting a guess of the bomb number
    View.OnClickListener nextPlayerListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            String errMsg = checkNumberInRange(editText);

            try {
                guessNumber = Integer.parseInt(errMsg);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), errMsg, Toast.LENGTH_LONG).show();
                return;
            }

            TransferObject.getInstance().setInteger(guessNumber);
            TransferObject.getInstance().setThisPlayerTitle(String.valueOf(playerTextView.getText()));
            if (playerNum + 1 >= TransferObject.getInstance().getStringList().size()) {
                TransferObject.getInstance().setNextPlayerTitle(String.format(getString(R.string.player_name),
                        1, TransferObject.getInstance().getStringList().get(1)));
            } else {
                TransferObject.getInstance().setNextPlayerTitle(String.format(getString(R.string.player_name),
                        playerNum+1, TransferObject.getInstance().getStringList().get(playerNum+1)));
            }

            viewModel.setData(TransferObject.getInstance());

            // Scroll to next fragment (ConfirmFragment)
            ((InGameActivity)getActivity()).setViewPager(1);
        }
    };

    // Listens to input of number of players
    View.OnClickListener nextStepListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Load number of players into viewModel
            int numOfPlayers;
            try {
                numOfPlayers = Integer.parseInt(String.valueOf(editText.getText()));
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(),
                        R.string.num_empty_error, Toast.LENGTH_LONG).show();
                return;
            }

            TransferObject.getInstance().setInteger(numOfPlayers);
            TransferObject.getInstance().setThisPlayerTitle(String.valueOf(playerTextView.getText()));
            TransferObject.getInstance().setNextPlayerTitle(getString(R.string.judge));
            viewModel.setData(TransferObject.getInstance());

            // Scroll to next fragment (ConfirmFragment)
            ((InGameActivity)getActivity()).setViewPager(1);
        }
    };
}