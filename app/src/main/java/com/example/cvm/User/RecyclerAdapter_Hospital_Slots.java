package com.example.cvm.User;

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

class RecyclerAdapter_Hospital_Slots extends RecyclerView.Adapter<RecyclerAdapter_Hospital_Slots.MyViewHolder> {

    public  static int location;
    Context context;
    ArrayList<create_slot> data;

    public FirebaseUser user;
    public static  String user_name,gender,age,address,email,mobile_number,user_id;
    public String ref_key,slot_date;
    public  String hospital_code;
    private DatabaseReference ref;
    private FirebaseDatabase database;
    private FirebaseDatabase database1;
    private DatabaseReference ref1;

    public RecyclerAdapter_Hospital_Slots(Context c, ArrayList<create_slot> p,String hospital_id) {
        context = c;
        data = p;
        hospital_code=hospital_id;
    }


    @NonNull
    @Override
    public RecyclerAdapter_Hospital_Slots.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerAdapter_Hospital_Slots.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.vaccination_booking_cardview, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter_Hospital_Slots.MyViewHolder holder, final int position) {

        holder.Date.setText(data.get(position).getDate());
        holder.Time.setText(data.get(position).getTime_slot());
        holder.Maximum_patients.setText(data.get(position).getMax_Patient());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Date,Time,Maximum_patients;
        Button book_slot;

        @SuppressLint("UseCompatLoadingForDrawables")
        public MyViewHolder(View itemView) {
            super(itemView);
            Date=itemView.findViewById(R.id.Date);
            Time=itemView.findViewById(R.id.Time);
            Maximum_patients=itemView.findViewById(R.id.Maximum_patients);
            book_slot=itemView.findViewById(R.id.book);
            database = FirebaseDatabase.getInstance();
            database1 = FirebaseDatabase.getInstance();



            //get user details
            user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference userref= FirebaseDatabase.getInstance().getReference();
            DatabaseReference myref= userref.child("user_signup").child(user.getUid());
            myref.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user_name= (Objects.requireNonNull(dataSnapshot.child("Username").getValue()).toString());
                    gender= (Objects.requireNonNull(dataSnapshot.child("Gender").getValue()).toString());
                    age= (Objects.requireNonNull(dataSnapshot.child("Age").getValue()).toString());
                    address= (Objects.requireNonNull(dataSnapshot.child("Address").getValue()).toString());
                    email= (Objects.requireNonNull(dataSnapshot.child("Email").getValue()).toString());
                    mobile_number= (Objects.requireNonNull(dataSnapshot.child("Mobile_number").getValue()).toString());
                    user_id=user.getUid().toString();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //ends

            book_slot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();
                    location=position;

                    ref_key=data.get(position).getRef_key();
                    slot_date=data.get(position).getDate();

                    ref = database.getReference("Hospitals").child(hospital_code).child("Slots").child(ref_key).child("Slot_Bookings").push();
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String user_refKey=ref.getKey();

                            ref.child("Username").setValue(user_name);
                            ref.child("Gender").setValue(gender);
                            ref.child("Age").setValue(age);
                            ref.child("Address").setValue(address);
                            ref.child("Email").setValue(email);
                            ref.child("Mobile_Number").setValue(mobile_number);
                            ref.child("User_id").setValue(user_id);
                            ref.child("Ref_Key").setValue(user_refKey);

                            Toast.makeText(context,"Slot Booked successfully",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    ref1 = database1.getReference("Admin_Notifications").child(hospital_code).push();
                    ref1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String admin_refKey=ref1.getKey();

                            ref1.child("booked_Username").setValue(user_name);
                            ref1.child("User_id").setValue(user_id);
                            ref1.child("Ref_Key").setValue(admin_refKey);
                            ref1.child("slot_date").setValue(slot_date);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });

        }
    }
}






