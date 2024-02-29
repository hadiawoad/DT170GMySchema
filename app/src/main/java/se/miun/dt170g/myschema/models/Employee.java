package se.miun.dt170g.myschema.models;

public class Employee {
    private String employeeName;
    private int employeeNumber;

    public Employee(String employeeName, int price) {
        this.employeeNumber = price;
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

}