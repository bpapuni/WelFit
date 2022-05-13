package com.example.welfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationFragment extends Fragment implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    private RelativeLayout reservationOverlay;
    private EditText editTextClass, dateInput, timeInput;;
    private String className;
    private Spinner timeSpinner;
    private Button bookBtn;
    private DbHandler dbHandler;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reservation, container, false);

        dbHandler = new DbHandler(getActivity());
        user = dbHandler.getLoggedInUser();

        editTextClass = v.findViewById(R.id.reservation_class_name);
        editTextClass.setEnabled(false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            className = bundle.getString("className");
        }
        editTextClass.setText(className);

        dateInput = v.findViewById(R.id.reservation_date);
        timeInput = v.findViewById(R.id.reservation_time);
        bookBtn = v.findViewById(R.id.btn_book);

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
//
//        timeSpinner = v.findViewById(R.id.time_spinner);
//        timeSpinner.setOnItemSelectedListener(this);
//
//        List<String> categories = new ArrayList<String>();
//        categories.add("Item 1");
//        categories.add("Item 2");
//        categories.add("Item 3");
//        categories.add("Item 4");
//        categories.add("Item 5");
//        categories.add("Item 6");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        timeSpinner.setAdapter(dataAdapter);


        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            reservation = new Reservation(email, firstName, lastName, className, time);
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
        String date = i2 + "/" + i1 + "/" + i;
        dateInput.setText(date);
    }

    private void showTimePickerDialog() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), R.style.DialogTheme,this, hour, minute, false);
        timePicker.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        timeInput.setText(i + ":" + i1);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        timeSpinner.setSelection();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}