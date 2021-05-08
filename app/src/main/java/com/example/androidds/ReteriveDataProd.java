package com.example.androidds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReteriveDataProd extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Produit> prodData;

    private ProduitAdapter prodAdapter;

    DatabaseReference dRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reterive_data_prod);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prodData = new ArrayList<Produit>();

        dRef= FirebaseDatabase.getInstance().getReference().child("Produit");
        dRef.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot dataSnapshotl: dataSnapshot.getChildren()){
                Produit uData= dataSnapshotl.getValue(Produit.class);
                    prodData.add(uData);
            }
            prodAdapter=new ProduitAdapter(ReteriveDataProd.this,prodData);
            recyclerView.setAdapter(prodAdapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}