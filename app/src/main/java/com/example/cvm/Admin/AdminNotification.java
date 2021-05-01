package com.example.cvm.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.cvm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminNotification extends AppCompatActivity {

    public RecyclerView recyclerView;
    public DatabaseReference ref2;
    public ArrayList<admin_notification> list;
    public RecyclerAdapter_admin_notification adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notification);

        String hospital_id = getIntent().getStringExtra("hospital_code");

        recyclerView = findViewById(R.id.recyclerview_admin_notification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<admin_notification>();

        ref2 = FirebaseDatabase.getInstance().getReference("Admin_Notifications")
                .child(hospital_id);
        ref2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3)
            {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot3.getChildren()) {

                    admin_notification p = dataSnapshot1.getValue(admin_notification.class);
                    list.add(p);
                }
                adapter = new RecyclerAdapter_admin_notification(AdminNotification.this, list);
                recyclerView.setAdapter(adapter);


            }


            // recyclerView.setBackgroundColor(randomAndroidColor);
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}