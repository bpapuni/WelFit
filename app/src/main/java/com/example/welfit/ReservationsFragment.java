package com.example.welfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservationsFragment extends Fragment implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    private RelativeLayout reservationOverlay;
    private TextView title;
    private EditText editTextClass, dateInput, timeInput;
    private String className, classDate, classTime, action;
    private Integer id;
    private Button bookBtn;
    private DbHandler dbHandler;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.WelFitTheme_NoActionBar);
        inflater = getActivity().getLayoutInflater().cloneInContext(contextThemeWrapper);
        View v = inflater.inflate(R.layout.fragment_reservation, container, false);

        dbHandler = new DbHandler(getActivity());
        user = dbHandler.getLoggedInUser();

        editTextClass = v.findViewById(R.id.reservation_class_name);
        editTextClass.setEnabled(false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            className = bundle.getString("className");
            classDate = bundle.getString("classDate");
            classTime = bundle.getString("classTime");
            action = bundle.getString("action");
            id = bundle.getInt("id");

        }
        editTextClass.setText(className);

        dateInput = v.findViewById(R.id.reservation_date);
        timeInput = v.findViewById(R.id.reservation_time);
        bookBtn = v.findViewById(R.id.btn_book);

        if (action.equals("Update")) {
            title = v.findViewById(R.id.reservation_banner);
            title.setText("Update Reservation");
            dateInput.setText(classDate);
            timeInput.setText(classTime);
            bookBtn.setText("Update");
        }

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dateInput.getText().toString().isEmpty() || timeInput.getText().toString().isEmpty()) {
                    TextView reservationError = getActivity().findViewById(R.id.reservation_error);
                    reservationError.setText("Fields cannot be left empty.");
                }
                else {
                    if (action.equals("Update")) {
                        Toast.makeText(getActivity(), className + " reservation updated.", Toast.LENGTH_SHORT).show();

                        Reservation updatedReservation = new Reservation(id, user.getId(), className, dateInput.getText().toString(), timeInput.getText().toString());
                        dbHandler.updateReservationDetails(updatedReservation);
                    }
                    else {
                        Toast.makeText(getActivity(), className + " reservation made.", Toast.LENGTH_SHORT).show();
                        Reservation newReservation = new Reservation(-1, user.getId(), className, dateInput.getText().toString(), timeInput.getText().toString());
                        dbHandler.insertReservationDetails(newReservation);
                    }
                    getActivity().onBackPressed();
                }
            }
        });

        reservationOverlay = v.findViewById(R.id.reservation_overlay);
        reservationOverlay.setOnClickListener(view -> getActivity().onBackPressed());

        return v;
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Date date = new Date(i, i1, i2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM");
        String stringDate = dateFormat.format(date);
        dateInput.setText(stringDate);
    }

    private void showTimePickerDialog() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = 0;
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), R.style.DialogTheme,this, hour, minute, false);
        timePicker.setTitle("Select a time");
        timePicker.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        timeInput.setText(String.format("%01d:%02d", i > 12 ? i - 12 : i, i1) + (i > 12 ? " PM" : " AM"));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}