package com.example.androidds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView singup=findViewById(R.id.singup);
        //
        Button btnLogin=findViewById(R.id.btnLogin);
        EditText login_edtemail=findViewById(R.id.login_edtemail);
        EditText login_edtpass=findViewById(R.id.login_edtpass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_edtemail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter your email",Toast.LENGTH_SHORT).show();
                }
                if(login_edtpass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter your password",Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                if(!(login_edtemail.getText().toString().isEmpty()&&login_edtpass.getText().toString().isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(login_edtemail.getText().toString(),login_edtpass.getText().toString()).addOnCompleteListener((task -> {
                        if(task.isSuccessful()){
                            String uid=task.getResult().getUser().getUid();
                            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                            firebaseDatabase.getReference().child("Users").child(uid).child("type").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String type=snapshot.getValue(String.class);
                                   if (type.equals("fournisseur")) {
                                           Intent in = new Intent(LoginActivity.this,  AcceuilFourni.class);
                                      startActivity(in);
                                   }
                                   if (type.equals("admin")) {
                                        Intent ina = new Intent(LoginActivity.this, AcceuilAdmin.class);
                                        startActivity(ina);
                                    }

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }));
                }
            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

}
