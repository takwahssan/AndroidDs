package com.example.androidds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class GestionProduit extends AppCompatActivity {
    EditText edtName, label, prix,qte,nomfourni;
    Button buttonAdd, btnRetreive;
    DatabaseReference databaseCars;
    //image

    private ImageView imageView;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_produit);

        imageView = findViewById(R.id.imageViewC);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });
        databaseCars = FirebaseDatabase.getInstance().getReference();
        edtName= (EditText) findViewById(R.id.edtName);
        prix = findViewById(R.id.prix);
        label = findViewById(R.id.label);
        qte = (EditText) findViewById(R.id.qte);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        btnRetreive = findViewById(R.id.btnRetreiveData);
        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GestionProduit.this, ReteriveDataProd.class);
                startActivity(i);
                finish();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    UploadToFireBase(imageUri);
                }else{
                    Toast.makeText(GestionProduit.this, "Please select an image", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
    }
    private void  UploadToFireBase(Uri uri){
        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @SuppressLint("StringFormatMatches")
                    @Override
                    public void onSuccess(Uri uri) {
                        //calcul total
                        /*String v1 = prix.getText().toString();
                        String v2 = qte.getText().toString();
                        float num1 = Float.parseFloat(v1);
                        float num2 = Float.valueOf(v2);
                        float tot = (num1 * num2);
                        total.setText(getString(R.string.stot, tot, " DT"));*/
                        String id= databaseCars.push().getKey();
                        Produit prod = new Produit(id,uri.toString(), nomfourni.getText().toString(),  label.getText().toString(), prix.getText().toString(),qte.getText().toString());
                        String prodId=root.push().getKey();
                        root.child(prodId).setValue(prod);
                        databaseCars.child("Produit").child(id).setValue(prod);
                        Toast.makeText(GestionProduit.this, "Produit added", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GestionProduit.this, "Produit not added", Toast.LENGTH_LONG).show();
            }
        });
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}