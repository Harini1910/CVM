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

import com.example.cvm.Admin.Hospital;
import com.example.cvm.Admin.create_slot;
import com.example.cvm.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

class RecyclerAdapter_admin_notification extends RecyclerView.Adapter<RecyclerAdapter_admin_notification.MyViewHolder> {

    Context context;
    ArrayList<admin_notification> data;
    public  static int location;

    public RecyclerAdapter_admin_notification(Context c, ArrayList<admin_notification> p) {
        context = c;
        data = p;
    }

    @NonNull
    @Override
    public RecyclerAdapter_admin_notification.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerAdapter_admin_notification.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_admin_notification, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter_admin_notification.MyViewHolder holder, final int position) {

        String user=data.get(position).getBooked_Username();
        String date=data.get(position).getSlot_date();
        String msg=user.toLowerCase()+" Booked a slot on "+date;
        holder.event.setText(msg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView event;

        @SuppressLint("UseCompatLoadingForDrawables")


        public MyViewHolder(View itemView) {
            super(itemView);
            event=itemView.findViewById(R.id.event);
        }
    }
}




