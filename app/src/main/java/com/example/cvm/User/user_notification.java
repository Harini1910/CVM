package com.example.cvm.User;

public class user_notification {
    String date,time,hospital_name;
    public user_notification() {
    }

    public user_notification(String date, String time, String hospital_name) {
        this.date = date;
        this.time = time;
        this.hospital_name = hospital_name;
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

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
