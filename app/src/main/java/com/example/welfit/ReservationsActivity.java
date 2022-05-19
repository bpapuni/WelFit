package com.example.welfit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.time.LocalDateTime;
import java.time.Month;

public class ReservationsActivity extends AppCompatActivity {
    private RelativeLayout reservationView;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        reservationView = findViewById( R.id.reservation_view);
    }

    public void ViewReservations(View v) {
        startActivity(new Intent(this, YourReservationsActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void MakeReservation(View v) {
        String className;

        switch (v.getId()) {
            case R.id.btn_boxing:
                // Make boxing reservation
                className = "Boxing";
                break;
            case R.id.btn_yoga:
                // Make yoga reservation
                className = "Yoga";
                break;
            case R.id.btn_pilates:
                // Make pilates reservation
                className = "Pilates";
                break;
            case R.id.btn_sandc:
                // Make strength & conditioning reservation
                className = "Strength & Conditioning";
                break;
            case R.id.btn_circuit_training:
                // Make yoga reservation
                className = "Circuit Training";
                break;
            case R.id.btn_hiit:
                // Make hiit reservation
                className = "H.I.I.T";
                break;
            case R.id.btn_crossfit:
                // Make strength & conditioning reservation
                className = "Crossfit";
                break;

            default:
                className = "NULL";
                break;
        }

        displayFragment(new ReservationsFragment(), className);
    }

    @SuppressLint("NewApi")
    private void displayFragment(Fragment fragment, String className) {
        Bundle bundle = new Bundle();
        bundle.putString("className", className);
        bundle.putString("action", "Make");
        fragment.setArguments(bundle);

        reservationView = findViewById(R.id.reservation_view);
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
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}