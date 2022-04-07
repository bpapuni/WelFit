package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.LocalDateTime;

public class MakeReservations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
    }

    public void MakeReservation(View v) {
        //In a fully working app firstName and lastName would be retrieved from a User object
        // and the app would take input from the user to pick a date and time for the reservation
        String firstName = "John";
        String lastName = "Doe";
        String className;

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
            case R.id.btn_hiit:
                // Make hiit reservation
                className = "Hiit";
                Toast.makeText(this, "H.I.I.T. reservation made", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sandc:
                // Make strength & conditioning reservation
                className = "S&C";
                Toast.makeText(this, "Strength & conditioning reservation made", Toast.LENGTH_SHORT).show();
                break;
            default:
                className = "NULL";
                break;
        }

        Reservation reservation = new Reservation(firstName, lastName, className);

    }
}