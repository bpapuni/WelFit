package com.example.welfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private TextView errorMsg;
    private EditText editTextEmail, editTextPw, editTextConfirmPw;
    private Button btnSignUp, btnReadUsers;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        String data = getIntent().getDataString();
//        EditText email = findViewById(R.id.sign_up_email);
//        email.setText(data);

        errorMsg = findViewById(R.id.sign_up_error);
        editTextEmail = findViewById(R.id.sign_up_email);
        editTextPw = findViewById(R.id.sign_up_password);
        editTextConfirmPw = findViewById(R.id.sign_up_confirm_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        btnReadUsers = findViewById(R.id.btn_view_users);
        dbHandler = new DbHandler(SignUp.this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = null;
                try {
                    // Check if fields are empty
                    if (editTextEmail.getText().toString().matches("") || editTextPw.getText().toString().matches("") || editTextConfirmPw.getText().toString().matches("")) {
                        throw new Exception("Fields cannot be left empty!");
                    }
                    // Validate email
                    else if (!editTextEmail.getText().toString().matches("^(.+)@(\\S+)$")) {
                        editTextEmail.setSelectAllOnFocus(true);
                        editTextEmail.requestFocus();
                        editTextEmail.setSelectAllOnFocus(false);
                        throw new Exception("Please enter a valid email!");
                    }
                    // Validate password
                    else if (editTextPw.getText().length() < 6) {
                        throw new Exception("Password must be 6 or more characters!");
                    }
                    // Check if passwords match
                    else if (!editTextPw.getText().toString().equals(editTextConfirmPw.getText().toString())) {
                        editTextPw.requestFocus();
                        editTextPw.setText("");
                        editTextConfirmPw.setText("");
                        throw new Exception("Passwords do not match!");
                    }
                    else {
                        user = new User(-1, editTextEmail.getText().toString(), editTextPw.getText().toString());
                        Toast.makeText(SignUp.this, "User has been added!", Toast.LENGTH_SHORT).show();
                        errorMsg.setText("");
                        dbHandler = new DbHandler(SignUp.this);
                        dbHandler.insertUserDetails(user);
                        Intent i = new Intent(SignUp.this, Login.class);
                        startActivity(i);
                    }
                }
                catch (Exception e) {
                    errorMsg.setText(e.getLocalizedMessage());
                }
            }
        });

        btnReadUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(SignUp.this, ViewUsers.class);
                startActivity(i);
            }
        });
    }
}