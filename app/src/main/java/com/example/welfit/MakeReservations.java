package com.example.welfit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class MakeReservations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
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
                Toast.makeText(this, "Boxing reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_yoga:
                // Make yoga reservation
                className = "Yoga";
                Toast.makeText(this, "Yoga reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_pilates:
                // Make hiit reservation
                className = "Hiit";
                Toast.makeText(this, "Pilates reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sandc:
                // Make strength & conditioning reservation
                className = "S&C";
                Toast.makeText(this, "Strength & conditioning reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_circuit_training:
                // Make yoga reservation
                className = "Circuit Training";
                Toast.makeText(this, "Circuit Training reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_hiit:
                // Make hiit reservation
                className = "Hiit";
                Toast.makeText(this, "H.I.I.T. reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_crossfit:
                // Make strength & conditioning reservation
                className = "Crossfit";
                Toast.makeText(this, "Crossfit reservation made", Toast.LENGTH_SHORT).show();
                break;

            default:
                className = "NULL";
                break;
        }

//        reservation = new Reservation(email, firstName, lastName, className, time);
    }
}