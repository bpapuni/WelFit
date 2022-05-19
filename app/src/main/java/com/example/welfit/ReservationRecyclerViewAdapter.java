package com.example.welfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationRecyclerViewAdapter extends RecyclerView.Adapter<ReservationRecyclerViewAdapter.ViewHolder> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservationArrayList.get(position);
        holder.className.setText(reservation.getClassName());
        holder.classDate.setText(reservation.getClassDate());
        holder.classTime.setText(reservation.getClassTime());
        holder.classId.setText(Integer.toString(reservation.getId()));
        holder.classTime.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return reservationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView className, classDate, classTime, classId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            className = itemView.findViewById(R.id.user_item1);
            classDate = itemView.findViewById(R.id.user_item2);
            classTime = itemView.findViewById(R.id.user_item3);
            classId = itemView.findViewById(R.id.user_item4);
        }
    }
}
