package com.example.abhinav.sahaya.Pills;

import com.example.abhinav.sahaya.Date;
import com.example.abhinav.sahaya.Time;

/**
 * Created by Xavier Marquès on 20/02/16.
 */
public class Pill {
    private int id;
    private String name;
    private Time time;
    private Date date_start;
    private Date date_end;
    private boolean isTaken;


    public Pill() {

    }

    public Pill(int id, String name, Time time, Date date_start, Date date_end, boolean isTaken) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date_start = date_start;
        this.date_end = date_end;
        this.isTaken = isTaken;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
    public boolean isTaken() {
        return isTaken;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }


}
