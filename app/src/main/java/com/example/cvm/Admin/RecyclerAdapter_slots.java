package com.example.cvm.Admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cvm.R;
import com.example.cvm.User.Hospital_slots;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

class RecyclerAdapter_slots extends RecyclerView.Adapter<RecyclerAdapter_slots.MyViewHolder> {

    Context context;
    ArrayList<create_slot> data;
    public  static int location;
    public String Hospital_code;

    public RecyclerAdapter_slots(Context c, ArrayList<create_slot> p,String hospital_id) {
        context = c;
        data = p;
        Hospital_code=hospital_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_slot, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.Date.setText(data.get(position).getDate());
        holder.Time_slot.setText(data.get(position).getTime_slot());
        holder.Patients.setText(data.get(position).getMax_Patient());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Date,Time_slot,Patients;
        CardView cardView;

        @SuppressLint("UseCompatLoadingForDrawables")


        public MyViewHolder(View itemView) {
            super(itemView);

            Date=itemView.findViewById(R.id.date);
            Time_slot=itemView.findViewById(R.id.time_slot);
            Patients=itemView.findViewById(R.id.patients);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            location=position;
            Intent intent=new Intent(context, Slot_Bookings.class);
            intent.putExtra("date",data.get(position).getDate());
            intent.putExtra("max_patient",data.get(position).getMax_Patient());
            intent.putExtra("time",data.get(position).getTime_slot());
            intent.putExtra("ref_key",data.get(position).getRef_key());
           intent.putExtra("hospital_id",Hospital_code);
            context.startActivity(intent);
        }
    }
}



