package com.adriel.onetohundred.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adriel.onetohundred.GameActivity;
import com.adriel.onetohundred.R;
import com.adriel.onetohundred.util.TransferObject;
import com.adriel.onetohundred.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnterNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterNameFragment extends Fragment {

    private TextView playerTextView;
    private EditText editText;
    private Button nextStepButton;
    private SharedViewModel viewModel;
    private int numOfPlayers;
    private int stage = 0;
    private List<String> nameList = new ArrayList<>();

    public EnterNameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EnterNameFragment.
     */
    public static EnterNameFragment newInstance() {
        return new EnterNameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_name, container, false);

        editText = view.findViewById(R.id.editTextName);

        nextStepButton = view.findViewById(R.id.nextStepButtonEnterName);
        nextStepButton.setOnClickListener(nextStepListener);
        playerTextView = view.findViewById(R.id.playerTextViewEnterName);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up viewModel to get number of players from other fragment
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), transferObject -> {
            numOfPlayers = transferObject.getInteger();
        });
    }

    View.OnClickListener nextStepListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Load name in editText to name list
            String name = String.valueOf(editText.getText());

            if ("".equals(name)) {
                Toast errToast = Toast.makeText(getContext(),
                        R.string.name_empty_error, Toast.LENGTH_LONG);
                errToast.show();
                return;
            }

            // Confirm dialog
            new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.confirm_name_title)
                    .setMessage(getConfirmMessage(name))
                    .setPositiveButton(R.string.confirm, (dialog, which) -> {
                        nameList.add(name);

                        // Clear editText
                        editText.setText("");

                        stage++;
                        if (nameList.size() <= numOfPlayers) {
                            // Prompt for input for next name
                            playerTextView.setText(String.format(getString(R.string.player), stage));
                        } else {
                            // All names input, load nameList into SharedViewModel
                            TransferObject.getInstance().setStringList(nameList);
                            viewModel.setData(TransferObject.getInstance());

                            // Start GameActivity and finish StartActivity
                            Intent startGameIntent = new Intent(getActivity(), GameActivity.class);
                            startGameIntent.putExtra(TransferObject.class.getCanonicalName(),
                                    TransferObject.getInstance());
                            startActivity(startGameIntent);
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton(R.string.change, null)
                    .show();
        }

        private String getConfirmMessage(String name) {
            if (stage >= numOfPlayers) {
                return String.format(getString(R.string.confirm_name), name,
                        getString(R.string.judge));
            }
            return String.format(getString(R.string.confirm_name), name,
                    String.format(getString(R.string.player), stage+1));
        }
    };
}