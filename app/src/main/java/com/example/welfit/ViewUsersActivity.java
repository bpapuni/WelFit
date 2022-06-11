package com.example.welfit;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class ViewUsersActivity extends AppCompatActivity {
    private ArrayList<User> userArrayList;
    private DbHandler dbHandler;
    private User user;
    private UserRecyclerViewAdapter userRVAdapter;
    private RecyclerView usersRV;
    private BottomAppBar bottomBar;
    private CheckBox checkBox;
    private MenuItem editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        userArrayList = new ArrayList<>();
        dbHandler = new DbHandler(ViewUsersActivity.this);
        user = dbHandler.getLoggedInUser();

        userArrayList = dbHandler.getUserDetails();
        userRVAdapter = new UserRecyclerViewAdapter(userArrayList, ViewUsersActivity.this);
        usersRV = findViewById(R.id.users_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewUsersActivity.this, RecyclerView.VERTICAL, false);
        usersRV.setLayoutManager(linearLayoutManager);
        usersRV.setAdapter(userRVAdapter);

        bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_delete) {
                DeleteUsers();
            }
            return false;
        });
    }

    private void DeleteUsers() {
        int count = usersRV.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            RecyclerView.ViewHolder holder = usersRV.findViewHolderForAdapterPosition(i);
            checkBox = holder.itemView.findViewById(R.id.item_checkbox);
            User user = userArrayList.get(i);

            if (checkBox.isChecked()) {
                dbHandler.deleteUser(user.getId());
                userArrayList.remove(i);
                userRVAdapter.notifyItemRemoved(i);
            }
            checkBox.setVisibility(View.INVISIBLE);
            findViewById(R.id.bottom_bar).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.your_reservations, menu);
        editBtn = menu.findItem(R.id.action_edit);
        return true;
    }

    public void onClick(View v) {
        Log.d("Exercises", "test");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } else {
            for (int i = 1; i < usersRV.getChildCount(); i++) {
                RecyclerView.ViewHolder holder = usersRV.findViewHolderForAdapterPosition(i);
                checkBox = holder.itemView.findViewById(R.id.item_checkbox);

                if (checkBox.getVisibility() == View.INVISIBLE) {
                    checkBox.setVisibility(View.VISIBLE);
                    findViewById(R.id.bottom_bar).setVisibility(View.VISIBLE);
                } else {
                    checkBox.setVisibility(View.INVISIBLE);
                    findViewById(R.id.bottom_bar).setVisibility(View.INVISIBLE);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}