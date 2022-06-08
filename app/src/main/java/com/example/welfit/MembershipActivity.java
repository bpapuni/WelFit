package com.example.welfit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class MembershipActivity extends AppCompatActivity {
    private EditText firstName, lastName, email, pw, confirmPw;
    private TextView billingDate, detailsError;
    private Button updateBtn;
    private DbHandler dbHandler;
    private User user;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbHandler = new DbHandler(MembershipActivity.this);
        user = dbHandler.getLoggedInUser();
        firstName = findViewById(R.id.details_first_name);
        firstName.setText(user.getFirstName());
        lastName = findViewById(R.id.details_last_name);
        lastName.setText(user.getLastName());
        email = findViewById(R.id.details_email);
        email.setText(user.getEmail());
        pw = findViewById(R.id.details_pw);
        confirmPw = findViewById(R.id.details_confirm_pw);
        billingDate = findViewById(R.id.billing_date);
        detailsError = findViewById(R.id.details_error);
        updateBtn = findViewById(R.id.details_update);

        LocalDate nextWed = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, dd MMM");
        String selectedDate = nextWed.format(formatter);
        billingDate.setText("Next Billing Date:\t\t\t\t " + selectedDate);
    }

    public void UpdateDetails(View v) {
        String password = user.getPassword();
        if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || email.getText().toString().isEmpty())
            detailsError.setText("Name or Email fields cannot be left empty.");
        else if (!email.getText().toString().matches("^(.+)@(\\S+)$")) {
            email.setSelectAllOnFocus(true);
            email.requestFocus();
            email.setSelectAllOnFocus(false);
            detailsError.setText("Please enter a valid email!");
        }
        else if (pw.getText().length() > 0 && pw.getText().length() < 6) {
            detailsError.setText("Password must be 6 or more characters!");
        }
        else if (!pw.getText().toString().equals(confirmPw.getText().toString()))
            detailsError.setText("Passwords do not match.");
        else {
            if (pw.getText().length() > 0)
                password = confirmPw.getText().toString();
            detailsError.setText("");
            Toast.makeText(v.getContext(), "Details updated.", Toast.LENGTH_SHORT).show();
            User updatedUser = new User(user.getId(), firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password, "true");
            dbHandler.updateUserDetails(updatedUser);
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