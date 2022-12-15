package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatingActivity extends AppCompatActivity {
    EditText heading,desc;
    Button update;
    Note[] notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating);
        heading=findViewById(R.id.updatingPageHeading);
        desc=findViewById(R.id.updatingPageDesc);
        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("Id");
        String headingTxt=bundle.getString("Heading");
        String descTxt=bundle.getString("Content");
        heading.setText(headingTxt);
        desc.setText(descTxt);
        update=findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String headingTxtUpdated=heading.getText().toString();
                String descTxtUpdated=desc.getText().toString();
                new DBHelper(getApplicationContext()).updateNotes(id,headingTxtUpdated,descTxtUpdated);
                Toast.makeText(getApplicationContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                Bundle b= ActivityOptions.makeSceneTransitionAnimation((Activity) getApplicationContext()).toBundle();
                startActivity(intent,b);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}