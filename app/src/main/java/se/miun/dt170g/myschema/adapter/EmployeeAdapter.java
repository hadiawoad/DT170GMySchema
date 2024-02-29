package se.miun.dt170g.myschema.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import se.miun.dt170g.myschema.R;
import se.miun.dt170g.myschema.models.Employee;
import android.view.ViewGroup;
import android.view.LayoutInflater;


public class EmployeeAdapter {

    private Context context;
    private ArrayList<Employee> employee;

    public EmployeeAdapter(Context context, ArrayList<Employee> employee){
        this.context = context;
        this.employee = employee;
    }

    @NonNull
    @Override
        public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, parent, false);

        return new EmployeeAdapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {

        holder.employeeName.setText("Employee Name: " + employee.get(position).getEmployeeName());
        holder.employeeNumber.setText("Employee Number: " + employee.get(position).getEmployeeNumber());
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView employeeName, employeeNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.employeeName);
            employeeNumber = itemView.findViewById(R.id.employeeNumber);

        }
    }

}
