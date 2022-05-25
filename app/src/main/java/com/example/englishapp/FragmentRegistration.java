package com.example.englishapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentRegistration extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnRegistrate;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private String dbLink = "https://englishapp-df661-default-rtdb.asia-southeast1.firebasedatabase.app";
    private DatabaseReference users;

    private EditText emailEdit;
    private EditText passEdit;
    private EditText nameEdit;

    public FragmentRegistration() {

    }


    public static FragmentRegistration newInstance() {
        FragmentRegistration fragment = new FragmentRegistration();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        emailEdit = v.findViewById(R.id.emailEdit);
        passEdit = v.findViewById(R.id.passwordEdit);
        nameEdit = v.findViewById(R.id.nickNameEdit);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance(dbLink);
        users = db.getReference("User_data");


        btnRegistrate = v.findViewById(R.id.buttonRegistration);
        btnRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(emailEdit.getText().toString())){
                    Snackbar.make(getActivity().findViewById(R.id.root), "Введние почту", Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }
                if(passEdit.getText().toString().length() < 5){
                    Snackbar.make(getActivity().findViewById(R.id.root), "Пароль не менее 5 символов", Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }
                if(TextUtils.isEmpty(nameEdit.getText().toString())){
                    Snackbar.make(getActivity().findViewById(R.id.root), "Введние ваше имя(никнейм)", Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }
                auth.createUserWithEmailAndPassword(emailEdit.getText().toString(), passEdit.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(emailEdit.getText().toString());
                                user.setNickName(nameEdit.getText().toString());
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(getActivity().findViewById(R.id.root), "Регистрация прошла успешно", Snackbar.LENGTH_LONG)
                                                        .show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(getActivity().findViewById(R.id.root), "Неудачно: " + e.getMessage(), Snackbar.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(getActivity().findViewById(R.id.root), "Неудачно(регистрация): " + e.getMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });

        return v;
    }
}