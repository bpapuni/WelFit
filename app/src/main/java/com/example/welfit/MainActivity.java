package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    ImageButton qrScannerBtn, stopwatchBtn;
    Button reservationsBtn, dashboardBtn, exercisesBtn, membershipBtn, contactusBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(new Intent(MainActivity.this, Reservations.class));
            }
        });

        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }
        });

        exercisesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Exercises.class));
            }
        });

        membershipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Membership.class));
            }
        });

        contactusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Reservations.class));
            }
        });

        qrScannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QRScanner.class));
            }
        });
    }


}