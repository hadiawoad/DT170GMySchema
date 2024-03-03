package se.miun.dt170g.myschema.models;

public class Shift {

    private int shiftId;
    private String date;
    private String type;
    private int employeeId;

    public Shift(int shiftId, String date, String type, int employeeId) {
        this.shiftId = shiftId;
        this.date = date;
        this.type = type;
        this.employeeId = employeeId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
