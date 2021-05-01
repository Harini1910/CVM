package com.example.cvm.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cvm.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Slot_Bookings extends AppCompatActivity {

    public RecyclerView recyclerView;
    public DatabaseReference ref2;
    public ArrayList<bookings> list;
    public RecyclerAdapter_slot_bookings adapter;
    public TextView slot_date,slot_time,slot_max_patients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot__bookings);

        String Date = getIntent().getStringExtra("date");
        String Max_Patient = getIntent().getStringExtra("max_patient");
        String Timing=getIntent().getStringExtra("time");
        String hospital_id=getIntent().getStringExtra("hospital_id");
        String Ref_key=getIntent().getStringExtra("ref_key");
        slot_date=findViewById(R.id.slot_date);
        slot_time=findViewById(R.id.slot_timing);
        slot_max_patients=findViewById(R.id.slot_maximum_patients);

        slot_date.setText(Date);
        slot_time.setText(Timing);
        slot_max_patients.setText(Max_Patient);

        recyclerView = findViewById(R.id.recyclerview_slots_booked);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<bookings>();

        ref2 = FirebaseDatabase.getInstance().getReference("Hospitals")
                .child(hospital_id).child("Slots").child(Ref_key).child("Slot_Bookings");
        ref2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3)
            {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot3.getChildren()) {

                    bookings p = dataSnapshot1.getValue(bookings.class);
                    list.add(p);
                }
                adapter = new RecyclerAdapter_slot_bookings(Slot_Bookings.this, list,hospital_id,Date,Timing);
                recyclerView.setAdapter(adapter);


            }


            // recyclerView.setBackgroundColor(randomAndroidColor);
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}