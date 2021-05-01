package com.example.cvm.User;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;


import com.example.cvm.Admin.Hospital;
import com.example.cvm.Admin.create_slot;
import com.example.cvm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Hospital_slots extends AppCompatActivity {

    public RecyclerView recyclerView;
    public DatabaseReference ref2;
    public ArrayList<create_slot> list;
    public RecyclerAdapter_Hospital_Slots adapter;
    public TextView hospital_name,hospital_address,hospital_number,Hospital_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_slots);

        String Hospital_name = getIntent().getStringExtra("hospital_name");
        String Hospital_address = getIntent().getStringExtra("hospital_address");
        String Hospital_number=getIntent().getStringExtra("hospital_number");
        String hospital_id=getIntent().getStringExtra("id");

        hospital_name=findViewById(R.id.HospitalName);
        hospital_address=findViewById(R.id.Hospital_Address);
        hospital_number=findViewById(R.id.Hospital_Number);
        Hospital_id=findViewById(R.id.Hospital_ID);

        hospital_name.setText(Hospital_name);
        hospital_address.setText(Hospital_address);
        hospital_number.setText(Hospital_number);
        Hospital_id.setText(hospital_id);

        recyclerView = findViewById(R.id.recyclerview_hospitals_slots);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       // linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<create_slot>();


        ref2 = FirebaseDatabase.getInstance().getReference("Hospitals")
                .child(hospital_id).child("Slots");
        ref2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3)
            {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot3.getChildren()) {

                    create_slot p = dataSnapshot1.getValue(create_slot.class);
                    list.add(p);
                }
                adapter = new RecyclerAdapter_Hospital_Slots(Hospital_slots.this, list,hospital_id);
                recyclerView.setAdapter(adapter);


            }


            // recyclerView.setBackgroundColor(randomAndroidColor);
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}