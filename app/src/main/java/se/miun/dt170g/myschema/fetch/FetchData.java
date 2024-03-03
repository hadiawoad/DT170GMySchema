package se.miun.dt170g.myschema.fetch;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import se.miun.dt170g.myschema.models.Employee;
import se.miun.dt170g.myschema.models.Shift;

public interface FetchData {

    @GET("shift")
    Call<ArrayList<Shift>> getShift();

    @GET("employee")
    Call<ArrayList<Employee>> getEmployee();

}
