package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    ArrayList<User> usersArrayList;
    private DbHandler dbHandler;

    private EditText emailInput, pwInput;
    private TextView errorMsg, signUp;
    private CheckBox rememberMeCheckBox;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usersArrayList = new ArrayList<>();
        dbHandler = new DbHandler(LoginActivity.this);
        usersArrayList = dbHandler.getUserDetails();

        emailInput = findViewById(R.id.login_email);
        pwInput = findViewById(R.id.login_password);
        errorMsg = findViewById(R.id.login_error);
        signUp = findViewById(R.id.btn_sign_up);
        signUp.setMovementMethod(LinkMovementMethod.getInstance());
        rememberMeCheckBox = findViewById(R.id.remember_me);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            emailInput.setText(loginPreferences.getString("username", ""));
            pwInput.setText(loginPreferences.getString("password", ""));
            rememberMeCheckBox.setChecked(true);
        }
    }

    public void BeginAuthentication(View v) {
        Authenticate();
    }

    public void Authenticate() {
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(emailInput.getWindowToken(), 0);

        String username = emailInput.getText().toString();
        String password = pwInput.getText().toString();

        if (rememberMeCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", username);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }

        // Only run if database exists
        if (!usersArrayList.isEmpty()) {
            // Iterate through database
            for (User u : usersArrayList) {
                // Reset login status of all users
                u.setIsLoggedIn("false");
                // Check if user email input is in the database
                if (emailInput.getText().toString().equals(u.getEmail())) {
                    if (pwInput.getText().toString().equals(u.getPassword())) {
                        u.setIsLoggedIn("true");
                        dbHandler.updateUserDetails(u);
                        startActivity(new Intent(this, LandingPageActivity.class));
                        errorMsg.setText("");
                    } else {
                        errorMsg.setText(R.string.invalid_login_password);
                    }
                    // Exit loop once instance is found
                    break;
                }
                // No instance of the users email input found in the database
                else if (usersArrayList.indexOf(u) == usersArrayList.size() - 1) {
                    errorMsg.setText(R.string.invalid_login_email);
                }
            }
        } else {
            Toast.makeText(this, "No database exists!", Toast.LENGTH_SHORT).show();
        }
    }
}