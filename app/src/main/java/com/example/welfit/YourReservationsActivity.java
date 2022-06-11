package com.example.welfit;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class YourReservationsActivity extends AppCompatActivity {
    private ArrayList<Reservation> reservationsArrayList;
    private DbHandler dbHandler;
    private User user;
    private ReservationRecyclerViewAdapter reservationRVAdapter;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Reservation reservation = reservationsArrayList.get(position);
            displayFragment(new ReservationsUpdateFragment(), reservation);
        }
    };

    private CoordinatorLayout reservationView;
    private RecyclerView reservationRV;
    private BottomAppBar bottomBar;
    private CheckBox checkBox;
    private MenuItem editBtn;

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
        reservationRVAdapter.setOnItemClickListener(onItemClickListener);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_delete) {
                DeleteReservations();
            }
            return false;
        });
    }

    private void DeleteReservations() {
        int count = reservationRV.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            RecyclerView.ViewHolder holder = reservationRV.findViewHolderForAdapterPosition(i);
            checkBox = holder.itemView.findViewById(R.id.item_checkbox);
            Reservation reservation = reservationsArrayList.get(i);

            if (checkBox.isChecked()) {
                dbHandler.deleteReservation(reservation.getId());
                reservationsArrayList.remove(i);
                reservationRVAdapter.notifyItemRemoved(i);
            }
            checkBox.setVisibility(View.INVISIBLE);
            findViewById(R.id.bottom_bar).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.your_reservations, menu);
        editBtn = menu.findItem(R.id.action_edit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } else {
            for (int i = 0; i < reservationRV.getChildCount(); i++) {
                RecyclerView.ViewHolder holder = reservationRV.findViewHolderForAdapterPosition(i);
                checkBox = holder.itemView.findViewById(R.id.item_checkbox);

                if (checkBox.getVisibility() == View.INVISIBLE) {
                    checkBox.setVisibility(View.VISIBLE);
                    findViewById(R.id.bottom_bar).setVisibility(View.VISIBLE);
                } else {
                    checkBox.setVisibility(View.INVISIBLE);
                    findViewById(R.id.bottom_bar).setVisibility(View.INVISIBLE);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NewApi")
    private void displayFragment(Fragment fragment, Reservation reservation) {
        Bundle bundle = new Bundle();
        bundle.putString("className", reservation.getClassName());
        bundle.putString("classDate", reservation.getClassDate());
        bundle.putString("classTime", reservation.getClassTime());
        bundle.putString("action", "Update");
        bundle.putInt("id", reservation.getId());
        fragment.setArguments(bundle);

        reservationView = findViewById(R.id.your_reservations);
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#DD000000"));
        reservationView.setForeground(cd);


        FragmentManager fM = getSupportFragmentManager();
        fM.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit)
                .replace(R.id.frame_layout, fragment)
                .addToBackStack("")
                .commit();
    }

    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            reservationView.getForeground().setAlpha(0);
            getSupportFragmentManager().popBackStack();
            reservationsArrayList.clear();
            ArrayList<Reservation> newReservationsArrayList = dbHandler.getReservationDetails(user);
            reservationsArrayList.addAll(newReservationsArrayList);
            reservationRVAdapter.notifyDataSetChanged();
        }
    }
}