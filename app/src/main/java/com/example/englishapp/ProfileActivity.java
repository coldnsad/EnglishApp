package com.example.englishapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private String dbLink = "https://englishapp-df661-default-rtdb.asia-southeast1.firebasedatabase.app";
    private DatabaseReference currentUser;

    private Button profileButtonUpdate, profileButtonSgnOut, profileButtonChangePass;

    private EditText emailEdit;
    private EditText passEdit;
    private EditText nameEdit;
    private EditText secondNameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameEdit = findViewById(R.id.profileNameEdit);
        secondNameEdit = findViewById(R.id.profileSecondNameEdit);

        profileButtonUpdate = findViewById(R.id.profileButtonUpdate);
        profileButtonSgnOut = findViewById(R.id.profileButtonSgnOut);

        //emailEdit.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        db = FirebaseDatabase.getInstance(dbLink);
        currentUser = db.getReference("User_data").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        getUserData();

        profileButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameEdit.getText().toString())) {
                    Snackbar.make(findViewById(R.id.root), "Введние ваше имя", Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(secondNameEdit.getText().toString())) {
                    Snackbar.make(findViewById(R.id.root), "Введние вашу фамилию", Snackbar.LENGTH_LONG)
                            .show();
                    return;
                }
                User user = new User(
                        FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                        nameEdit.getText().toString(),
                        secondNameEdit.getText().toString()
                );
                currentUser.removeValue();
                currentUser
                        .push()
                        .setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(findViewById(R.id.root), "Данные обновлены успешно", Snackbar.LENGTH_LONG)
                                        .show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(findViewById(R.id.root), "Неудачно: " + e.getMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                });


            }
        });

        profileButtonSgnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(ProfileActivity.this, AuthActivity.class);
                startActivity(in);
            }
        });
    }

    private void getUserData() {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    nameEdit.setText(user.getName());
                    secondNameEdit.setText(user.getSecondName());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        currentUser.addValueEventListener(valueEventListener);
    }

    public void openMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openDictionary(View view) {
        Intent intent = new Intent(this, DictionaryActivity.class);
        startActivity(intent);
    }
}