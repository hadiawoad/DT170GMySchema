package se.miun.dt170g.myschema.models;

public class Employee {
    private String employeeName;
    private int employeeNumber;

    public Employee(String employeeName, int employeeNumber) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

}