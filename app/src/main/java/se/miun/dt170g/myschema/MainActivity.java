package se.miun.dt170g.myschema;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and calendar view
        Button callButton = findViewById(R.id.ringa);
        CalendarView calendarView = findViewById(R.id.calendarView);
        RecyclerView recyclerView = findViewById(R.id.ShiftRV);

        // Set OnClickListener for the call button
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCallPermission(); // This initiates the permission request and call process
            }
        });


        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Extract the year, month, and day
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int dayOfMonth = currentDate.getDayOfMonth();

        String date = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month, dayOfMonth);

        fetchEmployee(recyclerView, date);

        // Listen for date changes on the calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                String date = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);


                fetchEmployee(recyclerView, date);

                Log.d("gg date: ", date);
            }
        });

    }

    public void fetchEmployee(RecyclerView recyclerView, String date){
        Call<ArrayList<Employee>> call = fetchData.getEmployee();

        call.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                if (response.isSuccessful()) {
                    employee = response.body();

                    Log.d("fetchEmployee", String.valueOf(response.code()));

                    fetchShift(recyclerView, date);
                }else{
                    Log.d("Response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                Log.d("ResponseFailure", t.getMessage());
            }
        });
    }



   public void fetchShift(RecyclerView recyclerView, String date){

        Call<ArrayList<Shift>> call = fetchData.getShiftByDate(date);
        call.enqueue(new Callback<ArrayList<Shift>>() {
            @Override
            public void onResponse(Call<ArrayList<Shift>> call, Response<ArrayList<Shift>> response) {
                if (response.isSuccessful()) {
                    employeeShift = response.body();


                    EmployeeAdapter adapter = new EmployeeAdapter(MainActivity.this, employee, employeeShift);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    Log.d("fetchShift", String.valueOf(response.code()));

                } else {
                    Log.d("fetchShift", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Shift>> call, Throwable t) {
                Log.d("fetchShiftFailure", t.getMessage());
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
        callIntent.setData(Uri.parse("tel:0722853211")); // Specify the phone number
        startActivity(callIntent);
    }

}



