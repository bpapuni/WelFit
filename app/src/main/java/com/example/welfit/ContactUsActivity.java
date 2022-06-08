package com.example.welfit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ContactUsActivity extends AppCompatActivity {
    private Spinner dropdown;
    private EditText message;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dropdown = findViewById(R.id.topic_spinner);
        String[] items = new String[]{"Class Reservation", "Membership", "Gym Hygiene", "General Enquiry"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        message = findViewById(R.id.message);
        errorMessage = findViewById(R.id.contact_error);
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0)
                    errorMessage.setText("");
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SendMessage(View view) {
        if (message.getText().toString().isEmpty())
            errorMessage.setText("Message cannot be left empty.");
        else{
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "wfgym@wandw.ac.nz" });
            email.putExtra(Intent.EXTRA_SUBJECT, dropdown.getSelectedItem().toString());
            email.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
            email.setType("message/rfc822");

            try {
                startActivity(Intent.createChooser(email, "Choose an Email client:"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(ContactUsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}