package se.miun.dt170g.myschema.adapter;

import android.annotation.SuppressLint;
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
import se.miun.dt170g.myschema.models.Shift;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;
    //private ArrayList<Shift> employeeShift;

    //public EmployeeAdapter(Context context, ArrayList<Employee> employees, ArrayList<Shift> employeeShift)
    public EmployeeAdapter(Context context, ArrayList<Employee> employees){
        this.context = context;
        this.employees = employees;
        //this.employeeShift = employeeShift;
    }

    @NonNull
    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // Assuming there's a layout file named employee_item.xml for individual list items
        View view = inflater.inflate(R.layout.activity_main, parent, false);

        return new EmployeeAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {
       // holder.employeeName.setText("Employee Name: " + employees.get(position).getEmployeeName());
        //holder.employeeRole.setText("Employee Role: " + employees.get(position).getEmployeeRole());
       // holder.employeeDate.setText("Date: " + employeeShift.get(position).getDate());
       // holder.type.setText("Type: " + employeeShift.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView employeeName, employeeRole, employeeDate, type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.employeeName);
            employeeRole = itemView.findViewById(R.id.employeeRole);
            type = itemView.findViewById(R.id.employeeDate);
            employeeDate = itemView.findViewById(R.id.type);
        }
    }
}
