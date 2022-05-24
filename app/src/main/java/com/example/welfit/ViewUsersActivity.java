package com.example.welfit;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewUsersActivity extends AppCompatActivity {
    private ArrayList<User> userArrayList;
    private DbHandler dbHandler;
    private UserRecyclerViewAdapter userRVAdapter;
    private RecyclerView usersRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        userArrayList = new ArrayList<>();
        dbHandler = new DbHandler(ViewUsersActivity.this);
        userArrayList = dbHandler.getUserDetails();

        // on below line passing our array lost to our adapter class.
        userRVAdapter = new UserRecyclerViewAdapter(userArrayList, ViewUsersActivity.this);
        usersRV = findViewById(R.id.users_recycler_view);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewUsersActivity.this, RecyclerView.VERTICAL, false);
        usersRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        usersRV.setAdapter(userRVAdapter);
    }

    public void onClick(View v) {
        Log.d("Exercises", "test");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}