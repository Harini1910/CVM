package com.example.cvm.Admin;

public class bookings {
    String Address,Age,Email,Gender,Mobile_Number,Username,User_id;
    public bookings() {
    }

    public bookings(String address, String age, String email, String gender, String mobile_Number, String username,String user_id) {
        Address = address;
        Age = age;
        Email = email;
        Gender = gender;
        Mobile_Number = mobile_Number;
        Username = username;
        User_id=user_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }
}
