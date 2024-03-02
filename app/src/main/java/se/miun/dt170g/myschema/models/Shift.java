package se.miun.dt170g.myschema.models;

public class Shift {

    private int shiftId;
    private String employeeDate;
    private String type;
    private int employeeId;

    public Shift(int shiftId, String employeeDate, String type, int employeeId) {
        this.shiftId = shiftId;
        this.employeeDate = employeeDate;
        this.type = type;
        this.employeeId = employeeId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(String employeeDate) {
        this.employeeDate = employeeDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
