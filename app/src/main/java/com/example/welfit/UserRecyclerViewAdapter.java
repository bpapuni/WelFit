package com.example.welfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<User> userArrayList;
    private Context context;

    // constructor
    public UserRecyclerViewAdapter(ArrayList<User> userArrayList, Context context) {
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        User user = userArrayList.get(position);
        holder.userEmail.setText(user.getEmail());
        holder.userPw.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView userEmail, userPw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            userEmail = itemView.findViewById(R.id.user_email);
            userPw = itemView.findViewById(R.id.user_pw);
        }
    }
}
