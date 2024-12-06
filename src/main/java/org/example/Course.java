package org.example;

public class Course {
    private String crn;
    private String subject;
    private String department;
    private String number;
    private String section;
    private String instructor;
    private String days;
    private String startTime;
    private String endTime;
    private String building;
    private String room;
    private String campus;
    private String classType;
    private String method;
    private String dpOrIn;
    private String pot;
    private int actual;
    private int max;

    // Default Constructor
    public Course() {}

    // Parameterized Constructor
    public Course(String crn, String subject, String department, String number, String section,
                  String instructor, String days, String startTime, String endTime,
                  String building, String room, String campus, String classType,
                  String method, String dpOrIn, String pot, int actual, int max) {
        this.crn = crn;
        this.subject = subject;
        this.department = department;
        this.number = number;
        this.section = section;
        this.instructor = instructor;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.building = building;
        this.room = room;
        this.campus = campus;
        this.classType = classType;
        this.method = method;
        this.dpOrIn = dpOrIn;
        this.pot = pot;
        this.actual = actual;
        this.max = max;
    }

    // Getters and Setters

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDpOrIn() {
        return dpOrIn;
    }

    public void setDpOrIn(String dpOrIn) {
        this.dpOrIn = dpOrIn;
    }

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Course{" +
                "crn='" + crn + '\'' +
                ", subject='" + subject + '\'' +
                ", department='" + department + '\'' +
                ", number='" + number + '\'' +
                ", section='" + section + '\'' +
                ", instructor='" + instructor + '\'' +
                ", days='" + days + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", campus='" + campus + '\'' +
                ", classType='" + classType + '\'' +
                ", method='" + method + '\'' +
                ", dpOrIn='" + dpOrIn + '\'' +
                ", pot='" + pot + '\'' +
                ", actual=" + actual +
                ", max=" + max +
                '}';
    }
}