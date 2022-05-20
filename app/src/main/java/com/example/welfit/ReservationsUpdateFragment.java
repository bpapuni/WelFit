package com.example.welfit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ReservationsUpdateFragment extends ReservationsBaseFragment {
    private TextView reservationError;
    private EditText dateInput, timeInput;
    private String className, classDate, classTime, action;
    private Integer id;
    private DbHandler dbHandler;
    private User user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflateFragment(inflater, container);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            className = bundle.getString("className");
            classDate = bundle.getString("classDate");
            classTime = bundle.getString("classTime");
            id = bundle.getInt("id");
        }

        dbHandler = new DbHandler(getActivity());
        user = dbHandler.getLoggedInUser();
        TextView title = v.findViewById(R.id.reservation_banner);
        title.setText("Update Reservation");
        dateInput = v.findViewById(R.id.reservation_date);
        dateInput.setText(classDate);
        timeInput = v.findViewById(R.id.reservation_time);
        timeInput.setText(classTime);
        reservationError = v.findViewById(R.id.reservation_error);
        Button bookBtn = v.findViewById(R.id.btn_book);
        bookBtn.setText("Update");

        return v;
    }

    @Override
    public void processReservation(){
        if (dateInput.getText().toString().isEmpty() || timeInput.getText().toString().isEmpty()) {
            reservationError.setText("Fields cannot be left empty.");
        }
        else {
            Toast.makeText(getActivity(), className + " reservation updated.", Toast.LENGTH_SHORT).show();
            Reservation updatedReservation = new Reservation(id, user.getId(), className, dateInput.getText().toString(), timeInput.getText().toString());
            dbHandler.updateReservationDetails(updatedReservation);
            getActivity().onBackPressed();
        }
    }
}
