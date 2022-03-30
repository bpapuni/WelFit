package com.example.welfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executor;


public class Login extends AppCompatActivity {

    EditText emailInput, pwInput;
    TextView errorMsg;
    User user = new User("admin@gmail.com", "admin");
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.loginEmail);
        pwInput = findViewById(R.id.loginPassword);
        errorMsg = findViewById(R.id.loginError);


    }

    // Begin authentication when Login button is clicked.
    public void BeginAuthentication(View v) {
        Authenticate();
    }

    // Check login details match those kept on record.
    public void Authenticate() {
        if (emailInput.getText().toString().equals(user.getEmail())) {
            if (pwInput.getText().toString().equals(user.getPassword())) {
                ShowLandingPage();
            } else {
                errorMsg.setText(R.string.invalidPassword);
            }
        } else {
            errorMsg.setText(R.string.invalidEmail);
        }
    }

    // General purpose method to hold the intent call to go to the MainActivity class.
    public void ShowLandingPage() {
        startActivity(new Intent(this, MainActivity.class));
    }

    // If the user clicks the Enter tick key on the keyboard, attempt to
    // login using the Pin Login authentication.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            Authenticate();
        }
        return super.onKeyUp(keyCode, event);
    }

    // Override the onBackPressed method to close the application if the back btn is pressed.
    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            super.onBackPressed();
        }
    }
}