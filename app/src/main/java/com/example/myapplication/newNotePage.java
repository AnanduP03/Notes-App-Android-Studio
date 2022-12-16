package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class newNotePage extends AppCompatActivity {
    private EditText heading,desc;
    DBHelper DB;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_page);
            DB=new DBHelper(this);
            heading= findViewById(R.id.newPageHeading);
            desc= findViewById(R.id.newPageDesc);
            save= findViewById(R.id.save);
            save.setOnClickListener(v -> {
                String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());
                String headingText=heading.getText().toString();
                String descText=desc.getText().toString();
                Boolean checkInsertion=DB.insertNotes(headingText,descText,dateTime);
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                Bundle b= ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                if(checkInsertion){
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
                    startActivity(intent,b);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}