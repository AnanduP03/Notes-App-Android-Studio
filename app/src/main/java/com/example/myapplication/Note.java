package com.example.myapplication;


public class Note {
    public long id;
    public String heading,desc,dateTime;
    public Note(long id,String heading,String desc,String dateTime){
        this.id=id;
        this.heading=heading;
        this.desc=desc;
        this.dateTime=dateTime;
    }
    public void setHeading(String value){
        this.heading=value;
    }
    public void setDesc(String value){
        this.desc=value;
    }
    public void setDateTime(String value){
        this.dateTime=value;
    }
}
