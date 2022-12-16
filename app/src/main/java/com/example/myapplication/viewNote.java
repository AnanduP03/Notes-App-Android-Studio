package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class viewNote extends AppCompatActivity {
    TextView heading,desc,dateTime;
    ImageButton btn,sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("Id");
        String Heading=bundle.getString("Heading");
        String Desc=bundle.getString("Content");
        String date=bundle.getString("dateTime");
        heading=findViewById(R.id.viewHeading);
        desc=findViewById(R.id.viewDesc);
        dateTime=findViewById(R.id.viewDateTime);
        heading.setText(Heading);
        desc.setText(Desc);
        dateTime.setText(date);
        btn=findViewById(R.id.editButton);
        sharebtn=findViewById(R.id.shareButton);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),updatingActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Id",id);
                bundle.putString("Heading",Heading);
                bundle.putString("Desc",Desc);
                intent.putExtras(bundle);
                startActivity(intent,b);
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, ("Heading: "+Heading+"\nContent: "+Desc));
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }
}