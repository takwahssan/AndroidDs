package com.example.androidds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.text.BreakIterator;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Users> userData;
    public  UserAdapter(Context context,ArrayList<Users> userData){
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fournisseurs_list,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final   Users userData = this.userData.get(position);
        //FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        holder.fournName.setText(userData.getName());
        holder.fournPhone.setText(userData.getPhoneNumber());
        holder.fournEmail.setText(userData.getEmail());
        holder.fournType.setText(userData.getType());





        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userData.getUid());
                databaseReference.removeValue();
                Toast.makeText(context,"Data Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),UpdateData.class);
                i.putExtra("id",userData.getUid());
                i.putExtra("name",userData.getName());
                i.putExtra("phone",userData.getPhoneNumber());
                i.putExtra("email",userData.getEmail());
                i.putExtra("pass",userData.getPassword());
                i.putExtra("type",userData.getType());

                v.getContext().startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return userData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fournName, fournPhone,fournEmail,fournPassword, fournType;
        Button btnDelete,btnUpdate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fournName = itemView.findViewById(R.id.fournName);
            fournPhone = itemView.findViewById(R.id.fournPhone);
            fournEmail = itemView.findViewById(R.id.fournEmail);
            fournPassword=itemView.findViewById(R.id.fournPassword);
            fournType=itemView.findViewById(R.id.fournType);
            btnDelete= itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
