package com.example.welfit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ReservationsActivity extends AppCompatActivity {
    RelativeLayout reservationView;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        reservationView = findViewById( R.id.reservation_view);
        reservationView.getForeground().setAlpha(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void MakeReservation(View v) {
        // In a fully function app email, firstName and lastName would be retrieved from a User object
        // time would take input from the user to pick a date and time for the reservation
        String email = "admin@gmail.com";
        String firstName = "John";
        String lastName = "Doe";
        String className;
        LocalDateTime time = LocalDateTime.of(2022, Month.JUNE, 25, 17, 0);
        Reservation reservation;

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

        displayFragment(new ReservationFragment(), className);
    }

    @SuppressLint("NewApi")
    private void displayFragment(Fragment fragment, String className) {
        Bundle bundle = new Bundle();
        bundle.putString("className", className);
        fragment.setArguments(bundle);

        reservationView = findViewById(R.id.reservation_view);
        reservationView.getForeground().setAlpha(230);

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
}