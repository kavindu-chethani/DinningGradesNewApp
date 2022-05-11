package com.example.dinninggradesnewapp;

public class tableReserveModel {
    String table_no,table_floor,no_of_guest,date, time;

    tableReserveModel(){

    }

    public tableReserveModel(String table_no, String table_floor, String noofguest, String date, String time) {
        this.table_no = table_no;
        this.table_floor = table_floor;
        this.no_of_guest = noofguest;
        this.date = date;
        this.time = time;
    }

    public String getTableno() {
        return table_no;
    }

    public void setTableno(String tableno) {
        this.table_no = tableno;
    }

    public String getFloor() {
        return table_floor;
    }

    public void setFloor(String floor) {
        this.table_floor = floor;
    }

    public String getNoofguest() {
        return no_of_guest;
    }

    public void setNoofguest(String noofguest) {
        this.no_of_guest = noofguest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public  String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
