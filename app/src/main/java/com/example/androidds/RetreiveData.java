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

import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class RetreiveData extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Users> userData;

    private UserAdapter userAdapter;

    DatabaseReference dRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userData = new ArrayList<Users>();

        dRef= FirebaseDatabase.getInstance().getReference().child("Users");
        dRef.addListenerForSingleValueEvent(valueEventListener);


    }
    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot dataSnapshotl: dataSnapshot.getChildren()){
                Users uData= dataSnapshotl.getValue(Users.class);
                Users uData1= dataSnapshotl.getValue(Users.class);
                if(uData.getType().equals("fournisseur")){
                    userData.add(uData);
                }

            }
            userAdapter=new UserAdapter(RetreiveData.this, userData);
            recyclerView.setAdapter(userAdapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}
