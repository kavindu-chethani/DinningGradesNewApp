package com.example.dinninggradesnewapp.models;

public class tableReserveModel {

    String tableno,floor, noofguest,date,time;


    tableReserveModel(){ }

    public tableReserveModel(String tableno, String floor, String noofguest, String date, String time) {
        this.tableno = tableno;
        this.floor = floor;
        this.noofguest = noofguest;
        this.date = date;
        this.time = time;
    }

    public String getTableno() {
        return tableno;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNoofguest() {
        return noofguest;
    }

    public void setNoofguest(String noofguest) {
        this.noofguest = noofguest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
