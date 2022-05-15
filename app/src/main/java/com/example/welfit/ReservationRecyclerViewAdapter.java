package com.example.welfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationRecyclerViewAdapter extends RecyclerView.Adapter<ReservationRecyclerViewAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Reservation> reservationArrayList;
    private Context context;

    // constructor
    public ReservationRecyclerViewAdapter(ArrayList<Reservation> reservationArrayList, Context context) {
        this.reservationArrayList = reservationArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Reservation reservation = reservationArrayList.get(position);
        holder.userEmail.setText(reservation.getClassName());
        holder.userPw.setText(reservation.getClassTime().toString());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return reservationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView userEmail, userPw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            userEmail = itemView.findViewById(R.id.user_email);
            userPw = itemView.findViewById(R.id.user_pw);
        }
    }
}
