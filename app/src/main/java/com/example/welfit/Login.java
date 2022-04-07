package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends AppCompatActivity {

    User user = new User("admin@gmail.com", "admin", "John", "Doe");

    private EditText emailInput, pwInput;
    private TextView errorMsg;
    private CheckBox saveLoginCheckBox;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.loginEmail);
        pwInput = findViewById(R.id.loginPassword);
        errorMsg = findViewById(R.id.loginError);
        saveLoginCheckBox = findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            emailInput.setText(loginPreferences.getString("username", ""));
            pwInput.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }

    // Begin authentication when Login button is clicked.
    public void BeginAuthentication(View v) {
        Authenticate();
    }

    // Check login details match those kept on record.
    public void Authenticate() {
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(emailInput.getWindowToken(), 0);

        String username = emailInput.getText().toString();
        String password = pwInput.getText().toString();

        if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", username);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }

//        doSomethingElse();
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
}