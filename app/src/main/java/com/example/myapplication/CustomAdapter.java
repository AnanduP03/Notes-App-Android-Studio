package com.example.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemViewHolder> {
    private Note[] notes;
    private Context context;

    public CustomAdapter(Note[] notes, Context context) {
        this.notes=notes;
        this.context = context;
    }

    public static String limitingLength(String text){
        if(text.length()<=40){
            return text;
        }
        else{
            return (text.substring(0,40)+"...");
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position){
        Note note = notes[position];
        holder.Heading.setText(String.valueOf(note.heading));
        String descShrink=limitingLength(String.valueOf(notes[position].desc));
        holder.Desc.setText(descShrink);
        holder.dateTime.setText(String.valueOf(notes[position].dateTime));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=holder.getAdapterPosition();
                notes=new DBHelper(context).getNotes();
                Bundle bundle=new Bundle();
                String headingTxt=notes[i].heading;
                String descTxt=notes[i].desc;
                String dateTime=notes[i].dateTime;
                int id= (int) notes[i].id;
                bundle.putInt("Id",id);
                bundle.putString("Heading",headingTxt);
                bundle.putString("Content",descTxt);
                bundle.putString("dateTime",dateTime);
                Intent intent=new Intent(context,viewNote.class);
                intent.putExtras(bundle);
                Bundle b= ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
                context.startActivity(intent,b);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int i=holder.getAdapterPosition();
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.warning);
                builder.setMessage("Are you sure that you want to delete "+note.heading);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.v("CustomAdapter Note","ID: "+note.id);

                        new DBHelper(context).deleteNotes(note.id);

                        Toast.makeText(context.getApplicationContext(), "Deleted Successfully",Toast.LENGTH_SHORT).show();
                        Intent refresh=new Intent(context,MainActivity.class);
                        Bundle b=ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
                        context.startActivity(refresh,b);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            return true;
            }
        });
    }


    @Override
    public int getItemCount(){
        return notes.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView Heading,Desc,dateTime;
        private DBHelper DB;
        public ItemViewHolder(@NonNull View v){
            super(v);
            Heading=v.findViewById(R.id.heading);
            Desc=v.findViewById(R.id.desc);
            dateTime=v.findViewById(R.id.dateTime);
        }
    }
}
