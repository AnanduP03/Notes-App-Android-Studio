package com.example.myapplication;


public class Note {
    public long id;
    public String heading,desc;
    public Note(long id,String heading,String desc){
        this.id=id;
        this.heading=heading;
        this.desc=desc;
    }
    public void setHeading(String value){
        this.heading=value;
    }
    public void setDesc(String value){
        this.desc=value;
    }
}
