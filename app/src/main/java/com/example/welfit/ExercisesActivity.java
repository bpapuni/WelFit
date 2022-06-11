package com.example.welfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.HashMap;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {
    private GridLayout gl;
    private String[] exercises = new String[]{ "Bench Press", "Shoulder Press", "Lateral Raises", "Lat Pulldowns", "Back Squat", "Deadlift", "Tricep Dips", "Bicep Curls"};
    private String[] filters;
    private TextView[] filterBtns;
    private HashMap<String, String> videoIds = new HashMap<String, String>();
    private HashMap<String, String> buttonIds = new HashMap<String, String>();
    private YouTubePlayerView youTubePlayerView;
    private FullScreenHelper fullScreenHelper = new FullScreenHelper(this);
    private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        gl = findViewById(R.id.exercises_grid_layout);

        filterBtns = new TextView[] { findViewById(R.id.chest), findViewById(R.id.shoulders), findViewById(R.id.back), findViewById(R.id.legs), findViewById(R.id.arms) };

        videoIds.put("Bench Press", "esQi683XR44");
        videoIds.put("Shoulder Press", "CnBmiBqp-AI");
        videoIds.put("Lateral Raises", "6m7JO28RqZg");
        videoIds.put("Lat Pulldowns", "apzFTbsm7HU");
        videoIds.put("Back Squat", "dW5-C1fsMjk");
        videoIds.put("Deadlift", "fc4_hq7tjkU");
        videoIds.put("Tricep Dips", "wjUmnZH528Y");
        videoIds.put("Bicep Curls", "ykJmrZ5v0Oo");

        buttonIds.put("Bench Press", "R.id.benchBtn");
        buttonIds.put("Shoulder Press", "R.id.shoulder_pressBtn");
        buttonIds.put("Lateral Raises", "R.id.lat_raisesBtn");
        buttonIds.put("Lat Pulldowns", "R.id.pulldownsBtn");
        buttonIds.put("Back Squat", "R.id.squatBtn");
        buttonIds.put("Deadlift", "R.id.deadliftBtn");
        buttonIds.put("Tricep Dips", "R.id.dipsBtn");
        buttonIds.put("Bicep Curls", "R.id.curlsBtn");

        youTubePlayerView = findViewById(R.id.example_exercise_video);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> {
            player = youTubePlayer;
                for(TextView tv : filterBtns) {
                    tv.setVisibility(View.VISIBLE);
                displayButtons("");
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen();
        }
    }


    public void beginFilter(View v) {
        for(TextView tv : filterBtns) {
            tv.setBackground(null);
        }
        if (v.getId() != R.id.close_filters)
            v.setBackground(getDrawable(R.drawable.roundedcorner));
        switch(v.getId()) {
            case R.id.chest:
                displayButtons("Chest");
                break;
            case R.id.shoulders:
                displayButtons("Shoulders");
                break;
            case R.id.back:
                displayButtons("Back");
                break;
            case R.id.legs:
                displayButtons("Legs");
                break;
            case R.id.arms:
                displayButtons("Arms");
                break;
            case R.id.close_filters:
                displayButtons("X");
                break;
        }
    }

    @SuppressLint("NewApi")
    private void displayButtons(String filter) {
        if (filter.equals(""))
            filters = exercises;
        else
            findViewById(R.id.close_filters).setVisibility(View.VISIBLE);
            switch(filter) {
                case "Chest":
                    filters = new String[]{ "Bench Press", "Tricep Dips" };
                    break;
                case "Shoulders":
                    filters = new String[]{ "Bench Press", "Shoulder Press", "Lateral Raises", "Tricep Dips" };
                    break;
                case "Back":
                    filters = new String[]{ "Lat Pulldowns", "Deadlift" };
                    break;
                case "Legs":
                    filters = new String[]{ "Back Squat", "Deadlift" };
                    break;
                case "Arms":
                    filters = new String[]{ "Tricep Dips", "Bicep Curls" };
                    break;
                case "X":
                    filters = exercises;
                    findViewById(R.id.close_filters).setVisibility(View.INVISIBLE);
                    break;
            }
        gl.removeViews(0, gl.getChildCount());
        int i = 0;
        for(String s : filters) {
            Button b = new Button(this);
            GridLayout.Spec speccolumn = GridLayout.spec(i%2);
            GridLayout.Spec specrow = GridLayout.spec(i/2, GridLayout.TOP);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(specrow, speccolumn);
            params.setMargins(20, 20, 20, 20);

            b.setText(filters[i]);
            b.setTooltipText(filters[i]);
            b.setTextSize(20);
            b.setWidth(450);
            b.setHeight(450);
            switch(filters[i]) {
                case "Bench Press":
                    b.setId(R.id.benchBtn);
                    break;
                case "Shoulder Press":
                    b.setId(R.id.shoulder_pressBtn);
                    break;
                case "Lateral Raises":
                    b.setId(R.id.lat_raisesBtn);
                    break;
                case "Lat Pulldowns":
                    b.setId(R.id.pulldownsBtn);
                    break;
                case "Back Squat":
                    b.setId(R.id.squatBtn);
                    break;
                case "Deadlift":
                    b.setId(R.id.deadliftBtn);
                    break;
                case "Tricep Dips":
                    b.setId(R.id.dipsBtn);
                    break;
                case "Bicep Curls":
                    b.setId(R.id.curlsBtn);
                    break;
            }

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView placeholder = findViewById(R.id.video_placeholder);
                    placeholder.setVisibility(View.GONE);
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    player.loadVideo(videoIds.get(view.getTooltipText()), 0);
                }
            });

            gl.addView(b, params);
            i++;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}