package se.miun.dt170g.myschema.models;


public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeRole;
    private String employeeAddress;
    private int employeeTelephone;

    public Employee(int employeeId, String employeeName, String employeeRole, String employeeAddress, int employeeTelephone) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeAddress = employeeAddress;
        this.employeeTelephone = employeeTelephone;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public int getEmployeeTelephone() {
        return employeeTelephone;
    }

    public void setEmployeeTelephone(int employeeTelephone) {
        this.employeeTelephone = employeeTelephone;
    }

}