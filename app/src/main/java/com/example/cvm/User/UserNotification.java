package com.example.cvm.User;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import com.example.cvm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserNotification extends AppCompatActivity {

    public RecyclerView recyclerView;
    public DatabaseReference ref2;
    public ArrayList<user_notification> list;
    public RecyclerAdapter_user_notification adapter;
    public FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notification);

        user = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = findViewById(R.id.recyclerview_user_notification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<user_notification>();

        ref2 = FirebaseDatabase.getInstance().getReference("User_Notification")
                .child(user.getUid());
        ref2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3)
            {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot3.getChildren()) {
                    user_notification p = dataSnapshot1.getValue(user_notification.class);
                    list.add(p);
                }
                adapter = new RecyclerAdapter_user_notification(UserNotification.this, list);
                recyclerView.setAdapter(adapter);
            }


            // recyclerView.setBackgroundColor(randomAndroidColor);
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}