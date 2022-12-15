package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table NotesTable(id integer primary key autoincrement,heading TEXT,content TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int j) {
        DB.execSQL("DROP Table if exists NotesTable");
    }

    public Boolean insertNotes(String heading,String desc){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("heading",heading);
        contentValues.put("content",desc);
        long result=DB.insert("NotesTable",null,contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteNotes(long i){
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.delete("NotesTable","id=?",new String[]{String.valueOf(i)});
    }

    public void updateNotes(int i,String heading,String desc){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("id",i);
        cv.put("heading",heading);
        cv.put("content",desc);
        DB.update("NotesTable",cv,"id=?",new String[]{String.valueOf(i)});
    }

    public Note[] getNotes(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from NotesTable",null);
        Note[] note=new Note[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext()){
            note[i]=new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2));
            Log.v("DBHelper Note","ID: "+note[i].id);
            i++;
        }
        return note;

    }
}
