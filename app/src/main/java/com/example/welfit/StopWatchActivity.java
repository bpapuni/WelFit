package com.example.welfit;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StopWatchActivity extends AppCompatActivity {
    private long init, now, time, paused;
    private ToggleButton passTog;
    private TextView display;
    private Handler handler;

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