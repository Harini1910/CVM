package com.example.cvm.Admin;

public class admin_notification {
    String booked_Username,slot_date;
    public admin_notification() {
    }

    public admin_notification(String booked_Username, String slot_date) {
        this.booked_Username = booked_Username;
        this.slot_date = slot_date;
    }

    public String getBooked_Username() {
        return booked_Username;
    }

    public void setBooked_Username(String booked_Username) {
        this.booked_Username = booked_Username;
    }

    public String getSlot_date() {
        return slot_date;
    }

    public void setSlot_date(String slot_date) {
        this.slot_date = slot_date;
    }
}
