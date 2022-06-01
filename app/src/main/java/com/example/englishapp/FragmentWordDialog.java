package com.example.englishapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FragmentWordDialog extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "WORD";
    private static final String ARG_PARAM2 = "TRANSCRIPTION";
    private static final String ARG_PARAM3 = "CATEGORY_NAME";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentWordDialog() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentWordDialog newInstance(String param1, String param2) {
        FragmentWordDialog fragment = new FragmentWordDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam2 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_dialog, container, false);

        TextView viewWord = view.findViewById(R.id.textWordDialog);
        TextView viewTranscription = view.findViewById(R.id.textTranscriptionDialog);

        viewWord.setText(getArguments().getString("WORD"));
        viewTranscription.setText(getArguments().getString("TRANSCRIPTION"));

        return view;
    }
}