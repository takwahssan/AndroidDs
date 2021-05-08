package com.example.androidds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Produit> prodData;
    public ProduitAdapter(Context c, ArrayList<Produit> carData){
        this.context = c;
        this.prodData = prodData;
    }


    @NonNull
    @Override
    public ProduitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prod_list,parent,false);
        return new ProduitAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitAdapter.MyViewHolder holder, int position) {
        Produit prodData = this.prodData.get(position);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        Glide.with(context).load(prodData.getImageUrl()).into(holder.imageprod);
        holder.nomfourni.setText(prodData.getNomfourni());
        holder.label.setText(prodData.getLabel());
        holder.prix.setText(prodData.getPrix());
        holder.qte.setText(prodData.getPrix());

        holder.btnDeleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Produit").child(prodData.getPid());
                databaseReference.removeValue();
                Toast.makeText(context,"Data Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(v.getContext(),UpdateProd.class);
                ic.putExtra("id",prodData.getPid());
                ic.putExtra("imageprod",prodData.getQte());
                ic.putExtra("nomfourni",prodData.getNomfourni());

                ic.putExtra("label",prodData.getLabel());
                ic.putExtra("prix",prodData.getPrix());

                ic.putExtra("qte",prodData.getQte());


                v.getContext().startActivity(ic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prodData.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomfourni, label,prix,qte;
        Button btnDeleteCar,btnUpdateCar;
        CardView cardView;
        ImageView imageprod;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomfourni = itemView.findViewById(R.id.nomfourni);
            label = itemView.findViewById(R.id.label);
            prix = itemView.findViewById(R.id.prix);
            qte=itemView.findViewById(R.id.qte);
            imageprod=itemView.findViewById(R.id.imageprod);

            btnDeleteCar = itemView.findViewById(R.id.btnDelete);
            btnUpdateCar = itemView.findViewById(R.id.btnUpdate);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }
}
