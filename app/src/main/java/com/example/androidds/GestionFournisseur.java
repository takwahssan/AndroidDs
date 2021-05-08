package com.example.androidds;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GestionFournisseur extends AppCompatActivity {
    EditText edtname, edtphonenumber, edtEmail, edtPass, usertypee;
    Button buttonAdd, btnRetreive;
    DatabaseReference databaseFournisseurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_fournisseur);
        databaseFournisseurs = FirebaseDatabase.getInstance().getReference();
        edtname = (EditText) findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtphonenumber = findViewById(R.id.phonenumber);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        usertypee = (EditText) findViewById(R.id.usertype);

        btnRetreive = findViewById(R.id.btnRetreiveData);
        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GestionFournisseur.this, RetreiveData.class);
                startActivity(i);
                finish();
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFournisseur();
            }
        });
    }
    private void addFournisseur(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        //// if(!(edtname.getText().toString().isEmpty()&&edtEmail.getText().toString().isEmpty()&&edtPass.getText().toString().isEmpty())){
        //// String id= databaseFournisseurs.push().getKey();
        //// Users user = new Users(id, edtname.getText().toString(), edtphonenumber.getText().toString(), edtEmail.getText().toString(), edtPass.getText().toString(), usertypee.getText().toString());
        //// databaseFournisseurs.child("Users").child(id).setValue(user);
        //// Toast.makeText(this, "fournisseur added", Toast.LENGTH_LONG).show();
        // // } else {
        ////  Toast.makeText(this, "fournisseur not added", Toast.LENGTH_LONG).show();
        /// /}
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if(!(edtname.getText().toString().isEmpty()&&edtEmail.getText().toString().isEmpty()&&edtPass.getText().toString().isEmpty())){
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPass.getText().toString()).addOnCompleteListener((task -> {

                if (task.isSuccessful()) {
                    String uid = task.getResult().getUser().getUid();

                    Users user = new Users(uid, edtname.getText().toString(), edtphonenumber.getText().toString(), edtEmail.getText().toString(), edtPass.getText().toString(),usertypee.getText().toString());

                    firebaseDatabase.getReference().child("Users").child(uid).setValue(user);


                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }


            }));
        }
    }
}
