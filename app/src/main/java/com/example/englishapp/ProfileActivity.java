package com.example.englishapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private AlertDialog.Builder dialog;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private String dbLink = "https://englishapp-df661-default-rtdb.asia-southeast1.firebasedatabase.app";
    private DatabaseReference currentUser;

    private Button profileButtonUpdate, profileButtonSgnOut, profileButtonChangePass;

    private EditText login;
    private EditText oldPassword;
    private EditText newPassword;
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
        profileButtonChangePass = findViewById(R.id.profileButtonChangePass);

        db = FirebaseDatabase.getInstance(dbLink);
        currentUser = db.getReference("User_data").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        getUserData();

        //Change user's info click
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
        //Sign out click
        profileButtonSgnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(ProfileActivity.this, AuthActivity.class);
                startActivity(in);
            }
        });
        //Change password click
        profileButtonChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangePasswordDialog();
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

    private void showChangePasswordDialog(){

        LayoutInflater inflater = LayoutInflater.from(this);

        View dialogWindow = inflater.inflate(R.layout.change_password_dialog, null);
        dialog = new AlertDialog.Builder(this);
        dialog.setView(dialogWindow);

        user = FirebaseAuth.getInstance().getCurrentUser();
        login = dialogWindow.findViewById(R.id.dialogLogin);
        oldPassword = dialogWindow.findViewById(R.id.dialogOldPassword);
        newPassword = dialogWindow.findViewById(R.id.dialogNewPassword);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                AuthCredential credential = EmailAuthProvider
                        .getCredential(login.getText().toString(), oldPassword.getText().toString());
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Snackbar.make(findViewById(R.id.root), "Password updated", Snackbar.LENGTH_LONG)
                                                        .show();
                                            } else {
                                                Snackbar.make(findViewById(R.id.root), "Error password not updated", Snackbar.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    });
                                } else {
                                    Snackbar.make(findViewById(R.id.root), "Error auth failed", Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
            }
        });
        dialog.setNegativeButton("Отмена", null);

        dialog.create().show();
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