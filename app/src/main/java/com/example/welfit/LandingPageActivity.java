package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


public class LandingPageActivity extends AppCompatActivity {
    private ImageButton qrScannerBtn, stopwatchBtn;
    private Button reservationsBtn, dashboardBtn, exercisesBtn, membershipBtn, contactusBtn, viewUsersBtn, logoutBtn;
    private ArrayList<User> usersArrayList;
    private DbHandler dbHandler;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        usersArrayList = new ArrayList<>();
        dbHandler = new DbHandler(LandingPageActivity.this);
        usersArrayList = dbHandler.getUserDetails();
        user = dbHandler.getLoggedInUser();
        reservationsBtn = findViewById(R.id.btn_reservations);
        dashboardBtn = findViewById(R.id.btn_dashboard);
        exercisesBtn = findViewById(R.id.btn_exercises);
        membershipBtn = findViewById(R.id.btn_membership);
        contactusBtn = findViewById(R.id.btn_contactus);
        viewUsersBtn = findViewById(R.id.btn_view_users);
        logoutBtn = findViewById(R.id.btn_logout);
        qrScannerBtn = findViewById(R.id.qr_scanner);
        stopwatchBtn = findViewById(R.id.stopwatch);

        if (user.getId() != -1)
            viewUsersBtn.setVisibility(View.GONE);

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

        viewUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LandingPageActivity.this, ViewUsersActivity.class);
                startActivity(i);
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

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPageActivity.this, LoginActivity.class));
            }
        });
    }


}