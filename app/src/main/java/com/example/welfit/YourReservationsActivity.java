package com.example.welfit;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class YourReservationsActivity extends AppCompatActivity {
    private ArrayList<Reservation> reservationsArrayList;
    private DbHandler dbHandler;
    private User user;
    private ReservationRecyclerViewAdapter reservationRVAdapter;
    private RecyclerView reservationRV;
    private BottomAppBar bottomBar;
    private TextView id;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_reservations);

        reservationsArrayList = new ArrayList<>();
        dbHandler = new DbHandler(YourReservationsActivity.this);
        user = dbHandler.getLoggedInUser();

        reservationsArrayList = dbHandler.getReservationDetails(user);
        reservationRVAdapter = new ReservationRecyclerViewAdapter(reservationsArrayList, YourReservationsActivity.this);
        reservationRV = findViewById(R.id.reservations_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(YourReservationsActivity.this, RecyclerView.VERTICAL, false);
        reservationRV.setLayoutManager(linearLayoutManager);
        reservationRV.setAdapter(reservationRVAdapter);

        bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setOnMenuItemClickListener(item -> {
            switch(item.getItemId()) {
                case(R.id.action_delete) :
                    DeleteReservations();
                    break;
                default :
                    break;
            }
            return false;
        });
    }

    private void DeleteReservations() {
        int count = reservationRV.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            RecyclerView.ViewHolder holder = reservationRV.findViewHolderForAdapterPosition(i);
            checkBox = holder.itemView.findViewById(R.id.item_checkbox);
            id = holder.itemView.findViewById(R.id.user_item4);

            if (checkBox.isChecked()) {
                dbHandler.deleteReservation(id.getText().toString());
                reservationsArrayList.remove(i);
                reservationRVAdapter.notifyItemRemoved(i);
                findViewById(R.id.bottom_bar).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.your_reservations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        for (int i = 0; i < reservationRV.getChildCount(); i++) {
            RecyclerView.ViewHolder holder = reservationRV.findViewHolderForAdapterPosition(i);
            checkBox = holder.itemView.findViewById(R.id.item_checkbox);

            if (checkBox.getVisibility() == View.INVISIBLE) {
                checkBox.setVisibility(View.VISIBLE);
                findViewById(R.id.bottom_bar).setVisibility(View.VISIBLE);
            }
            else {
                checkBox.setVisibility(View.INVISIBLE);
                findViewById(R.id.bottom_bar).setVisibility(View.INVISIBLE);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}