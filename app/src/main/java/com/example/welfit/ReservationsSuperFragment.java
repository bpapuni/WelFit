package com.example.welfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservationsSuperFragment extends Fragment implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    private EditText dateInput;
    private EditText timeInput;
    private String className;
    private Integer id;

    protected View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.WelFitTheme_NoActionBar);
        inflater = getActivity().getLayoutInflater().cloneInContext(contextThemeWrapper);
        View v = inflater.inflate(R.layout.fragment_reservation, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            className = bundle.getString("className");
        }

        EditText editTextClass = v.findViewById(R.id.reservation_class_name);
        editTextClass.setEnabled(false);
        editTextClass.setText(className);
        dateInput = v.findViewById(R.id.reservation_date);
        dateInput.setOnClickListener(view -> showDatePickerDialog());
        timeInput = v.findViewById(R.id.reservation_time);
        timeInput.setOnClickListener(view -> showTimePickerDialog());
        Button bookBtn = v.findViewById(R.id.btn_book);
        bookBtn.setOnClickListener(view -> processReservation());
        RelativeLayout reservationOverlay = v.findViewById(R.id.reservation_overlay);
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
        SimpleDateFormat simpledateformat = new SimpleDateFormat("EE, dd MMM");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i1, i2);
        String selectedDate = simpledateformat.format(newDate.getTime());
        dateInput.setText(selectedDate);
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
        timeInput.setText(String.format("%01d:%02d", i > 12 ? i - 12 : i, i1) + (i >= 12 ? " PM" : " AM"));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    protected void processReservation() {

    }
}