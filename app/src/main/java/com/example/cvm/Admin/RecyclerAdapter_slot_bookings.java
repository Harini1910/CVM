package com.example.cvm.Admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cvm.Admin.Admin_Homepage;
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

class RecyclerAdapter_slot_bookings extends RecyclerView.Adapter<RecyclerAdapter_slot_bookings.MyViewHolder> {

    public  static int location;
    Context context;
    ArrayList<bookings> data;

    public FirebaseUser user;
    public  String hospital_code;
    public static  String hospital_name,u_id;
    public String ref_key,slot_date,slot_time;
    private DatabaseReference ref;
    private FirebaseDatabase database;
    private FirebaseDatabase database1;
    private DatabaseReference ref1;


    public RecyclerAdapter_slot_bookings(Context c, ArrayList<bookings> p,String hospital_id,String date,String time) {
        context = c;
        data = p;
        hospital_code=hospital_id;
        slot_date=date;
        slot_time=time;
    }


    @NonNull
    @Override
    public RecyclerAdapter_slot_bookings.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerAdapter_slot_bookings.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.slots_booked, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter_slot_bookings.MyViewHolder holder, final int position) {

        holder.patient.setText(data.get(position).getUsername());
        holder.ages.setText(data.get(position).getAge());
        holder.genders.setText(data.get(position).getGender());
        holder.addresss.setText(data.get(position).getAddress());
        holder.emails.setText(data.get(position).getEmail());
        holder.mobile_numbers.setText(data.get(position).getMobile_Number());




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView patient,genders,ages,addresss,emails,mobile_numbers;
        Button accept;


        @SuppressLint("UseCompatLoadingForDrawables")
        public MyViewHolder(View itemView) {
            super(itemView);
            patient=itemView.findViewById(R.id.patient);
            genders=itemView.findViewById(R.id.gender);
            ages=itemView.findViewById(R.id.patient_age);
            addresss=itemView.findViewById(R.id.user_address);
            emails=itemView.findViewById(R.id.user_email);
            mobile_numbers=itemView.findViewById(R.id.mobile);
            accept=itemView.findViewById(R.id.accept);

            database = FirebaseDatabase.getInstance();
            database1 = FirebaseDatabase.getInstance();


            user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference userref= FirebaseDatabase.getInstance().getReference();
            DatabaseReference myref= userref.child("Hospitals").child(hospital_code);
            myref.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    hospital_name= (Objects.requireNonNull(dataSnapshot.child("hospital_address").getValue()).toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    location=position;
                    u_id=data.get(position).getUser_id();
                    ref = database.getReference("User_Notification").child(u_id).push();
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String user_refKey=ref.getKey();

                            ref.child("hospital_name").setValue(hospital_name);
                            ref.child("Hospital_code").setValue(hospital_code);
                            ref.child("date").setValue(slot_date);
                            ref.child("time").setValue(slot_time);
                            ref.child("Ref_Key").setValue(user_refKey);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });
            //ends


        }
    }
}






