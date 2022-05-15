package com.example.welfit;

        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class ViewReservationsActivity extends AppCompatActivity {
    private ArrayList<Reservation> reservationsArrayList;
    private DbHandler dbHandler;
    private User user;
    private ReservationRecyclerViewAdapter reservationRVAdapter;
    private RecyclerView reservationRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

        reservationsArrayList = new ArrayList<>();
        dbHandler = new DbHandler(ViewReservationsActivity.this);
        user = dbHandler.getLoggedInUser();
        Toast.makeText(this, user.getFirstName(), Toast.LENGTH_SHORT).show();
        reservationsArrayList = dbHandler.getReservationDetails(user);
        reservationRVAdapter = new ReservationRecyclerViewAdapter(reservationsArrayList, ViewReservationsActivity.this);
        reservationRV = findViewById(R.id.reservations_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewReservationsActivity.this, RecyclerView.VERTICAL, false);
        reservationRV.setLayoutManager(linearLayoutManager);

        reservationRV.setAdapter(reservationRVAdapter);
    }
}