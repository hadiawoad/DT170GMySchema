package se.miun.dt170g.myschema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import se.miun.dt170g.myschema.R;
import se.miun.dt170g.myschema.models.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees){
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // Assuming there's a layout file named employee_item.xml for individual list items
        View view = inflater.inflate(R.layout.activity_main, parent, false);

        return new EmployeeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {
        holder.employeeName.setText("Employee Name: " + employees.get(position).getEmployeeName());
        holder.employeeNumber.setText("Employee Number: " + employees.get(position).getEmployeeNumber());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView employeeName, employeeNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.employeeName);
            employeeNumber = itemView.findViewById(R.id.employeeNumber);
        }
    }
}
