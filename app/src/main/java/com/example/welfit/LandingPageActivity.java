package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


public class LandingPageActivity extends AppCompatActivity {
    ImageButton qrScannerBtn, stopwatchBtn;
    Button reservationsBtn, dashboardBtn, exercisesBtn, membershipBtn, contactusBtn;
    ArrayList<User> usersArrayList;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersArrayList = new ArrayList<>();
        dbHandler = new DbHandler(LandingPageActivity.this);
        usersArrayList = dbHandler.getUserDetails();

        for (User u : usersArrayList) {
            if (u.isLoggedIn().equals("true")) {
//                Toast.makeText(this, u.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        }

        reservationsBtn = findViewById(R.id.btn_reservations);
        dashboardBtn = findViewById(R.id.btn_dashboard);
        exercisesBtn = findViewById(R.id.btn_exercises);
        membershipBtn = findViewById(R.id.btn_membership);
        contactusBtn = findViewById(R.id.btn_contactus);
        qrScannerBtn = findViewById(R.id.qr_scanner);
        stopwatchBtn = findViewById(R.id.stopwatch);

        reservationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, ReservationsActivity.class));
            }
        });

        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, DashboardActivity.class));
            }
        });

        exercisesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, ExercisesActivity.class));
            }
        });

        membershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, MembershipActivity.class));
            }
        });

        contactusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, ContactUsActivity.class));
            }
        });

        qrScannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, QRScanner.class));
            }
        });

        stopwatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, StopWatchActivity.class));
            }
        });
    }


}