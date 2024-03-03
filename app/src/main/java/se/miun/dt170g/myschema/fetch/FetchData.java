package se.miun.dt170g.myschema.fetch;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import se.miun.dt170g.myschema.models.Employee;
import se.miun.dt170g.myschema.models.Shift;

public interface FetchData {

    @GET("shift")
    Call<ArrayList<Shift>> getShift();

    @GET("employee")
    Call<ArrayList<Employee>> getEmployee();


    @GET("shift/{date}")
    Call<ArrayList<Shift>> getShiftByDate(@Path("date") String date);

}
