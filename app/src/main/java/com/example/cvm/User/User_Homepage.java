package com.example.cvm.User;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cvm.Admin.AdminNotification;
import com.example.cvm.Admin.Admin_Homepage;
import com.example.cvm.Admin.Hospital;
import com.example.cvm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class User_Homepage extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<Hospital> list;
    public RecyclerAdapter_hospitals adapter;
    public FirebaseAuth auth;
    FirebaseUser user;
    private FirebaseDatabase database;
    public DatabaseReference ref2;
    public ImageView Notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__homepage);

        recyclerView = findViewById(R.id.recyclerview_hospitals);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<Hospital>();
        Notification=findViewById(R.id.user_notification);

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(User_Homepage.this, UserNotification.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();
        user =FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        assert user != null;

        ref2 = FirebaseDatabase.getInstance().getReference("Hospitals_List");
        ref2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3)
            {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot3.getChildren()) {

                    Hospital p = dataSnapshot1.getValue(Hospital.class);
                    list.add(p);
                }
                adapter = new RecyclerAdapter_hospitals(User_Homepage.this, list);
                recyclerView.setAdapter(adapter);
            }


            // recyclerView.setBackgroundColor(randomAndroidColor);


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}