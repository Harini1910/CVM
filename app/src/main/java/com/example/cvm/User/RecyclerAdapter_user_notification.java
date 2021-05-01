package com.example.cvm.User;

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
import com.example.cvm.Admin.admin_notification;
import com.example.cvm.Admin.create_slot;
import com.example.cvm.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

class RecyclerAdapter_user_notification extends RecyclerView.Adapter<com.example.cvm.User.RecyclerAdapter_user_notification.MyViewHolder> {

    Context context;
    ArrayList<user_notification> data;
    public  static int location;

    public RecyclerAdapter_user_notification(Context c, ArrayList<user_notification> p) {
        context = c;
        data = p;
    }

    @NonNull
    @Override
    public com.example.cvm.User.RecyclerAdapter_user_notification.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new com.example.cvm.User.RecyclerAdapter_user_notification.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_user_notification, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final com.example.cvm.User.RecyclerAdapter_user_notification.MyViewHolder holder, final int position) {

        String hospital_name=data.get(position).getHospital_name();
        String date=data.get(position).getDate();
        String time=data.get(position).getTime();
        String msg="Your slot has been confirmed on "+date+" at "+time+" in "+hospital_name+".You can get your next vaccination after 28 days from first vaccination";
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





