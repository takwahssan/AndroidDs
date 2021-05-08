package com.example.androidds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProd extends AppCompatActivity {
    EditText nomfo,labeli,prixi,qtei;
    Button btnUpdate;
    ImageView imagepro;
    String nomf,labell,prixx,qtee,imagee,pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prod);
        nomfo = findViewById(R.id.nomfourni);
        labeli = findViewById(R.id.label);
        prixi = findViewById(R.id.prix);
        qtei= findViewById(R.id.qte);
        imagepro= findViewById(R.id.imageprod);

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        pid=intent.getStringExtra("id");
        imagee=intent.getStringExtra("imageprod");
        nomf=intent.getStringExtra("nomfourni");
        labell=intent.getStringExtra("label");
        prixx=intent.getStringExtra("prix");
        qtee=intent.getStringExtra("qte");



        imagepro.setImageURI(Uri.parse(imagee));
        nomfo.setText(nomf);
        labeli.setText(labell);
        prixi.setText(prixx);
        qtei.setText(qtee);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Produit").child(pid);
                String nomf,labell,prixx,qtee,image;
                image= imagepro.toString();
                nomf=nomfo.getText().toString();
                labell=labeli.getText().toString();
                prixx=prixi.getText().toString();
                qtee=qtei.getText().toString();

                Produit userData = new Produit(pid, image,nomf, labell,prixx,qtee);
                databaseReference.setValue(userData);
                Toast.makeText(UpdateProd.this,"Data Updated",Toast.LENGTH_SHORT).show();

            }
        });
    }


}