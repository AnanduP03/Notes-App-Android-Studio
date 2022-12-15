package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper DB;
    CustomAdapter adapter;
    Button btn;
    Note[] notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.add);
        btn.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),newNotePage.class);
            Bundle b=ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
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