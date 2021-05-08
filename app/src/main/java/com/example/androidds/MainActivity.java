package com.example.androidds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText edtname, edtphonenumber, edtEmail, edtPass, usertype;
    Button  singup;
    TextView backtologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backtologin = findViewById(R.id.backtologin);
        singup = findViewById(R.id.btnsingup);

        edtname = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtphonenumber = findViewById(R.id.phonenumber);
        usertype = findViewById(R.id.usertypee);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter your name", Toast.LENGTH_SHORT).show();

                }

                if (edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter your mail", Toast.LENGTH_SHORT).show();

                }
                if (edtPass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();

                }
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if (!(edtname.getText().toString().isEmpty() && edtEmail.getText().toString().isEmpty() && edtPass.getText().toString().isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPass.getText().toString()).addOnCompleteListener((task -> {

                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();

                            Users user = new Users(uid, edtname.getText().toString(), edtphonenumber.getText().toString(), edtEmail.getText().toString(), edtPass.getText().toString(), usertype.getText().toString());

                            firebaseDatabase.getReference().child("Users").child(uid).setValue(user);

                            Intent in = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }));
                }

            }
        });
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}