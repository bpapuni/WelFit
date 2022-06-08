package com.example.welfit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ToggleButton;

public class StopWatchActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    Button start,stop;
    long init, now, time, paused;
    ToggleButton passTog;
    TextView display;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        handler = new Handler();
        display = (TextView) findViewById(R.id.time_view);
        passTog = findViewById(R.id.start_button);

        final Runnable updater = new Runnable() {
            @Override
            public void run() {
                if (passTog.isChecked()) {
                    now = System.currentTimeMillis();
                    time = now - init;
                    DateFormat format = new SimpleDateFormat("mm:ss.SS");
                    String displayTime = format.format(time);
                    display.setText(displayTime);
                    handler.postDelayed(this, 10);
                }
            }
        };
        passTog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (passTog.isChecked()) {
                    if (paused > 0)
                        init += System.currentTimeMillis() - paused;
                    else
                        init = System.currentTimeMillis();
                }
                else
                    paused = System.currentTimeMillis();

                handler.post(updater);
            }
        });
    }

    public void onClickReset(View v) {
        paused = 0;
        display.setText("00:00.00");
        passTog.setChecked(false);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}