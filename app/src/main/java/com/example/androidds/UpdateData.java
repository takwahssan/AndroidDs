package com.example.androidds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateData extends AppCompatActivity {
    EditText etName,etPhone,etEmail,etPass,etType;
    Button btnUpdate;
    String namep,phonep,emailp,passp,typep,Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPass= findViewById(R.id.etPass);
        etType= findViewById(R.id.etType);

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        Uid=intent.getStringExtra("id");
        namep=intent.getStringExtra("name");
        phonep=intent.getStringExtra("phone");
        emailp=intent.getStringExtra("email");
        passp=intent.getStringExtra("pass");
        typep=intent.getStringExtra("type");


        etName.setText(namep);
        etPhone.setText(phonep);
        etEmail.setText(emailp);
        etPass.setText(passp);
        etType.setText(typep);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(Uid);
                String uName,uPhone,uEmail,uPass,uType;
                uName= etName.getText().toString();
                uPhone=etPhone.getText().toString();
                uEmail=etEmail.getText().toString();
                uPass=etPass.getText().toString();
                uType=etType.getText().toString();

                Users userData = new Users(Uid,uName,uPhone,uEmail,uPass,uType);
                databaseReference.setValue(userData);
                Toast.makeText(UpdateData.this,"Data Updated",Toast.LENGTH_SHORT).show();

            }
        });
    }


}