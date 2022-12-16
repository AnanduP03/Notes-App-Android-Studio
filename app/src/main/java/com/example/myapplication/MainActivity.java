package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView nav_view;
    RecyclerView recyclerView;
    DBHelper DB;
    CustomAdapter adapter;
    Button btn;
    Note[] notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        nav_view=findViewById(R.id.nav_view);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(this);
        btn= findViewById(R.id.add);
        Bundle b=ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        btn.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),newNotePage.class);
            startActivity(intent,b);
        });

        DB=new DBHelper(this);

        recyclerView=findViewById(R.id.recyclerView);
        notes=DB.getNotes();
        adapter=new CustomAdapter(notes,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(notes.length==0){
            Toast.makeText(this,"No Entry Exists",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.addNote:
                startActivity(new Intent(this,newNotePage.class));
                break;
            case R.id.notes:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.rating:
                startActivity(new Intent(this,RatingPage.class));
                break;
            case R.id.share:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AnanduP03/Notes-App-Android-Studio")));
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        notes=DB.getNotes();
        adapter=new CustomAdapter(notes,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}