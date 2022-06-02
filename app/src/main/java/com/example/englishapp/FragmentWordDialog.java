package com.example.englishapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FragmentWordDialog extends Fragment {

    private static final String ARG_PARAM1 = "WORD_NAME";
    private static final String ARG_PARAM2 = "WORD_TRANSLATION";
    private static final String ARG_PARAM3 = "WORD_TRANSCRIPTION";

    private String wordName;
    private String wordTranslation;
    private String wordTranscription;

    private TextView viewWord;
    private TextView viewTranscription;
    private TextView viewTranslation;
    private String wordSoundRes = "https://www.english-easy.info/talker/words/";

    public FragmentWordDialog() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentWordDialog newInstance(String wordName, String wordTranslation, String wordTranscription) {
        FragmentWordDialog fragment = new FragmentWordDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, wordName);
        args.putString(ARG_PARAM2, wordTranslation);
        args.putString(ARG_PARAM3, wordTranscription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            wordName = getArguments().getString(ARG_PARAM1);
            wordTranslation = getArguments().getString(ARG_PARAM2);
            wordTranscription = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_dialog, container, false);

        viewWord = view.findViewById(R.id.textWordDialog);
        viewTranscription = view.findViewById(R.id.textTranscriptionDialog);
        //viewTranslation = view.findViewById(R.id.textTranslationDialog);

        viewWord.setText(wordName);
        viewTranscription.setText(wordTranscription);
        //viewTranslation.setText(wordTranslation);

        view.findViewById(R.id.wordSoundButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer.create(getActivity(), Uri.parse(wordSoundRes + wordName.toLowerCase() + ".mp3"))
                        .start();
            }
        });
        return view;
    }
}