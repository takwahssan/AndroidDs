package com.example.androidds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class AcceuilAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Button btnfourni, btnemp;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Button boomMenuButton;
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_admin);

        toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        //  btnfourni=(Button) findViewById(R.id.btnfourni);
        //btnemp=(Button) findViewById(R.id.btnemp);

        // btnfourni.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        //   Intent in = new Intent(AccueilAdmin.this,GestionFournisseur.class);
        //    startActivity(in);

        //}
        // });
        // nav_fourni.setOnClickListener(new View.OnClickListener() {
        // @Override
        //  public void onClick(View view) {
        //  Intent in = new Intent(AccueilAdmin.this,GestionEmployer.class);
        // startActivity(in);
//
        // }
        // });


        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_Layout);
        navigationView=findViewById(R.id.nav_view);
        //   toolbar=findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(AcceuilAdmin.this,drawerLayout,toolbar1,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        // navigationView.setCheckedItem(R.id.nav_fourni);


    }
 /* public void navfourni(View view){
        Intent in = new Intent(AccueilAdmin.this,GestionFournisseur.class);

       startActivity(in);
    }*/


    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AcceuilAdmin.this, LoginActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_fourni:
                Intent inf = new Intent(AcceuilAdmin.this, GestionFournisseur.class);
                startActivity(inf);
                break;
            case R.id.nav_prod:
                Intent i = new Intent(AcceuilAdmin.this, GestionProduit.class);
                startActivity(i);
                break;
            case R.id.nav_logout:
                Logout();
                break;


        }
        return true;
    }
}