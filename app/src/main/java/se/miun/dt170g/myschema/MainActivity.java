package se.miun.dt170g.myschema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170g.myschema.adapter.EmployeeAdapter;
import se.miun.dt170g.myschema.fetch.FetchData;
import se.miun.dt170g.myschema.fetch.Retro;
import se.miun.dt170g.myschema.models.Employee;
import se.miun.dt170g.myschema.models.Shift;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Employee> employee = new ArrayList<>();
    private ArrayList<Shift> employeeShift = new ArrayList<>();

    private Retro retrofit = new Retro();
    private FetchData fetchData = retrofit.getRetrofit().create(FetchData.class);

    private static final int REQUEST_CALL_PERMISSION = 1; // Request code for permission
    private long selectedDateMillis; // To hold the selected date from the CalendarView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and calendar view
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        Button button1 = findViewById(R.id.visa);
        Button callButton = findViewById(R.id.ringa);
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Default to current date
        selectedDateMillis = calendarView.getDate();

        // Listen for date changes on the calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Calendar month is zero-based, add 1 for current month
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDateMillis = calendar.getTimeInMillis();
                fetchShift(selectedDateMillis);
            }

        });

        // Set OnClickListener for the booking button
        button1.setOnClickListener(v -> {

            //Toast.makeText(MainActivity.this, "Selected date for booking: " + employeeShift.size(), Toast.LENGTH_LONG).show();
            // Here, implement your logic to handle the booking process
        });

        // Set OnClickListener for the call button
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCallPermission(); // This initiates the permission request and call process
            }
        });

    }

   public void fetchShift(long date){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'Z'", Locale.getDefault());
       String formattedDate = sdf.format(new Date(date));
        // Retrofit call to fetch Shift data
        Call<ArrayList<Shift>> call = fetchData.getShift();
        call.enqueue(new Callback<ArrayList<Shift>>() {
            @Override
            public void onResponse(Call<ArrayList<Shift>> call, Response<ArrayList<Shift>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Shift> shifts = response.body();
                    ArrayList<Shift> filteredShifts = (ArrayList<Shift>) shifts.stream()
                            .filter(shift -> shift.getEmployeeDate().startsWith(formattedDate))
                            .collect(Collectors.toList());

                    EmployeeAdapter adapter = new EmployeeAdapter(MainActivity.this, employee, filteredShifts);
                    RecyclerView recyclerView = null;
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    Log.d("fetch res", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Shift>> call, Throwable t) {
                Log.d("fetch res", t.getMessage());
            }
        });
    }


    // Existing methods for requesting permissions and making a phone call
    public void requestCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            makePhoneCall();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0722853211")); // Specify the phone number here
        startActivity(callIntent);
    }

}



