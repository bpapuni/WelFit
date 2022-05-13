package com.example.welfit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewUsersActivity extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<User> userArrayList;
    private DbHandler dbHandler;
    private UserRecyclerViewAdapter userRVAdapter;
    private RecyclerView usersRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        // initializing our all variables.
        userArrayList = new ArrayList<>();
        dbHandler = new DbHandler(ViewUsersActivity.this);

        // getting our course array
        // list from db handler class.
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
}